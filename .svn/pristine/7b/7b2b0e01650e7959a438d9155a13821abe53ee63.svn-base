package com.tiankui.reactService.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	// excel默认宽度；
	private static int width = 256 * 20;
	// 默认字体
	private static String excelfont = "微软雅黑";

	private final static String excel2003L = ".xls"; // 2003- 版本的excel
	private final static String excel2007U = ".xlsx"; // 2007+ 版本的excel

	/**
	 * 
	 * @param excelName
	 *            导出的EXCEL名字
	 * @param sheetName
	 *            导出的SHEET名字 当前sheet数目只为1
	 * @param headers
	 *            导出的表格的表头
	 * @param ds_titles
	 *            导出的数据 map.get(key) 对应的 key
	 * @param ds_format
	 *            导出数据的样式 1:String left; 2:String center 3:String right 4 int
	 *            right 5:float ###,###.## right 6:number: #.00% 百分比 right
	 * @param widths
	 *            表格的列宽度 默认为 256*14
	 * @param data
	 *            数据集 List<Map>
	 * @param response
	 * @throws IOException
	 */
	public static void export(String excelName, String sheetName, String[] headers, String[] ds_titles, int[] ds_format,
			int[] widths, List<Map<String, Object>> data, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		HttpSession session = request.getSession();
		session.setAttribute("state", null);
		if (widths == null) {
			widths = new int[ds_titles.length];
			for (int i = 0; i < ds_titles.length; i++) {
				widths[i] = width;
			}
		}
		if (ds_format == null) {
			ds_format = new int[ds_titles.length];
			for (int i = 0; i < ds_titles.length; i++) {
				ds_format[i] = 1;
			}
		}
		// 设置文件名
		String fileName = "";
		if (StringUtils.isNotEmpty(excelName)) {
			fileName = excelName;
		}
		// 创建一个工作薄
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建一个sheet
		HSSFSheet sheet = wb.createSheet(StringUtils.isNotEmpty(sheetName) ? sheetName : "excel");
		// 创建表头，如果没有跳过
		int headerrow = 0;
		if (headers != null) {
			HSSFRow row = sheet.createRow(headerrow);
			// 表头样式
			HSSFCellStyle style = wb.createCellStyle();
			HSSFFont font = wb.createFont();
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font.setFontName(excelfont);
			font.setFontHeightInPoints((short) 11);
			style.setFont(font);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			for (int i = 0; i < headers.length; i++) {
				sheet.setColumnWidth(i, widths[i]);
				HSSFCell cell = row.createCell(i);
				cell.setCellValue(headers[i]);
				cell.setCellStyle(style);
				row.setHeight((short) 450);
			}
			headerrow++;
		}
		// 表格主体 解析list
		if (data != null) {
			List<HSSFCellStyle> styleList = new ArrayList<HSSFCellStyle>();

			for (int i = 0; i < ds_titles.length; i++) { // 列数
				HSSFCellStyle style = wb.createCellStyle();
				HSSFFont font = wb.createFont();
				font.setFontName(excelfont);
				font.setFontHeightInPoints((short) 10);
				style.setFont(font);
				style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				style.setBorderRight(HSSFCellStyle.BORDER_THIN);
				style.setBorderTop(HSSFCellStyle.BORDER_THIN);
				if (ds_format[i] == 1) {
					style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
				} else if (ds_format[i] == 2) {
					style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				} else if (ds_format[i] == 3) {
					style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
					// int类型
				} else if (ds_format[i] == 4) {
					style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
					// int类型
					style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0"));
				} else if (ds_format[i] == 5) {
					// float类型
					style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
					style.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
				} else if (ds_format[i] == 6) {
					// 百分比类型
					style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
					style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));
				}
				styleList.add(style);
			}
			for (int i = 0; i < data.size(); i++) { // 行数
				HSSFRow row = sheet.createRow(headerrow);
				Map<String, Object> map = data.get(i);
				for (int j = 0; j < ds_titles.length; j++) { // 列数
					HSSFCell cell = row.createCell(j);
					Object o = map.get(ds_titles[j]);
					if (o == null || "".equals(o)) {
						cell.setCellValue("");
					} else if (ds_format[j] == 4) {
						// int
						cell.setCellValue((Long.valueOf((map.get(ds_titles[j])) + "")).longValue());
					} else if (ds_format[j] == 5 || ds_format[j] == 6) {
						// float
						cell.setCellValue((Double.valueOf((map.get(ds_titles[j])) + "")).doubleValue());
					} else if (ds_format[j] == 7) {
						// date
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date date2 = new Date();
						date2.setTime((Long.valueOf((map.get(ds_titles[j])) + "")).longValue());
						cell.setCellValue(sdf.format(date2));
					} else if (ds_format[j] == 8) {
						Integer is = Integer.valueOf(map.get(ds_titles[j]).toString());
						if (is == 0) {
							cell.setCellValue("否");
						}
						if (is == 1) {
							cell.setCellValue("是");
						}
					} else {
						cell.setCellValue(map.get(ds_titles[j]) + "");
					}
					cell.setCellStyle((HSSFCellStyle) styleList.get(j));
				}
				headerrow++;
			}
		}

		fileName = fileName + excel2003L;
		String filename = "";
		try {
			filename = encodeChineseDownloadFileName(request, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		response.setHeader("Content-disposition", filename);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + filename);
		response.setHeader("Pragma", "No-cache");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
		session.setAttribute("state", "open");

	}

	/**
	 * 对文件流输出下载的中文文件名进行编码 屏蔽各种浏览器版本的差异性
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public static String encodeChineseDownloadFileName(HttpServletRequest request, String pFileName) throws Exception {

		String filename = null;
		String agent = request.getHeader("USER-AGENT");
		if (null != agent) {
			if (-1 != agent.indexOf("Firefox")) {// Firefox
				filename = "=?UTF-8?B?"
						+ (new String(org.apache.commons.codec.binary.Base64.encodeBase64(pFileName.getBytes("UTF-8"))))
						+ "?=";
			} else if (-1 != agent.indexOf("Chrome")) {// Chrome
				filename = new String(pFileName.getBytes(), "ISO8859-1");
			} else {// IE7+
				filename = java.net.URLEncoder.encode(pFileName, "UTF-8");
				filename = filename.replace("+", "%20");
			}
		} else {
			filename = pFileName;
		}
		return filename;
	}

	/**
	 * 描述：获取IO流中的数据，组装成List<List<Object>>对象
	 * 
	 * @param in,fileName
	 * @return
	 * @throws IOException
	 */
	public static List<List<Object>> getBankListByExcel(InputStream in, String fileName){
		List<List<Object>> list = null;

		// 创建Excel工作薄
		Workbook work = null;
		try {
			work = getWorkbook(in, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null == work) {
			try {
				throw new Exception("创建Excel工作薄为空！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;

		list = new ArrayList<List<Object>>();
		// 遍历Excel中所有的sheet
		for (int i = 0; i < work.getNumberOfSheets(); i++) {
			sheet = work.getSheetAt(i);
			if (sheet == null) {
				continue;
			}
			// 遍历当前sheet中的所有行
			for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
				row = sheet.getRow(j);
				//过滤表头
				if (row == null || row.getFirstCellNum() == j) {
					continue;
				}
				// 遍历所有的列
				List<Object> li = new ArrayList<Object>();
				for (int y = 0; y <= row.getLastCellNum(); y++) {
					cell = row.getCell(y);
					li.add(getCellValue(cell)==null?"":getCellValue(cell));
				}
				list.add(li);
			}
		}
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 描述：根据文件后缀，自适应上传文件的版本
	 * 
	 * @param inStr,fileName
	 * @return
	 * @throws Exception
	 */
	public static Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
		Workbook wb = null;
		String fileType = fileName.substring(fileName.lastIndexOf("."));
		if (excel2003L.equals(fileType)) {
			wb = new HSSFWorkbook(inStr); // 2003-
		} else if (excel2007U.equals(fileType)) {
			wb = new XSSFWorkbook(inStr); // 2007+
		} else {
			throw new Exception("解析的文件格式有误！");
		}
		return wb;
	}

	/**
	 * 描述：对表格中数值进行格式化
	 * 
	 * @param cell
	 * @return
	 */
	public static Object getCellValue(Cell cell) {
		Object value = null;
		DecimalFormat df = new DecimalFormat("0"); // 格式化number String字符
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd"); // 日期格式化
		DecimalFormat df2 = new DecimalFormat("0.00"); // 格式化数字
		if (cell==null) {
			return "";
		}
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			value = cell.getRichStringCellValue().getString();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if ("General".equals(cell.getCellStyle().getDataFormatString())) {
				value = df.format(cell.getNumericCellValue());
			} else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
				value = sdf.format(cell.getDateCellValue());
			} else {
				value = df2.format(cell.getNumericCellValue());
			}
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			value = cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_BLANK:
			value = "";
			break;
		default:
			break;
		}
		return value;
	}
}