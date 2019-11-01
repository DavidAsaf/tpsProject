/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author gerso
 */

public class JavaConnectBD {
    Connection conn = null;
    public static Connection ConnecBD() {
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "System", "admin");
            return con;
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
   


   
}
