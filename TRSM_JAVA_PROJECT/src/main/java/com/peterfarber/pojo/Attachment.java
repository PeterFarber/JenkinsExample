package com.peterfarber.pojo;

public class Attachment {

    private int id;
    private int type;
    private String path;
    private String description;
    private int application_ID;

    public Attachment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getApplication_ID() {
        return application_ID;
    }

    public void setApplication_ID(int application_ID) {
        this.application_ID = application_ID;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", type=" + type +
                ", path='" + path + '\'' +
                ", description='" + description + '\'' +
                ", application_ID=" + application_ID +
                '}';
    }
}
