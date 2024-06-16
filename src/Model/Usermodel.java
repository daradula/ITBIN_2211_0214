/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Usermodel {

    private static final Map<String, Integer> categoryMap = new HashMap<>();
    static {
        categoryMap.put("Electronics", 1);
        categoryMap.put("Computers", 2);
        categoryMap.put("Audio", 3);
        categoryMap.put("Wearable Tech", 4);
        categoryMap.put("Photography", 5);
    }

    public static void addtocart(JTable tblData, int rowIndex) {
        DefaultTableModel model = (DefaultTableModel) tblData.getModel();

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "1234");

            String itemName = (String) model.getValueAt(rowIndex, 0);
            String description = (String) model.getValueAt(rowIndex, 1);
            double price = (double) model.getValueAt(rowIndex, 2);
            int stockQuantity = (int) model.getValueAt(rowIndex, 3);
            String categoryName = (String) model.getValueAt(rowIndex, 4);

            int categoryId = getCategoryId(categoryName);

            String insertQuery = "INSERT INTO cart (item_name, description, price, stock_quantity, category_id) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement pst = conn.prepareStatement(insertQuery);
            pst.setString(1, itemName);
            pst.setString(2, description);
            pst.setDouble(3, price);
            pst.setInt(4, stockQuantity);
            pst.setInt(5, categoryId);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Row added to cart successfully.");
                JOptionPane.showMessageDialog(null, "Item added to cart");
            } else {
                System.out.println("Failed to add row to cart.");
                JOptionPane.showMessageDialog(null, "Failed to add item to cart");
            }

            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage());
        }
    }

    private static int getCategoryId(String categoryName) {
        Integer categoryId = categoryMap.get(categoryName);
        if (categoryId == null) {
            throw new IllegalArgumentException("Unknown category name: " + categoryName);
        }
        return categoryId;
    }
    
    public static void updategalleryuser(JTable tblData){
    System.out.println("Updating seller gallery");

    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "1234");
        
        String query = "SELECT cart.item_name AS ItemName, cart.description AS Description, cart.price AS Price, cart.stock_quantity AS StockQuantity, categories.category_name AS CategoryName " +
                       "FROM cart " +
                       "LEFT JOIN categories ON cart.category_id = categories.category_id";
        
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
    
    public static void deletegalleryseller(JTable tblData, int rowIndex) {
    DefaultTableModel model = (DefaultTableModel) tblData.getModel();

    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "1234");

        String itemName = (String) model.getValueAt(rowIndex, 0);
        
        String deleteQuery = "DELETE FROM cart WHERE item_name = ?";

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
}