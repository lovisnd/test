package com.tiankui.reactService.service.impl;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tiankui.reactService.entity.Equip;
import com.tiankui.reactService.entity.EquipType;
import com.tiankui.reactService.entity.Equipment;
import com.tiankui.reactService.entity.Port;
import com.tiankui.reactService.service.ICollectionService;

@Service
public class CollectionService implements ICollectionService {

	@Override
	public List<EquipType> getList() {
		List<EquipType> equipTypeList = new ArrayList<EquipType>();
        for (int x = 0; x < 3; x++) {
        	String icon = "desktop";
        	String iconColor = "#f00";
        	String title = "主机";
        	if (x == 1) {
        		icon = "database";
        		title = "储存";
        		iconColor = "#0f0";
        	}
        	if (x == 2) {
				icon = "swap";
				title = "交换机";
				iconColor = "#00f";
			}
            EquipType e = new EquipType();
            e.setId("" + x);
            e.setKey("" + x);
            e.setIcon(icon);
            e.setIconColor(iconColor);
            e.setTitle(title);
            ArrayList<Equip> equipArrayList = new ArrayList<Equip>();
            for (int i = 0; i < 3; i++) {
                Equip equip = new Equip();
                equip.setId(x + "-" + i);
                equip.setKey(x + "-" + i);
                equip.setIcon(icon);
                equip.setIconColor(iconColor);
                equip.setTitle("型号 " + x + "-" + i);
                equip.setTypeId("" + x);
                ArrayList<Equipment> equipmentArrayList = new ArrayList<Equipment>();
                for (int j = 0; j < 3; j++) {
                    Equipment equipment = new Equipment();
                    equipment.setId(x + "-" + i + "-" + j);
                    equipment.setEquipId(x + "-" + i + "-" + j);
                    equipment.setKey(x + "-" + i + "-" + j);
                    equipment.setIcon(icon);
                    equipment.setIconColor(iconColor);
                    equipment.setTitle("设备" + x + "-" + i + "-" + j);
                    equipment.setIsLeaf(true);
                    equipmentArrayList.add(equipment);
                }
                equip.setChildren(equipmentArrayList);
                equipArrayList.add(equip);
            }
            e.setChildren(equipArrayList);
            equipTypeList.add(e);
        }
		return equipTypeList;
	}

	@Override
	public Map<String, Object> getFlow() {
		Map<String, Object> map = new HashMap<String, Object>();

        double[][] strings = new double[100][];
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(4);

        for (int i = 0; i < 100; i++) {
            double d = Math.random() * 10;
            long time = System.currentTimeMillis();
            strings[i] = new double[]{new BigDecimal((time + i * 1000 * 60)).doubleValue(), new BigDecimal(nf.format(d)).doubleValue()};
        }
        map.put("flowRateData", strings);
		return map;
	}

	@Override
	public Map<String, Object> getDeviceStatus() {
		Map<String, Object> map = new HashMap<String, Object>();

        double[][] strings1 = new double[100][];
        double[][] strings2 = new double[100][];
        double[][] strings3 = new double[100][];
        double[][] strings4 = new double[100][];
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(4);

        for (int i = 0; i < 100; i++) {
            long time = System.currentTimeMillis();
            strings1[i] = new double[]{new BigDecimal((time + i * 1000 * 60)).doubleValue(), new BigDecimal(nf.format(Math.random() * 40)).doubleValue()};
            strings2[i] = new double[]{new BigDecimal((time + i * 1000 * 60)).doubleValue(), new BigDecimal(nf.format(Math.random() * 40)).doubleValue()};
            strings3[i] = new double[]{new BigDecimal((time + i * 1000 * 60)).doubleValue(), new BigDecimal(nf.format(Math.random() * 400)).doubleValue()};
            strings4[i] = new double[]{new BigDecimal((time + i * 1000 * 60)).doubleValue(), new BigDecimal(nf.format(Math.random() * 400)).doubleValue()};
        }
        map.put("memoryUsageData", strings1);
        map.put("memoryMaxUsage", "15.66%");
        map.put("memoryMinUsage", "14.84%");
        map.put("memoryAvgUsage", "14.7%");

        map.put("cpuUsageData", strings2);
        map.put("cpuMaxUsage", "14.49%");
        map.put("cpuMinUsage", "14.42%");
        map.put("cpuAvgUsage", "14.35%");

        map.put("inflowSpeedData", strings3);
        map.put("inflowSpeedMax", "60.4MB");
        map.put("inflowSpeedAvg", "60.4MB");

        map.put("outflowSpeedData", strings4);
        map.put("outflowSpeedMax", "88.55MB");
        map.put("outflowSpeedAvg", "88.55MB");

		return map;
	}

	@Override
	public Map<String, Object> getTable() {
		List<Port> list = new ArrayList<Port>();
        for (int i = 0; i < 3; i++) {
            Port p = new Port();
            p.setId("" + i);
            p.setFlowName("FC port 8/" + (45 + i));
            p.setPhysicalState(0);
            p.setOperatingState(1);
            p.setMaxInflow((int) (Math.random() * 50 + 50));
            p.setMaxOutflow((int) (Math.random() * 50 + 50));
            p.setRealInflow((int) (Math.random() * 50));
            p.setRealOutflow((int) (Math.random() * 50));
            list.add(p);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", list);
        map.put("count", list.size());
        String[] str = new String[1];
        str[0] = "0";
        map.put("selectedRowKeys", str);
		return map;
	}

}
