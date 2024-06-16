/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Usermodel;
import javax.swing.JTable;

public class Usercontroller {

    public static void addcartllergallery(JTable jTable2, int selectedRow) {
        Usermodel.addtocart(jTable2, selectedRow);
    }
    
    public static void updatecart(JTable jTable2) {
        Usermodel.updategalleryuser(jTable2);
    }
    
    public static void deleterow(JTable jTable2, int Row) {
        Usermodel.deletegalleryseller(jTable2, Row);
    }
    
}
