package com.example.courtpool.objects;

public class Court {

    String name;
    String address;

    public Court() {

    }

    public Court(String _name, String _address) {
        this.name = _name;
        this.address = _address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
