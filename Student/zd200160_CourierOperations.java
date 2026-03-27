/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Student;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zd200160d
 */
public class zd200160_CourierOperations implements rs.etf.sab.operations.CourierOperations{

    Connection conn = DB.getInstance().getConnection();
    @Override
    public boolean insertCourier(String korIme, String regBr) {
            String sql  = "insert into Kurir(KorIme, RegBr) values (?, ?)";
            try ( PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, korIme);
                ps.setString(2, regBr);
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(zd20060_CityOperations.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }

    @Override
    public boolean deleteCourier(String string) {
        
            try (PreparedStatement ps = conn.prepareStatement("delete Korisnik where KorIme = ?")){
                ps.setString(1, string);
                int rv = ps.executeUpdate();
                return (rv != 0);
                    

            } catch (SQLException ex) {
                Logger.getLogger(zd200160_UserOperations.class.getName()).log(Level.SEVERE, null, ex);
            }
        return false;
    }

    @Override
    public List<String> getCouriersWithStatus(int i) {
        List<String> list = new ArrayList<>();
            String sql  = "select KorIme from Kurir where Status = ?";
            try ( PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, i);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(zd20060_CityOperations.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<String> getAllCouriers() {
        List<String> list = new ArrayList<>();
        String sql  = "select KorIme from Kurir";
        try {
            Statement stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.add(rs.getString(1));
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(zd200160_UserOperations.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public BigDecimal getAverageCourierProfit(int i) {
        //System.out.println("Student.zd200160_CourierOperations.getAverageCourierProfit()");
        String sql  = "select avg(Profit) from Kurir where BrIsporucenihPaketa >= ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, i);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) 
                return rs.getBigDecimal(1);
        } catch (SQLException ex) {
            Logger.getLogger(zd20060_CityOperations.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;
    }
    
}
