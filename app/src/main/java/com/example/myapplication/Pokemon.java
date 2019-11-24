package com.example.myapplication;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pokemon_table")
public class Pokemon {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String imageUrl;
    private String type;
    private int weight;
    private int height;


    public Pokemon(int id, String name, String imageUrl, String type, int weight, int height) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.type= type;
        this.weight = weight;
        this.height = height;

    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getType() {
        return type;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }
}