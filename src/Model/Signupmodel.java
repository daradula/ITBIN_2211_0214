/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Signupmodel {
    public static void adduser(String username, String email, String password, String userType){
    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "1234");

        String query = "INSERT INTO user (username, email, password, user_type) VALUES (?, ?, ?, ?)";
        pstmt = conn.prepareStatement(query);
        pstmt.setString(1, username);
        pstmt.setString(2, email);
        pstmt.setString(3, password);
        pstmt.setString(4, userType);

        int rowsInserted = pstmt.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new user was registered successfully.");
            JOptionPane.showMessageDialog(null, "A new user was registered successfully.");
        }
    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
        JOptionPane.showMessageDialog(null, "Error");
    } finally {
        try {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            System.out.println("Error closing resources: " + ex.getMessage());
        }
    }
    }
}
