package com.example.trytry;

public class Item {

    public String city;
    public String id;
    public String adress;
    public String num;
    public String phone;

    public Item() {
    }

    public Item(String city, String id, String adress, String num, String phone) {
        this.city = city;
        this.id = id;
        this.phone=phone;
        this.adress = adress;
        this.num = num;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getCity() {
        return city;
    }


    public void setCity(String city) {
        this.city = city;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getAdress() {
        return adress;
    }


    public void setAdress(String adress) {
        this.adress = adress;
    }


    public String getNum() {
        return num;
    }


    public void setNum(String num) {
        this.num = num;
    }
}
