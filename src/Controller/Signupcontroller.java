/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Signupmodel;

public class Signupcontroller {
    public static void addnewuser(String user, String email, String password, String usertype){
        Signupmodel.adduser(user, email, password, usertype);
    }
    
}
