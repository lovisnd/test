package com.tiankui.reactService.entity;

import java.util.List;

public class EquipType {
    private String id;//设备类型id
    private String title;//设备类型名
    private String key;//设备类型key
    private boolean isLeaf;//是否为叶子节点
    private String icon;//设备类型图标
    private String iconColor;//设备类型图标
    private List<Equip> children;//设备列表

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconColor() {
		return iconColor;
	}

	public void setIconColor(String iconColor) {
		this.iconColor = iconColor;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public List<Equip> getChildren() {
        return children;
    }

    public void setChildren(List<Equip> children) {
        this.children = children;
    }
}
