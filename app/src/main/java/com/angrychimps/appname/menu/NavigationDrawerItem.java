package com.angrychimps.appname.menu;

public class NavigationDrawerItem {

	private String ItemName;
	private int imgResID;
	private String title;

	public NavigationDrawerItem(String itemName, int imgResID) {
		ItemName = itemName;
		this.imgResID = imgResID;
	}

	public NavigationDrawerItem(String title) {
		this(null, 0);
		this.title = title;
	}

	public String getItemName() {
		return ItemName;
	}

	public void setItemName(String itemName) {
		ItemName = itemName;
	}

	public int getImgResID() {
		return imgResID;
	}

	public void setImgResID(int imgResID) {
		this.imgResID = imgResID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
