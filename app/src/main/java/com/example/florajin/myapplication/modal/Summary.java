package com.example.florajin.myapplication.modal;

import java.util.List;

public class Summary {
    private String category;
    private List<String> subcategory;

    public Summary(String category, List<String> subcategory) {
        this.category = category;
        this.subcategory = subcategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(List<String> subcategory) {
        this.subcategory = subcategory;
    }
}
