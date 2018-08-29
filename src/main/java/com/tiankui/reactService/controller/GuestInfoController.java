package com.tiankui.reactService.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tiankui.reactService.entity.ErrorInfo;
import com.tiankui.reactService.entity.GuestInfo;
import com.tiankui.reactService.entity.Result;
import com.tiankui.reactService.entity.ResultArray;
import com.tiankui.reactService.entity.TSyslog;
import com.tiankui.reactService.enums.LogLevel;
import com.tiankui.reactService.enums.LogType;
import com.tiankui.reactService.service.IGuestInfoService;
import com.tiankui.reactService.service.ISyslogService;
import com.tiankui.reactService.util.CSVUtil;
import com.tiankui.reactService.util.ExcelUtils;
import com.tiankui.reactService.util.IPUtil;
import com.tiankui.reactService.util.ObjectUtil;

/**
 * 
 * @ClassName: GuestInfoController
 * @Description: TODO 客户信息操作
 * @author zhouao
 * @date 2018年7月24日
 *
 */
@RestController
@RequestMapping("/api/guestInfo")
public class GuestInfoController {

	@Autowired
	private IGuestInfoService guestInfoService;

	@Autowired
	public ISyslogService syslogService;

	/**
	 * 
	 * @Title: doImport
	 * @Description: TODO 客户信息导入
	 * @param request
	 * @throws Exception
	 *             参数
	 * @return Result<Integer> 返回类型
	 */
	@RequestMapping(value = "/upload", method = { RequestMethod.GET, RequestMethod.POST })
	public Result<List<ErrorInfo>> doImport(HttpServletRequest request, @RequestParam String token) {
		Result<List<ErrorInfo>> result = new Result<List<ErrorInfo>>();
		TSyslog tSyslog = new TSyslog();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		InputStream in = null;
		MultipartFile file = multipartRequest.getFile("file");
		if (file.isEmpty()) {
			try {
				throw new Exception("文件不存在！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			in = file.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<List<Object>> listob = null;
		if (file.getOriginalFilename().toString().endsWith(".csv")) {
			listob = CSVUtil.getBankListByCSV(in, file.getOriginalFilename());
		} else {
			listob = ExcelUtils.getBankListByExcel(in, file.getOriginalFilename());
		}
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<ErrorInfo> errorList = new ArrayList<ErrorInfo>();
		try {
			errorList = guestInfoService.upload(listob, token, file.getOriginalFilename());
		} catch (Exception e) {
			e.printStackTrace();
			tSyslog = new TSyslog(LogType.FILE_UPLOAD_FAIL.getIndex(), LogLevel.FATAL.getIndex(),
					file.getOriginalFilename() + "为非法文件！仅支持导入合法的10086工单文件！请检查！");
			result.setData(null);
			result.setStatus(-1);
			result.setMessage("为非法文件！仅支持导入集团客户文件！请检查！");
			syslogService.insert(tSyslog, token, IPUtil.getIpAdrress(request));
			return result;
		}
		if (errorList.size() > 0) {
			tSyslog = new TSyslog(LogType.FILE_UPLOAD_FAIL.getIndex(), LogLevel.ERROR.getIndex(),
					file.getOriginalFilename() + "文件记录有错误！请检查！");
			result.setData(errorList);
			result.setStatus(0);
			result.setMessage("文件有错误记录！导入失败！请检查！");
		}
		if (errorList.size() == 0) {
			tSyslog = new TSyslog(LogType.FILE_UPLOAD_SUCCESS.getIndex(), LogLevel.INFO.getIndex(),
					file.getOriginalFilename() + "文件导入成功！");
			result.setData(null);
			result.setStatus(1);
			result.setMessage("文件导入成功！");
		}
		syslogService.insert(tSyslog, token, IPUtil.getIpAdrress(request));
		return result;
	}

	/**
	 * 
	 * @Title: list @Description: TODO 获取客户信息列表 @param map 参数 @return
	 *         Result<List<GuestInfo>> 返回类型 @throws
	 */
	@RequestMapping(value = "/list")
	public Result<List<GuestInfo>> list(HttpServletRequest request, @RequestParam String token,
			@RequestBody Map<String, Object> map) {
		Result<List<GuestInfo>> result = new Result<List<GuestInfo>>();
		List<GuestInfo> list = guestInfoService.getListByMap(map);
		int count = guestInfoService.getCountList(map);
		result.setData(list);
		result.setCount(count);
		result.setStatus(1);
		result.setMessage("查询成功！");
		TSyslog tSyslog = new TSyslog(LogType.DATA_QUERY_SUCCESS.getIndex(), LogLevel.INFO.getIndex(),
				"集团客户信息列表数据查询成功！");
		syslogService.insert(tSyslog, token, IPUtil.getIpAdrress(request));
		return result;
	}

	/**
	 * 
	 * @Title: getAreas @Description: TODO 查询客户所在区域列表 @param @return 参数 @return
	 *         Result<String> 返回类型 @throws
	 */
	@RequestMapping(value = "/areas")
	public Result<List<String>> getAreas() {
		Result<List<String>> result = new Result<List<String>>();
		List<String> list = guestInfoService.getAreaList();
		result.setData(list);
		result.setStatus(1);
		result.setMessage("查询成功！");
		return result;
	}

	/**
	 * 
	 * @Title: export @Description: TODO 客户信息导出 @param @param map @param @param
	 *         request @param @param response 参数 @return void 返回类型 @throws
	 */
	@RequestMapping(value = "/export")
	public void export(@RequestParam Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response) {
		String[] excelHeader = { "编号", "名称", "地址", "级别", "服务等级", "所属国家", "所属省份", "所属地市", "所属区县", "经理", "经理联系电话",
				"经理联系邮箱", "创建时间", "创建人" };
		String[] ds_titles = { "guestNo", "guestName", "guestAddress", "guestLevel", "guestServiceLevel", "country",
				"province", "city", "area", "guestManage", "guestManagePhone", "guestManageEmail", "createTime",
				"creater" };
		int[] ds_format = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 7, 1 };
		int[] widths = { 256 * 15, 256 * 25, 256 * 25, 256 * 5, 256 * 10, 256 * 10, 256 * 10, 256 * 10, 256 * 10,
				256 * 10, 256 * 15, 256 * 15, 256 * 20, 256 * 10 };
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		List<GuestInfo> list = guestInfoService.getList(map);
		for (GuestInfo user : list) {
			Map<String, Object> toMap = ObjectUtil.objectToMap(user);
			toMap.put("createTime", user.getCreateTime().getTime());
			mapList.add(toMap);
		}
		try {
			ExcelUtils.export("客户信息表", "客户信息", excelHeader, ds_titles, ds_format, widths, mapList, request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: addGuestInfo @Description: TODO 添加客户信息 @param @param
	 *         map @param @return 参数 @return Result<Integer> 返回类型 @throws
	 */
	@RequestMapping(value = "/add")
	public Result<Integer> addGuestInfo(HttpServletRequest request, @RequestBody Map<String, Object> map,
			@RequestParam String token) {
		TSyslog tSyslog = new TSyslog();
		Result<Integer> result = new Result<Integer>();
		map.put("token", token);
		int resultNum = guestInfoService.addGuestInfo(map);
		result.setData(resultNum);
		if (resultNum == 1) {
			result.setStatus(1);
			result.setMessage("添加成功！");
			tSyslog = new TSyslog(LogType.DATA_ADD_SUCCESS.getIndex(), LogLevel.INFO.getIndex(),
					"添加集团客户数据成功！被添加集团客户：" + map.get("guestName"));
		} else {
			tSyslog = new TSyslog(LogType.DATA_ADD_FAIL.getIndex(), LogLevel.ERROR.getIndex(),
					"添加集团客户数据失败！被添加集团客户：" + map.get("guestName"));
		}
		syslogService.insert(tSyslog, token, IPUtil.getIpAdrress(request));
		return result;
	}

	/**
	 * 
	 * @Title: delGuestInfo @Description: TODO 删除客户信息 @param @param
	 *         id @param @return 参数 @return Result<Integer> 返回类型 @throws
	 */
	@RequestMapping(value = "/del")
	public Result<Integer> delGuestInfo(HttpServletRequest request, @RequestParam String token,
			@RequestParam String id) {
		TSyslog tSyslog = new TSyslog();
		GuestInfo delGuest = guestInfoService.getGuestInfoById(id);
		int resultNum = guestInfoService.delGuestInfo(id);
		Result<Integer> result = new Result<Integer>();
		if (resultNum == 1) {
			result.setData(resultNum);
			result.setStatus(1);
			result.setMessage("删除成功！");
			tSyslog = new TSyslog(LogType.DATA_DELETE_SUCCESS.getIndex(), LogLevel.INFO.getIndex(),
					"删除客户数据成功！被删除客户：" + delGuest.getGuestName());
		} else {
			result.setData(resultNum);
			result.setStatus(0);
			result.setMessage("删除失败！");
			tSyslog = new TSyslog(LogType.DATA_DELETE_SUCCESS.getIndex(), LogLevel.INFO.getIndex(),
					"删除客户数据成功！被删除客户：" + delGuest.getGuestName());
		}
		syslogService.insert(tSyslog, token, IPUtil.getIpAdrress(request));
		return result;
	}

	/**
	 * 
	 * @Title: getGuestInfoById @Description: TODO 根据ID查询客户信息 @param @param
	 *         id @param @return 参数 @return ResultArray<User> 返回类型 @throws
	 */
	@RequestMapping(value = "/query")
	public ResultArray<GuestInfo> getGuestInfoById(@RequestParam String id) {
		GuestInfo resultGuestInfo = guestInfoService.getGuestInfoById(id);
		ResultArray<GuestInfo> result = new ResultArray<GuestInfo>();
		result.setMessage("查询成功！");
		if (ObjectUtil.isNotNull(resultGuestInfo)) {
			GuestInfo[] guestInfos = { resultGuestInfo };
			result.setData(guestInfos);
			result.setStatus(1);
		} else {
			result.setData(null);
			result.setStatus(0);
		}
		return result;
	}

	/**
	 * 
	 * @Title: getGuestInfoById @Description:
	 *         TODO(这里用一句话描述这个方法的作用) @param @param id @param @return 参数 @return
	 *         ResultArray<GuestInfo> 返回类型 @throws
	 */
	@RequestMapping(value = "/queryDetail")
	public Result<GuestInfo> getGuestInfo(@RequestParam String id) {
		GuestInfo resultGuestInfo = guestInfoService.getGuestInfoById(id);
		Result<GuestInfo> result = new Result<GuestInfo>();
		result.setMessage("查询成功！");
		if (ObjectUtil.isNotNull(resultGuestInfo)) {
			result.setData(resultGuestInfo);
			result.setStatus(1);
		} else {
			result.setData(null);
			result.setStatus(0);
		}
		return result;
	}

	/**
	 * 
	 * @Title: editGuestInfo @Description: TODO 修改客户信息 @param @param
	 *         id @param @param map @param @return 参数 @return Result<Integer>
	 *         返回类型 @throws
	 */
	@RequestMapping(value = "/edit")
	public Result<Integer> editGuestInfo(HttpServletRequest request, @RequestParam String token,
			@RequestParam String id, @RequestBody Map<String, Object> map) {
		TSyslog tSyslog = new TSyslog();
		int resultNum = guestInfoService.updateGuestInfo(id, map);
		Result<Integer> result = new Result<Integer>();
		if (resultNum == 1) {
			result.setData(resultNum);
			result.setStatus(1);
			result.setMessage("修改成功！");
			tSyslog = new TSyslog(LogType.DATA_UPDATE_SUCCESS.getIndex(), LogLevel.INFO.getIndex(),
					"修改集团客户数据成功！被修改的集团客户：" + guestInfoService.getGuestInfoById(id).getGuestName());
		} else {
			result.setData(resultNum);
			result.setStatus(0);
			result.setMessage("修改失败！");
			tSyslog = new TSyslog(LogType.DATA_UPDATE_FAIL.getIndex(), LogLevel.INFO.getIndex(),
					"修改集团客户数据失败！被修改的集团客户：" + guestInfoService.getGuestInfoById(id).getGuestName());
		}
		syslogService.insert(tSyslog, token, IPUtil.getIpAdrress(request));
		return result;
	}

	/**
	 * 
	 * @Title: getGuestNo @Description: TODO 查询重复客户编号 @param @param
	 *         guestNo @param @return 参数 @return Result<Integer> 返回类型 @throws
	 */
	@RequestMapping(value = "/getNo")
	public Result<Integer> getGuestNo(@RequestParam String id) {
		int num = guestInfoService.getGuestNo(id);
		Result<Integer> result = new Result<Integer>();
		result.setData(num);
		result.setStatus(1);
		result.setMessage("查询成功！");
		return result;
	}

	/**
	 * 导入模板下载
	 * @Title: download
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param request
	 * @param @param response
	 * @param @return    参数
	 * @return String    返回类型
	 * @throws
	 */
	@RequestMapping("/exportTable")
	public String download(HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("text/html;charset=utf-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;

		String downLoadPath = this.getClass().getClassLoader().getResource("/").getPath() + "/file/客户信息模板.xlsx"; // 注意不同系统的分隔符
		try {
			long fileLength = new File(downLoadPath).length();
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition",
					"attachment; filename=" + new String("客户信息模板.xlsx".getBytes("utf-8"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				try {
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (bos != null)
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return null;
	}

}
