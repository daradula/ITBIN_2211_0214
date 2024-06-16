/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import View.Dashboard;
import View.Dashboard_1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Sellerdashmodel {
    public static void updategalleryseller(JTable tblData){
    System.out.println("Updating seller gallery");

    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "1234");
        
        String query = "SELECT items.item_name AS ItemName, items.description AS Description, items.price AS Price, items.stock_quantity AS StockQuantity, categories.category_name AS CategoryName " +
                       "FROM items " +
                       "LEFT JOIN categories ON items.category_id = categories.category_id";
        
        PreparedStatement pst = conn.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        String[] columnNames = {"Item Name", "Description", "Price", "Stock Quantity", "Category"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        while (rs.next()) {
            String itemName = rs.getString("ItemName");
            String description = rs.getString("Description");
            double price = rs.getDouble("Price");
            int stockQuantity = rs.getInt("StockQuantity");
            String categoryName = rs.getString("CategoryName");

            model.addRow(new Object[]{itemName, description, price, stockQuantity, categoryName});
        }

        tblData.setModel(model);

        rs.close();
        pst.close();
        conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void searchgalleryseller(JTable tblData, String category){
    System.out.println("Updating seller gallery for category: " + category);

    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "1234");
        
        String query = "SELECT items.item_name AS ItemName, items.description AS Description, items.price AS Price, items.stock_quantity AS StockQuantity, categories.category_name AS CategoryName " +
                       "FROM items " +
                       "LEFT JOIN categories ON items.category_id = categories.category_id ";
        
        if (category != null && !category.isEmpty()) {
            query += "WHERE categories.category_name = ?";
        }
        
        PreparedStatement pst = conn.prepareStatement(query);
        
        if (category != null && !category.isEmpty()) {
            pst.setString(1, category);
        }

        ResultSet rs = pst.executeQuery();

        String[] columnNames = {"Item Name", "Description", "Price", "Stock Quantity", "Category"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        while (rs.next()) {
            String itemName = rs.getString("ItemName");
            String description = rs.getString("Description");
            double price = rs.getDouble("Price");
            int stockQuantity = rs.getInt("StockQuantity");
            String categoryName = rs.getString("CategoryName");

            model.addRow(new Object[]{itemName, description, price, stockQuantity, categoryName});
        }

        tblData.setModel(model);

        rs.close();
        pst.close();
        conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deletegalleryseller(JTable tblData, int rowIndex) {
    DefaultTableModel model = (DefaultTableModel) tblData.getModel();

    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "1234");

        String itemName = (String) model.getValueAt(rowIndex, 0);

        String deleteQuery = "DELETE FROM items WHERE item_name = ?";

        PreparedStatement pst = conn.prepareStatement(deleteQuery);
        pst.setString(1, itemName); 

        int rowsAffected = pst.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Row deleted successfully.");
            JOptionPane.showMessageDialog(null,"Deleted");
            model.removeRow(rowIndex);
        } else {
            System.out.println("Error");
        }

        pst.close();
        conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void updategalleryseller(JTable tblData, int rowIndex, String itemName, String description, double price, int stockQuantity) {
    DefaultTableModel model = (DefaultTableModel) tblData.getModel();

    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "1234");

        String currentName = (String) model.getValueAt(rowIndex, 0);

        String updateQuery = "UPDATE items SET item_name = ?, description = ?, price = ?, stock_quantity = ? WHERE item_name = ?";

        PreparedStatement pst = conn.prepareStatement(updateQuery);
        pst.setString(1, itemName);
        pst.setString(2, description);
        pst.setDouble(3, price);
        pst.setInt(4, stockQuantity);
        pst.setString(5, currentName); 

        int rowsAffected = pst.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Row updated successfully.");
            JOptionPane.showMessageDialog(null, "Updated Successfully");

            model.setValueAt(itemName, rowIndex, 0);
            model.setValueAt(description, rowIndex, 1);
            model.setValueAt(price, rowIndex, 2);
            model.setValueAt(stockQuantity, rowIndex, 3);
        } else {
            System.out.println("Failed to update row.");
            JOptionPane.showMessageDialog(null, "Failed to Update");
        }

        pst.close();
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
    
    public static void addgalleryseller(String itemName, String description, double price, int stockQuantity, int type) {
    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "1234");

        String insertQuery = "INSERT INTO items (item_name, description, price, stock_quantity, category_id) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement pst = conn.prepareStatement(insertQuery);
        pst.setString(1, itemName);
        pst.setString(2, description);
        pst.setDouble(3, price);
        pst.setInt(4, stockQuantity);
        pst.setInt(5, type);

        int rowsAffected = pst.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Row added successfully.");
            JOptionPane.showMessageDialog(null, "Added Successfully");
        } else {
            System.out.println("Failed to add row.");
            JOptionPane.showMessageDialog(null, "Failed to Add");
        }

        pst.close();
        conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    
}
