package com.tiankui.reactService.util;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.csvreader.CsvReader;

public class CSVUtil {

	static char separator = ',';

	/**
	 * java导入csv文件
	 * 
	 * @param filePath
	 *            导入路径
	 * @return
	 * @throws Exception
	 */
	public static List<String[]> importCsv(String filePath) throws Exception {
		CsvReader reader = null;
		List<String[]> dataList = new ArrayList<String[]>();
		try {
			reader = new CsvReader(filePath, separator, Charset.forName("GBK"));
			// 读取表头 加上这一句是不算表头数据从第二行开始取
			reader.readHeaders();
			// 逐条读取记录，直至读完
			while (reader.readRecord()) {
				dataList.add(reader.getRawRecord().split(","));
				// // 下面是几个常用的方法
				// 读取一条记录
				System.out.println(reader.getRawRecord());
				// 按列名读取这条记录的值
				System.out.println(reader.get(0));
				System.out.println(reader.get(1));
				System.out.println(reader.get(2));
				System.out.println(reader.get(3));
			}
		} catch (Exception e) {
			System.out.println("读取CSV出错..." + e);
			throw e;
		} finally {
			if (null != reader) {
				reader.close();
			}
		}
		return dataList;
	}

	public static List<List<Object>> getBankListByCSV(InputStream in, String originalFilename) {
		return null;
	}
}
