/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Sellerdashmodel;
import javax.swing.JTable;

public class Seller_dashcontroller {
    public static void updatesellergallery(JTable tblData){
        Sellerdashmodel.updategalleryseller(tblData);
    }
    
    public static void searchsellergallery(JTable tblData, String category){
        Sellerdashmodel.searchgalleryseller(tblData, category);
    }
    
    public static void deletesellergallery(JTable tblData, int row){
        Sellerdashmodel.deletegalleryseller(tblData, row);
    }
    
    public static void updatesellergallery(JTable tblData, int rowIndex, String itemName, String description, double price, int stockQuantity){
        Sellerdashmodel.updategalleryseller(tblData, rowIndex, itemName, description, price, stockQuantity);
    }
    
    public static void addsellergallery(String itemName, String description, double price, int stockQuantity, int type){
        Sellerdashmodel.addgalleryseller(itemName, description, price, stockQuantity, type);
    }
}
