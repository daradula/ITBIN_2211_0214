
package Controller;

import Model.user;
import Model.Loginmodel;
import View.Login;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Logincontroller {
    public static void checkuser(String id, String password){
        
        if(password == null){
            JOptionPane.showMessageDialog(null,"Incorrect User Name or Password");
            Login newlogin = new Login();
            newlogin.setVisible(true);
        }else{
            
            Loginmodel.validateUser(id,password);
        
            
            
        }
    }

    private static void validateUser(String id, String password) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
