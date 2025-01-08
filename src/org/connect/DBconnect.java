/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.connect;

import java.sql.*;

/**
 *
 * @author an
 */
public class DBconnect {

    private String URL = "jdbc:mysql://localhost:3306/parking_swing";
    private String USER = "root";
    private String PASSWORD = "";
    private Connection con;
    
    public DBconnect(String URL, String USER, String PASSWORD) {
        this.URL = URL;
        this.USER = USER;
        this.PASSWORD = PASSWORD;

    }

    public void connect() throws SQLException {
        if (con == null || con.isClosed()) {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        }
    }

    public void disconnect() throws SQLException {
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }

    public Connection getConnection() {
        return con;
    }
}
