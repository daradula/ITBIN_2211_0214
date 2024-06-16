package Model;

import View.Dashboard;
import View.Dashboard_1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Loginmodel {
    
    public static int validateUser(String userName, String password) {
        java.lang.System.out.println("User authenticate function");
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db", "root", "1234");

            String sql = "SELECT * FROM db.user WHERE username = ? AND password = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, userName);
            pst.setString(2, password);

            rs = pst.executeQuery();
            
            if (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                String type = rs.getString(5);
                String userPassword = rs.getString(6);

                if (type.equals("user")) {
                    java.lang.System.out.println("User");
                    Dashboard mainpage = new Dashboard();
                    mainpage.setpage(mainpage);
                    
                } else {
                    java.lang.System.out.println("Seller");
                    Dashboard_1 mainpage = new Dashboard_1();
                    mainpage.setpage(mainpage);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        return 0;
    }
}
