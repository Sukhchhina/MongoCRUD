package com.example.mongocrud;

public class Product {

    private String name;
    private String category;
    private Integer price;
    private String manufacturer;
    private String releaseDate;


    public Product(String name, String category, Integer price, String manufacturer, String releaseDate) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.manufacturer = manufacturer;
        this.releaseDate = releaseDate;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /* Option information
    @Override
    public String toString() {
        return "Product1{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", manufacturer='" + manufacturer + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                '}';



    }
    */

}
