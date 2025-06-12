package com.kingaspx.capture;
import java.util.Date;
import java.text.SimpleDateFormat;

public class ModelAttendance {

    private int person_id;
    private String first_name, last_name, student_id, class_section, image;

    public ModelAttendance() {

    }

    public ModelAttendance(String first_name, String class_section, String image) { //LastPerson
        this.first_name = first_name;
        this.class_section = class_section;
        this.image = image;
    }

    public ModelAttendance(int id, String first_name, String last_name, String student_id, String class_section) {
        this.person_id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.student_id = student_id;
        this.class_section = class_section;
    }

    public int gePerson_id() {
        return person_id;
    }

    public void setPerson_id(int id) {
        this.person_id = id;
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

    public Date getTime_Entry() {
        return new Date();
    }

}
