/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.mod;

/**
 *
 * @author an
 */
public class Basket_mod {

    private Integer id;
    private Integer id_clients;
    private String l_name;
    private String f_name;
    private String username;
    private String email;
    private Integer id_vehicles;
    private String brand;
    private String model;
    private String plate;
    private Integer year;
    private String image;
    private Long price;
    private Long loan_price;
    private String date;
    private Integer status_clients;
    private Integer status_vehicles;
    private Integer status;

    public Basket_mod(Integer id, Integer id_clients, String l_name, String f_name, String username, String email, Integer id_vehicles, String brand, String model, String plate, Integer year, String image, Long price, Long loan_price, String date, Integer status_clients, Integer status_vehicles, Integer status) {
        this.id = id;
        this.id_clients = id_clients;
        this.l_name = l_name;
        this.f_name = f_name;
        this.username = username;
        this.email = email;
        this.id_vehicles = id_vehicles;
        this.brand = brand;
        this.model = model;
        this.plate = plate;
        this.year = year;
        this.image = image;
        this.price = price;
        this.loan_price = loan_price;
        this.date = date;
        this.status_clients = status_clients;
        this.status_vehicles = status_vehicles;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public Integer getId_clients() {
        return id_clients;
    }

    public String getL_name() {
        return l_name;
    }

    public String getF_name() {
        return f_name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Integer getId_vehicles() {
        return id_vehicles;
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

    public String getDate() {
        return date;
    }

    public Integer getStatus_clients() {
        return status_clients;
    }

    public Integer getStatus_vehicles() {
        return status_vehicles;
    }

    public Integer getStatus() {
        return status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setId_clients(Integer id_clients) {
        this.id_clients = id_clients;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId_vehicles(Integer id_vehicles) {
        this.id_vehicles = id_vehicles;
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

    public void setDate(String date) {
        this.date = date;
    }

    public void setStatus_clients(Integer status_clients) {
        this.status_clients = status_clients;
    }

    public void setStatus_vehicles(Integer status_vehicles) {
        this.status_vehicles = status_vehicles;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
