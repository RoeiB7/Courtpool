package com.example.courtpool.objects;

public class Upload {

    String name;
    String imageUrl;

    public Upload() {

    }

    public Upload(String _name, String _imageUrl) {
        if (_name.trim().equals("")) {
            _name = "No name";
        }

        name = _name;
        imageUrl = _imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
