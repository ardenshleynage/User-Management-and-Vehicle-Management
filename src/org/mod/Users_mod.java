/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.mod;

/**
 *
 * @author an
 */
public class Users_mod {

    private Integer id;
    private String l_name;
    private String f_name;
    private String username;
    private String email;
    private String password;
    private Integer role;
    private String last_login;
    private Integer is_active;

    public Users_mod(Integer id, String l_name, String f_name, String username, String email, String password, Integer role, String last_login, Integer is_active) {
        this.id = id;
        this.l_name = l_name;
        this.f_name = f_name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.last_login = last_login;
        this.is_active = is_active;
    }

    public Integer getId() {
        return id;
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

    public String getPassword() {
        return password;
    }

    public Integer getRole() {
        return role;
    }

    public String getLast_login() {
        return last_login;
    }

    public Integer getIs_active() {
        return is_active;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    public void setIs_active(Integer is_active) {
        this.is_active = is_active;
    }

}
