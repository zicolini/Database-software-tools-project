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
public class zd200160_VehicleOperations implements rs.etf.sab.operations.VehicleOperations{

    Connection conn  = DB.getInstance().getConnection();
    
    @Override
    public boolean insertVehicle(String reg, int tipG, BigDecimal potrosnja) {
       //System.out.println(reg + " " + tipG + " " + potrosnja);
       String sql = "select * from Vozilo where RegBr = ? ";
       if(tipG !=0 & tipG !=1 & tipG !=2) return false;
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, reg);
            ResultSet rs = ps.executeQuery();
         
            //ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                //System.out.println("zauzeto");
                //System.out.println(rs.getInt(1));
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(zd200160_UserOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sql  = "insert into Vozilo(RegBr, TipGoriva, Potrosnja) values (?, ?, ?)";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, reg);
            ps.setInt(2, tipG);
            ps.setBigDecimal(3, potrosnja);
            int rv = ps.executeUpdate();
            //ResultSet rs = ps.getGeneratedKeys();
            //System.out.println(rv);
            //System.out.println("uneto!");
            return rv != 0;
        } catch (SQLException ex) {
            Logger.getLogger(zd200160_UserOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        }

    
    @Override
    public int deleteVehicles(String... strings) {
        int retval = 0;
        for (String str : strings){
            try (PreparedStatement ps = conn.prepareStatement("delete Vozilo where RegBr = ?")){
                ps.setString(1, str);
                int rv = ps.executeUpdate();
                if (rv != 0)
                    retval++;

            } catch (SQLException ex) {
                Logger.getLogger(zd200160_UserOperations.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return retval; 
        
    }

    @Override
    public List<String> getAllVehichles() {
        List<String> list = new ArrayList<>();
        String sql  = "select RegBr from Vozilo";
        try {
            Statement stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.add(rs.getString(1));
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(zd200160_VehicleOperations.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public boolean changeFuelType(String reg, int tipG) {
       String sql = "select * from Vozilo where RegBr = ? ";
       if(tipG !=0 & tipG !=1 & tipG !=2) return false;
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, reg);
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
        
        sql  = "update Vozilo set TipGoriva = ? where RegBr = ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, tipG);
            ps.setString(2, reg);
            int rv = ps.executeUpdate();
            //ResultSet rs = ps.getGeneratedKeys();
            //System.out.println(rv);
            //System.out.println("uneto!");
            return rv != 0;
        } catch (SQLException ex) {
            Logger.getLogger(zd200160_UserOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean changeConsumption(String reg, BigDecimal potrosnja) {
        String sql = "select * from Vozilo where RegBr = ? ";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, reg);
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
        
        sql  = "update Vozilo set Potrosnja = ? where RegBr = ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBigDecimal(1, potrosnja);
            ps.setString(2, reg);
            int rv = ps.executeUpdate();
            //ResultSet rs = ps.getGeneratedKeys();
            //System.out.println(rv);
            //System.out.println("uneto!");
            return rv != 0;
        } catch (SQLException ex) {
            Logger.getLogger(zd200160_UserOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
