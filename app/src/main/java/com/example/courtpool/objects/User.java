package com.example.courtpool.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {

    private String name;
    private String email;
    private String password;
    private String phone;
    private ArrayList<String> courtLocation = new ArrayList<>();
    private ArrayList<String> courtTypes = new ArrayList<>();
    private String skill;
    private Map<String, ArrayList<String>> playTime = new HashMap<>();


    public User() {

    }

    public User(String name, String email, String password, String phone, ArrayList<String> courtLocation, ArrayList<String> courtTypes, String skill, Map<String, ArrayList<String>> playTime) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.courtLocation = courtLocation;
        this.courtTypes = courtTypes;
        this.skill = skill;
        this.playTime = playTime;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<String> getCourtLocation() {
        return courtLocation;
    }

    public void setCourtLocation(ArrayList<String> courtLocation) {
        this.courtLocation = courtLocation;
    }

    public ArrayList<String> getCourtTypes() {
        return courtTypes;
    }

    public void setCourtTypes(ArrayList<String> courtTypes) {
        this.courtTypes = courtTypes;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public Map<String, ArrayList<String>> getPlayTime() {
        return playTime;
    }

    public void setPlayTime(Map<String, ArrayList<String>> playTime) {
        this.playTime = playTime;
    }
}
