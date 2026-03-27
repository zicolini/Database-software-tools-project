/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zd200160d
 */
public class zd200160_CourierRequestOperation implements rs.etf.sab.operations.CourierRequestOperation{

    Connection conn = DB.getInstance().getConnection();
    
    @Override
    public boolean insertCourierRequest(String korIme, String regBr) {
        String sql  = "insert into Zahtev(KorIme, RegBr) values (?, ?)";
            try ( PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, korIme);
                ps.setString(2, regBr);
                ResultSet rs = ps.executeQuery();
                //ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return true;
                }
            } catch (SQLException ex) {
                return false;
                //Logger.getLogger(zd200160_CourierRequestOperation.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
    }

    @Override
    public boolean deleteCourierRequest(String string) {
        try (PreparedStatement ps = conn.prepareStatement("delete Zahtev where KorIme = ?")){
                ps.setString(1, string);
                int rv = ps.executeUpdate();
                return (rv != 0);

            } catch (SQLException ex) {
                Logger.getLogger(zd200160_UserOperations.class.getName()).log(Level.SEVERE, null, ex);
            }
        return false;}

    @Override
    public boolean changeVehicleInCourierRequest(String korIme, String regBr) {
       String sql = "select * from Zahtev where RegBr = ? ";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, korIme);
            ResultSet rs = ps.executeQuery();
         
            //ResultSet rs = ps.getGeneratedKeys();
            if (!rs.next()) {
                System.out.println("nema");
                //System.out.println(rs.getInt(1));
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(zd200160_UserOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sql  = "update Zahtev set RegBr = ? where KorIme = ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, regBr);
            ps.setString(2, korIme);
            int rv = ps.executeUpdate();
            //ResultSet rs = ps.getGeneratedKeys();
            //System.out.println(rv);
            //System.out.println("uneto!");
            return rv != 0;
        } catch (SQLException ex) {
            Logger.getLogger(zd200160_UserOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;}

    @Override
    public List<String> getAllCourierRequests() {
        List<String> list = new ArrayList<>();
        String sql  = "select KorIme from Zahtev";
        try {
            Statement stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.add(rs.getString(1));
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(zd200160_CourierRequestOperation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public boolean grantRequest(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
