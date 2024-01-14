package com.JAVA.Beans;

import java.util.Arrays;
import java.util.List;

public class User {
    private int id;
    private String name;
    private String password;
    private byte[] picture;
    private String email;
    private String pictureBase64;
    private List<Blog> blogs;
    private List<Comment> comments;
    private List<Alarm> alarms;
    private List<Diagnostic> diagnostics;
    
    // Constructors
    public User() {
    }

    public User(int id, String name, String password, byte[] picture ) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.picture = picture;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPictureBase64() {
        return pictureBase64;
    }

    public void setPictureBase64(String pictureBase64) {
        this.pictureBase64 = pictureBase64;
    }
    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
    // You may want to override the toString() method for debugging or logging purposes
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", picture=" + Arrays.toString(picture) +
                '}';
    }

	public List<Alarm> getAlarms() {
		return alarms;
	}

	public void setAlarms(List<Alarm> alarms) {
		this.alarms = alarms;
	}

	public List<Diagnostic> getDiagnostics() {
		return diagnostics;
	}

	public void setDiagnostics(List<Diagnostic> diagnostics) {
		this.diagnostics = diagnostics;
	}
    
}
