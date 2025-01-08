/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.mod;

/**
 *
 * @author an
 */
public class Vehicles_mod {

    private Integer id;
    private String brand;
    private String model;
    private String plate;
    private Integer year;
    private String image;
    private Long price;
    private Long loan_price;
    private Integer Status;

    public Vehicles_mod(Integer id, String brand, String model, String plate, Integer year, String image, Long price, Long loan_price, Integer Status) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.plate = plate;
        this.year = year;
        this.image = image;
        this.price = price;
        this.loan_price = loan_price;
        this.Status = Status;
    }

    public Integer getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getPlate() {
        return plate;
    }

    public Integer getYear() {
        return year;
    }

    public String getImage() {
        return image;
    }

    public Long getPrice() {
        return price;
    }

    public Long getLoan_price() {
        return loan_price;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setLoan_price(Long loan_price) {
        this.loan_price = loan_price;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }

}
