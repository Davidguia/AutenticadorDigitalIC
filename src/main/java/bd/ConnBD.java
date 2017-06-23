/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 *
 */
public class ConnBD {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/brum?useTimezone=true&serverTimezone=UTC","helder","ufmt2017");
            
            return con;
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Classe n?o encontrada: "+ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Erro: "+ex.getMessage());
        }
        
        
        return null;
        
    }      
}
