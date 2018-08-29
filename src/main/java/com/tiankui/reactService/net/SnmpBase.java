package com.tiankui.reactService.net;

import com.tiankui.reactService.entity.CpuDetail;
import com.tiankui.reactService.entity.CpuInfo;
import com.tiankui.reactService.entity.DiskInfo;
import com.tiankui.reactService.entity.MemoryInfo;
import com.tiankui.reactService.entity.SystemInfo;
import org.apache.log4j.Logger;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * SNMP公共信息采集类 基本都是OID为1.3.6.1.2.1.25下的信息
 */
public class SnmpBase extends SnmpUtils {
	private static Logger logger = Logger.getLogger(SnmpBase.class);

	/**
	 * 基础的CPU信息采集(CPU核数和每个CPU的使用率)
	 *
	 * @param ip
	 * @param community
	 * @return
	 * @throws Exception
	 */
	public CpuInfo getCpuInfo(String ip, String community) throws Exception {
		ArrayList<CpuDetail> cpuInfos = new ArrayList<CpuDetail>();
		int userRate = 0;
		String cpuDesc = "";

		try {
			ArrayList<String> deviceIndex = snmpWalk(ip, community, Constant.DEVICE_INDEX);
			// 过滤重复的cpu信息 start
			boolean flag = true;
			for (int i = 0; i < deviceIndex.size(); i++) {
				String deviceType = snmpGet(ip, community, Constant.DEVICE_TYPE + "." + deviceIndex.get(i));
				if (deviceType.equals(Constant.DEVICE_CPU_ID)) {
					String cpuInfo = snmpGet(ip, community, Constant.DEVICE_INFO + "." + deviceIndex.get(i));
					String loadCurrent = snmpGet(ip, community, Constant.CPU_LOAD + "." + deviceIndex.get(i));
					CpuDetail obj = new CpuDetail();
					obj.setCpuDesc(cpuInfo);
					obj.setUserRate(loadCurrent);
					if (flag) {
						int intelCpu = cpuInfo.indexOf("Intel");
						int amdCpu = cpuInfo.indexOf("AMD");

						if (intelCpu != -1) {
							cpuDesc = cpuInfo.substring(intelCpu);
						} else if (amdCpu != -1) {
							cpuDesc = cpuInfo.substring(amdCpu);
						}
						flag = false;
					}
					userRate += Integer.parseInt(loadCurrent);
					obj.setSysRate(loadCurrent);
					obj.setFreeRate(Integer.toString(100 - Integer.parseInt(loadCurrent)));

					cpuInfos.add(obj);
				}
			}
			int coreNum = this.getCpuCoreNum(ip, community);
			userRate = userRate / coreNum;
			CpuInfo result = new CpuInfo();
			result.setCpuDetailInfos(cpuInfos);
			result.setCpuDesc(cpuDesc);
			result.setSysRate(Integer.toString(userRate));
			result.setFreeRate(Integer.toString(100 - userRate));
			result.setCoreNum(coreNum);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 获取CPU核数
	 *
	 * @param ip
	 * @param community
	 * @return
	 */
	private int getCpuCoreNum(String ip, String community) {
		ArrayList<String> result = snmpWalk(ip, community, Constant.CPU_LOAD);
		return result.size();
	}

	/**
	 * 获取内存大小
	 *
	 * @param ip
	 * @param community
	 * @return
	 */
	public String getMemorySize(String ip, String community) {
		try {
			NumberFormat nf = NumberFormat.getInstance();
			nf.setRoundingMode(RoundingMode.HALF_UP);
			nf.setMinimumFractionDigits(1);
			nf.setMaximumFractionDigits(1);
			return nf.format(Double.parseDouble(snmpGet(ip, community, Constant.MEMORY_SIZE)) / (1024 * 1024));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取物理内存信息
	 *
	 * @param ip
	 * @param community
	 * @return
	 * @throws Exception
	 */
	public MemoryInfo getMemoryInfo(String ip, String community) throws Exception {
		MemoryInfo memoryInfo = new MemoryInfo();
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(1);
		nf.setMaximumFractionDigits(1);

		int index = getMemoryIndex(ip, community);
		double physicalSize = Double.parseDouble(snmpGet(ip, community, Constant.WINDOW_DISK_SIZE + "." + index))
				* Double.parseDouble(snmpGet(ip, community, Constant.WINDOW_DISK_AMOUNT + "." + index))
				/ (1024 * 1024 * 1024);
		double physicalUsedSize = Double.parseDouble(snmpGet(ip, community, Constant.WINDOW_DISK_USED + "." + index))
				* Double.parseDouble(snmpGet(ip, community, Constant.WINDOW_DISK_AMOUNT + "." + index))
				/ (1024 * 1024 * 1024);

		memoryInfo.setMemorySize(nf.format(physicalSize));
		memoryInfo.setMemoryUsedSize(nf.format(physicalUsedSize));
		memoryInfo.setMemoryFreeSize(nf.format(physicalSize - physicalUsedSize));

		nf.setMinimumFractionDigits(0);
		nf.setMaximumFractionDigits(0);
		memoryInfo.setMemoryPercentage(nf.format(physicalUsedSize / physicalSize * 100));
		return memoryInfo;
	}

	private int getMemoryIndex(String ip, String community) throws Exception {
		ArrayList<String> diskIndexTable = snmpWalk(ip, community, Constant.WINDOW_DISK_INDEX);
		String physicalMemoryID = Constant.DEVICE_STORAGE_RAM_ID;
		int index = 0;
		int i = 1;
		for (String str : diskIndexTable) {
			String diskType = snmpGet(ip, community, Constant.WINDOW_DISK_TYPE + "." + i);
			if (diskType.equals(physicalMemoryID)) {
				index = Integer.parseInt(str);
			}
			i++;
		}
		diskIndexTable = null;
		return index;
	}

	/**
	 * 获取系统信息
	 *
	 * @param ip
	 * @param community
	 * @return
	 * @throws Exception
	 */
	public SystemInfo getSysInfo(String ip, String community) throws Exception {
		SystemInfo systemInfo = new SystemInfo();
		systemInfo.setSysDesc(snmpGet(ip, community, Constant.SYS_DESC));
		systemInfo.setSysContact(snmpGet(ip, community, Constant.SYS_CONTACT));
		systemInfo.setSysName(snmpGet(ip, community, Constant.SYS_NAME));
		systemInfo.setSysUpTime(snmpGet(ip, community, Constant.SYS_UPTIME));
		systemInfo.setSysLocation(snmpGet(ip, community, Constant.SYS_LOCATION));
		return systemInfo;
	}

	private ArrayList<String> getDiskIndex(String ip, String community) throws Exception {
		String hrStorageFixedDisk = Constant.DEVICE_DISK_ID;

		ArrayList<String> diskIndexTable = snmpWalk(ip, community, Constant.WINDOW_DISK_INDEX);
		ArrayList<String> result = new ArrayList<String>();
		for (String str : diskIndexTable) {
			String diskType = snmpGet(ip, community, Constant.WINDOW_DISK_TYPE + "." + str);
			if (diskType.equals(hrStorageFixedDisk)) {
				result.add(str);
			}
		}
		diskIndexTable = null;
		return null;
	}

	/**
	 * 返回系统硬盘信息，最后一个为硬盘的整个信息
	 *
	 * @param ip
	 * @param community
	 * @return
	 * @throws Exception
	 */
	public ArrayList<DiskInfo> getDiskInfo(String ip, String community) throws Exception {
		ArrayList<String> index = getDiskIndex(ip, community);

		NumberFormat nf = NumberFormat.getInstance();
		nf.setRoundingMode(RoundingMode.HALF_UP);
		nf.setMinimumFractionDigits(1);
		nf.setMaximumFractionDigits(1);

		double totalSize = 0;// 硬盘的整个大小
		double totalUsedSize = 0;// 硬盘的使用空间

		ArrayList<DiskInfo> result = new ArrayList<DiskInfo>();
		for (int i = 0; i < index.size(); i++) {
			DiskInfo obj = new DiskInfo();
			obj.setDiskDesc(snmpGet(ip, community, Constant.WINDOW_DISK_DESC + "." + index.get(i)));
			double sSize = Double.parseDouble(snmpGet(ip, community, Constant.WINDOW_DISK_SIZE + "." + index.get(i)))
					* Double.parseDouble(snmpGet(ip, community, Constant.WINDOW_DISK_AMOUNT + "." + index.get(i)))
					/ (1024 * 1024 * 1024);
			obj.setDiskSize(nf.format(sSize));
			double usedSize = Double.parseDouble(snmpGet(ip, community, Constant.WINDOW_DISK_USED + "." + index.get(i)))
					* Double.parseDouble(snmpGet(ip, community, Constant.WINDOW_DISK_AMOUNT + "." + index.get(i)))
					/ (1024 * 1024 * 1024);
			obj.setDiskSize(nf.format(usedSize));
			obj.setDiskFreeSize(nf.format(sSize - usedSize));
			nf.setMinimumFractionDigits(0);
			nf.setMaximumFractionDigits(0);
			obj.setPercentUsed(Integer.parseInt(nf.format(usedSize / sSize * 100)));

			totalSize += sSize;
			totalUsedSize += usedSize;
			result.add(obj);
		}
		DiskInfo ob = new DiskInfo();
		ob.setPercentUsed(Integer.parseInt(nf.format(totalUsedSize / totalSize * 100)));
		nf.setMinimumFractionDigits(1);
		nf.setMaximumFractionDigits(1);

		ob.setDiskSize(nf.format(totalSize));
		ob.setDiskUsedSize(nf.format(totalUsedSize));
		ob.setDiskFreeSize(nf.format(totalSize - totalUsedSize));
		result.add(ob);
		return result;
	}
}
