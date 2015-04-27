package com.angrychimps.appname.menu;

public class NavDrawerItem {

	private final String itemName;
    private final int layoutID; //Identifies which xml layout to use
    private String messages;
    private String email;
	private int imageID;
    private Integer imageBackground; //Image background in the profile area
    private boolean blue; //Does this row have a blue stripe along the side?
    private boolean indented;

    //email item
    public NavDrawerItem(int imagePerson, String itemName, String email, Integer imageBackground, int layoutID){
        imageID = imagePerson;
        this.itemName = itemName;
        this.email = email;
        this.imageBackground = imageBackground;
        this.layoutID = layoutID;
    }

    //messages item
    public NavDrawerItem(int imageID, String itemName, String messages, int layoutID){
        this.imageID = imageID;
        this.itemName = itemName;
        this.messages = messages;
        this.layoutID = layoutID;
    }

    //providerMode switch
    public NavDrawerItem(String itemName, int layoutID){
        this.itemName = itemName;
        this.layoutID = layoutID;
    }

    //normal item
    public NavDrawerItem(int imageID, String itemName, boolean indented, boolean blue, int layoutID){
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

    public Integer getImageBackground() {
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
