/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Student;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zd200160d
 */
public class zd200160_GeneralOperations implements rs.etf.sab.operations.GeneralOperations{
    
    Connection conn = DB.getInstance().getConnection();
    
    @Override
    public void eraseAll() {
        //System.out.println("Student.zd200160_GeneralOperations.eraseAll()");
        String sql  = "delete from Opstina;"+
                      "DBCC CHECKIDENT (Opstina, RESEED, 0)"+
                      "delete from Grad;"+
                      "DBCC CHECKIDENT (Grad, RESEED, 0)"+
                      "delete from Administrator;"+
                      //"DBCC CHECKIDENT (Administrator, RESEED, 0)"+
                      "delete from Korisnik;"+
                      //"DBCC CHECKIDENT (Korisnik, RESEED, 0)"+
                      "delete from Vozilo;"+
                      //"DBCC CHECKIDENT (Vozilo, RESEED, 0)"+
                      "delete from Kurir;"+
                      //"DBCC CHECKIDENT (Kurir, RESEED, 0)"+
                      "delete from Zahtev;"+
                      //"DBCC CHECKIDENT (Zahtev, RESEED, 0)"+
                      "delete from Voznja;"+
                      //"DBCC CHECKIDENT (Voznja, RESEED, 0)"+
                      "delete from Ponuda;"+
                      //"DBCC CHECKIDENT (Ponuda, RESEED, 0)"+
                      "delete from Paket";
                      //"DBCC CHECKIDENT (Paket, RESEED, 0)";
                      
        try {
            Statement stmt = conn.createStatement();
            
            stmt.executeUpdate(sql);
   
        } catch (SQLException ex) {
            Logger.getLogger(zd20060_CityOperations.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
}
