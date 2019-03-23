package com.estisharat.estisharatv1;

public class info {
    String email;
    String name;
    String img;
    String bio;

    public info(String email, String name, String img, String bio) {
        this.email = email;
        this.name = name;
        this.img = img;
        this.bio = bio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}