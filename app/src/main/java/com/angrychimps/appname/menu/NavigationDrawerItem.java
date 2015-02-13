package com.angrychimps.appname.menu;

public class NavigationDrawerItem {

	private String itemName;
    private String messages;
    private String email;
	private int imageID;
    private int imageBackground;
    private boolean blue;
    private boolean indented;
    private int layoutID;

    //email item
    public NavigationDrawerItem(int imagePerson, String itemName, String email, int imageBackground, int layoutID){
        imageID = imagePerson;
        this.itemName = itemName;
        this.email = email;
        this.imageBackground = imageBackground;
        this.layoutID = layoutID;
    }

    //messages item
    public NavigationDrawerItem(int imageID, String itemName, String messages, int layoutID){
        this.imageID = imageID;
        this.itemName = itemName;
        this.messages = messages;
        this.layoutID = layoutID;
    }

    //providerMode switch
    public NavigationDrawerItem(String itemName, int layoutID){
        this.itemName = itemName;
        this.layoutID = layoutID;
    }

    //normal item
    public NavigationDrawerItem(int imageID, String itemName, boolean indented, boolean blue, int layoutID){
        this.imageID = imageID;
        this.itemName = itemName;
        this.indented = indented;
        this.blue = blue;
        this.layoutID = layoutID;
    }

    public String getItemName() {
        return itemName;
    }

    public int getImageID() {
        return imageID;
    }

    public String getEmail() {
        return email;
    }

    public int getImageBackground() {
        return imageBackground;
    }

    public String getMessages() {
        return messages;
    }

    public boolean isIndented() {
        return indented;
    }

    public boolean isBlue() {
        return blue;
    }

    public int getLayoutID() {
        return layoutID;
    }
}
