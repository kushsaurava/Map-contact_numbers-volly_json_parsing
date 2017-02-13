package com.awsome.kush.test_demo.model;


public class ContactDATA {


    String name;
    String email;
    String phone;
    String officePhone;
    String latitude;
    String longitude;


    public ContactDATA(String name , String email, String phone, String officePhone, String latitude, String longitude) {


        this.name = name;
        this.email = email;
        this.phone = phone;
        this.officePhone=officePhone;
        this.latitude=latitude;
        this.longitude = longitude;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }



    public String getphone() {
        return phone;
    }

    public void setphone(String phone) {
        this.phone = phone;
    }

    public String getlatitude() {
        return latitude;
    }

    public void setlatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getlongitude() {
        return longitude;
    }

    public void setlongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public String getofficePhone() {
        return officePhone;
    }

    public void setofficePhone(String officePhone) {
        this.officePhone = officePhone;
    }
}

