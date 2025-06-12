package com.kingaspx.capture;

public class ModelPerson {

    private int id;
    private String first_name, last_name, student_id, class_section, image, date;

    public ModelPerson() {

    }

    public ModelPerson(String first_name, String class_section, String image) { //LastPerson
        this.first_name = first_name;
        this.class_section = class_section;
        this.image = image;
    }

    public ModelPerson(int id, String first_name, String last_name, String student_id, String class_section, String date) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.student_id = student_id;
        this.class_section = class_section;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getClass_section() {
        return this.class_section;
    }

    public void setClass_section(String class_section) {
        this.class_section = class_section;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
