package com.tiankui.reactService.service.impl;

import com.tiankui.reactService.entity.Dict;
import com.tiankui.reactService.entity.ErrorInfo;
import com.tiankui.reactService.entity.GuestInfo;
import com.tiankui.reactService.entity.User;
import com.tiankui.reactService.mapper.DictGroupMapper;
import com.tiankui.reactService.mapper.GuestInfoMapper;
import com.tiankui.reactService.mapper.UserMapper;
import com.tiankui.reactService.service.IGuestInfoService;
import com.tiankui.reactService.util.LoginUserCache;
import com.tiankui.reactService.util.ObjectUtil;
import com.tiankui.reactService.util.UUIDUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: GuestInfoService
 * @Description: TODO 客户信息管理
 * @author zhouao
 * @date 2018年7月24日
 *
 */
@Service
public class GuestInfoService implements IGuestInfoService {

	private static Logger logger = Logger.getLogger(GuestInfoService.class);
	@Autowired
	private GuestInfoMapper guestInfoMapper;
	@Autowired
	private DictGroupMapper dictGroupMapper;
	@Autowired
	private UserMapper userMapper;

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	@Override
	public List<ErrorInfo> upload(List<List<Object>> list, String token, String fileName) throws Exception{
		User user = userMapper.getTokenUser(token);
		List<GuestInfo> allList = new ArrayList<GuestInfo>();
		for (int i = 0; i < list.size(); i++) {
			List<Object> lo = list.get(i);
			GuestInfo vo = new GuestInfo();
			vo.setId(UUIDUtil.getUUID());
			vo.setGuestNo(String.valueOf(lo.get(0)));
			vo.setGuestName(String.valueOf(lo.get(1)));
			vo.setGuestAddress(String.valueOf(lo.get(2)));
			vo.setGuestLevel(String.valueOf(lo.get(3)));
			vo.setGuestServiceLevel(String.valueOf(lo.get(4)));
			vo.setCountry(String.valueOf(lo.get(5)));
			vo.setProvince(String.valueOf(lo.get(6)));
			vo.setCity(String.valueOf(lo.get(7)));
			vo.setArea(getAreas(String.valueOf(lo.get(8))));
			vo.setGuestManage(String.valueOf(lo.get(9)));
			vo.setGuestManagePhone(String.valueOf(lo.get(10)));
			vo.setGuestManageEmail(String.valueOf(lo.get(11)) == null ? "" : String.valueOf(lo.get(11)));
			vo.setCreateTime(new Date());
			vo.setCreater(user.getName());
			allList.add(vo);
		}
		List<ErrorInfo> errorList = new ArrayList<ErrorInfo>();
		errorList = rmGuestList(fileName, allList);
		//判断是否有错误数据，若有则停止导入
		if(errorList.size() <= 0){
			List<GuestInfo> repeatList = rmRepeat(fileName, allList);
			if (repeatList.size() > 0) {
				logger.error("文件记录中有与数据库客户编号重复的数据，将用于更新数据");
				logger.error("更新中...");
				long currentTime = System.currentTimeMillis();
				int batchNum = updateBatch(repeatList);
				long endTime = System.currentTimeMillis();
				logger.error("更新完成。");
				logger.error("总共更新" + repeatList.size() + "条，更新了" + batchNum + "批。用时【"+ (endTime - currentTime)  +"ms】");
				allList.removeAll(repeatList);
			}
			if(allList.size() > 0){
				int count = allList.size();
				logger.error("开始导入");
				logger.error("导入中...");
				long currentTime = System.currentTimeMillis();
				int batchNum = insertBatch(allList);
				long endTime = System.currentTimeMillis();
				logger.error("导入成功。");
				logger.error("总共导入" + count + "条，导入了" + batchNum + "批。用时【"+ (endTime - currentTime)  +"ms】");
			}
		}else{
			logger.error("文件中含有错误记录，停止导入！");
		}
		return errorList;
	}

	/**
	 * 查询字典Code
	 * @Title: getAreas
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param string
	 * @param @return    参数
	 * @return String    返回类型
	 * @throws
	 */
	private String getAreas(String string) {
		String area = "其他"; 
		List<Dict> areaList = dictGroupMapper.selectByPrimaryKey("9b21e9114e564fe8ac7346e2ac9f88e2");
		for (Dict dict : areaList) {
			if(string.contains(dict.getName())){
				area = dict.getDictCode();
			}
		}
		return area;
	}

	@Override
	public List<GuestInfo> getList(Map<String, Object> map) {
		if (ObjectUtil.isNotNull(map.get("num"))) {
			map.put("guestNo", "%" + map.get("num") + "%");
		}
		if (ObjectUtil.isNotNull(map.get("name"))) {
			map.put("guestName", "%" + map.get("name") + "%");
		}
		if (ObjectUtil.isNotNull(map.get("address"))) {
			map.put("guestAddress", "%" + map.get("address") + "%");
		}
		List<GuestInfo> list = guestInfoMapper.getList(map);
		return list;
	}

	/**
	 * 
	 * @Title: updateBatch @Description: TODO 分批批量更新客户信息 @param @param
	 * insertList @param @return 参数 @return int 返回类型 @throws
	 */
	private int updateBatch(List<GuestInfo> allList) {
		List<GuestInfo> repeat = new ArrayList<GuestInfo>();
		repeat.addAll(allList);
		// 每批次插入数量
		int batchCount = 1000;
		// 批次
		int batchNum = 1;
		for (;;) {
			List<GuestInfo> guestInfoList = new ArrayList<GuestInfo>();
			if (batchCount >= repeat.size()) {
				guestInfoMapper.updateBatch(repeat);
				// 一次可全部插入
				break;
			} else {
				// 需要多次插入
				guestInfoList = repeat.subList(0, batchCount);
				guestInfoMapper.updateBatch(guestInfoList);
			}
			repeat.removeAll(guestInfoList);
			batchNum++;
		}
		return batchNum;
	}

	/**
	 * 
	 * @Title: rmRepeat @Description: TODO 更新重复编码的记录 @param @param
	 * fileName @param @param allList @param @param insertList @param @param
	 * repeatList 参数 @return void 返回类型 @throws
	 */
	private List<GuestInfo> rmRepeat(String fileName, List<GuestInfo> allList) {
		List<GuestInfo> repeatList = new ArrayList<GuestInfo>();
		// 将客户编号重复的记录更新
		for (GuestInfo guestInfo : allList) {
			GuestInfo guestInfo2 = guestInfoMapper.getGuestInfoByNo(guestInfo.getGuestNo());
			if (ObjectUtil.isNotNull(guestInfo2)) {
				repeatList.add(guestInfo);
				int indexOf = allList.indexOf(guestInfo) + 2;
				logger.error(fileName + "，第" + indexOf + "行数据客户编号重复，即将应用最新数据！");
			}
		}
		return repeatList;
	}

	/**
	 * 
	 * @Title: rmGuestList @Description: TODO 移除客户编号或名称为空的记录 @param @param
	 * fileName @param @param allList @param @param insertList @param @param
	 * removeList 参数 @return void 返回类型 @throws
	 */
	private List<ErrorInfo> rmGuestList(String fileName, List<GuestInfo> allList) {
		List<ErrorInfo> errorList = new ArrayList<ErrorInfo>();
		// 移除客户编号或名称为空的记录
		for (GuestInfo guestInfo : allList) {
			if (ObjectUtil.isNull(guestInfo.getGuestNo())) {
				ErrorInfo errorInfo = new ErrorInfo();
				int indexOf = allList.indexOf(guestInfo) + 2;
				errorInfo.setMessage("第" + indexOf + "行数据校验失败，客户编号为空！");
				logger.error(fileName + "，第" + indexOf + "行数据校验失败，客户编号为空！");
				errorList.add(errorInfo);
			}
			if(ObjectUtil.isNotNull(guestInfo.getGuestNo())){
				int indexOf = allList.indexOf(guestInfo);
				for (int i=indexOf+1;i<allList.size();i++) {
					if (ObjectUtil.isNotNull(allList.get(i).getGuestNo())) {
						if(guestInfo.getGuestNo().equals(allList.get(i).getGuestNo())){
							ErrorInfo errorInfo = new ErrorInfo();
							errorInfo.setMessage("第" + (indexOf+2) + "行数据和第"+(i+2)+"行数据客户编号重复，校验失败！");
							logger.error(fileName + "，第" + (indexOf+2) + "行数据和第"+i+2+"行数据客户编号重复，校验失败！");
							errorList.add(errorInfo);
						}
					}
				}
			}
			if (ObjectUtil.isNull(guestInfo.getGuestName())){
				ErrorInfo errorInfo = new ErrorInfo();
				int indexOf = allList.indexOf(guestInfo) + 2;
				errorInfo.setMessage("第" + indexOf + "行数据校验失败，客户名称为空！");
				logger.error(fileName + "，第" + indexOf + "行数据校验失败，客户名称为空！");
				errorList.add(errorInfo);
			}
			if (ObjectUtil.isNull(guestInfo.getGuestAddress())){
				ErrorInfo errorInfo = new ErrorInfo();
				int indexOf = allList.indexOf(guestInfo) + 2;
				errorInfo.setMessage("第" + indexOf + "行数据校验失败，客户地址为空！");
				logger.error(fileName + "，第" + indexOf + "行数据校验失败，客户地址为空！");
				errorList.add(errorInfo);
			}
			if (ObjectUtil.isNull(guestInfo.getArea())){
				ErrorInfo errorInfo = new ErrorInfo();
				int indexOf = allList.indexOf(guestInfo) + 2;
				errorInfo.setMessage("第" + indexOf + "行数据校验失败，客户所在区域为空！");
				logger.error(fileName + "，第" + indexOf + "行数据校验失败，客户所在区域为空！");
				errorList.add(errorInfo);
			}
		}
		return errorList;
	}

	/**
	 * 
	 * @Title: insertBatch @Description: TODO 分批批量插入客户信息 @param @param
	 * insertList @param @return 参数 @return int 返回类型 @throws
	 */
	private int insertBatch(List<GuestInfo> insertList) {
		// 每批次插入数量
		int batchCount = 1000;
		// 批次
		int batchNum = 1;
		for (;;) {
			List<GuestInfo> guestInfoList = new ArrayList<GuestInfo>();
			if (batchCount >= insertList.size()) {
				guestInfoMapper.insertByBatch(insertList);
				// 一次可全部插入
				break;
			} else {
				// 需要多次插入
				guestInfoList = insertList.subList(0, batchCount);
				guestInfoMapper.insertByBatch(guestInfoList);
			}
			insertList.removeAll(guestInfoList);
			batchNum++;
		}
		return batchNum;
	}

	@Override
	public List<GuestInfo> getListByMap(Map<String, Object> map) {
		if (ObjectUtil.isNotNull(map.get("guestNo"))) {
			map.put("guestNo", "%" + map.get("guestNo") + "%");
		}
		if (ObjectUtil.isNotNull(map.get("guestName"))) {
			map.put("guestName", "%" + map.get("guestName") + "%");
		}
		if (ObjectUtil.isNotNull(map.get("guestAddress"))) {
			map.put("guestAddress", "%" + map.get("guestAddress") + "%");
		}
		if (ObjectUtil.isNotNull(map.get("start"))){
			int start = Integer.valueOf(map.get("start").toString());
			int limit = Integer.valueOf(map.get("limit").toString());
			map.put("start", start + 1);
			map.put("limit", limit + start);
		}
		List<GuestInfo> list = guestInfoMapper.getListByMap(map);
		return list;
	}

	@Override
	public List<String> getAreaList() {
		List<String> areaList = guestInfoMapper.getAreas();
		return areaList;
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	@Override
	public int addGuestInfo(Map<String, Object> map) {
		GuestInfo guestInfo = null;
		try {
			guestInfo = (GuestInfo) ObjectUtil.mapToObject(map, GuestInfo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		guestInfo.setId(UUIDUtil.getUUID());
		guestInfo.setGuestLevel(guestInfo.getGuestLevel() == null ? "" : guestInfo.getGuestLevel());
		guestInfo.setGuestServiceLevel(guestInfo.getGuestServiceLevel() == null ? "" : guestInfo.getGuestServiceLevel());
		guestInfo.setGuestManage(guestInfo.getGuestManage() == null ? "" : guestInfo.getGuestManage());
		guestInfo.setGuestManagePhone(guestInfo.getGuestManagePhone() == null ? "" : guestInfo.getGuestManagePhone());
		guestInfo.setGuestManageEmail(guestInfo.getGuestManageEmail() == null ? "" : guestInfo.getGuestManageEmail());
		guestInfo.setCountry("中国");
		guestInfo.setProvince("安徽省");
		guestInfo.setCity("六安");
		guestInfo.setCreateTime(new Date());
		guestInfo.setCreater(LoginUserCache.get(map.get("token").toString()).getName());
		int result = guestInfoMapper.save(guestInfo);
		return result;
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	@Override
	public int delGuestInfo(String id) {
		int result = guestInfoMapper.detele(id);
		return result;
	}

	@Override
	public GuestInfo getGuestInfoById(String id) {
		GuestInfo guestInfo = guestInfoMapper.getGuestInfoById(id);
		return guestInfo;
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	@Override
	public int updateGuestInfo(String id, Map<String, Object> map) {
		map.put("id", id);
		map.put("guestLevel", map.get("guestLevel") == null ? "" : map.get("guestLevel"));
		map.put("guestServiceLevel", map.get("guestServiceLevel") == null ? "" : map.get("guestServiceLevel"));
		map.put("guestManage", map.get("guestManage") == null ? "" : map.get("guestManage"));
		map.put("guestManagePhone", map.get("guestManagePhone") == null ? "" : map.get("guestManagePhone"));
		map.put("guestManageEmail", map.get("guestManageEmail") == null ? "" : map.get("guestManageEmail"));
		int result = guestInfoMapper.update(map);
		return result;
	}

	@Override
	public int getGuestNo(String guestNo) {
		int num = guestInfoMapper.getGuestNo(guestNo);
		return num;
	}

	@Override
	public int getCountList(Map<String, Object> map) {
		if (ObjectUtil.isNotNull(map.get("guestNo"))) {
			map.put("guestNo", "%" + map.get("guestNo") + "%");
		}
		if (ObjectUtil.isNotNull(map.get("guestName"))) {
			map.put("guestName", "%" + map.get("guestName") + "%");
		}
		if (ObjectUtil.isNotNull(map.get("guestAddress"))) {
			map.put("guestAddress", "%" + map.get("guestAddress") + "%");
		}
		int count = guestInfoMapper.getCountListByMap(map);
		return count;
	}

}
