package com.example.courtpool.objects;


public class Court {

    String name;
    String address;
    Boolean checked = false;

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

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String toString() {
        return name + '\'' + address;
    }
}
