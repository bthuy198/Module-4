package com.example.springuploadfile.model;

import org.springframework.web.multipart.MultipartFile;

public class ProductForm {
    private long id;
    private String name;
    private String description;
    private MultipartFile image;

    public ProductForm() {
    }

    public ProductForm(long id, String name, String description, MultipartFile image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
