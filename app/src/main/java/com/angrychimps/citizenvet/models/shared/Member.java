package com.angrychimps.citizenvet.models.shared;

/*
    Used in Member API, for both send and receive
 */
public class Member {
    private String id; //receive only
    private String first;
    private String last;
    private String title;
    private String email;
    private String password; //send only
    private String photo;

    public Member() {
    }

    public Member(String first, String last, String title, String email, String password) {
        this(first, last, title, email, password, null);
    }

    public Member(String first, String last, String title, String email, String password, String photo) {
        this.first = first;
        this.last = last;
        this.title = title;
        this.email = email;
        this.password = password;
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
