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
public class zd20060_CityOperations implements rs.etf.sab.operations.CityOperations{
    
    Connection conn = DB.getInstance().getConnection();

    @Override
    public int insertCity(String naziv, String postanskiBr) {
        
        //System.out.println(naziv + " " + postanskiBr);
        
        String sql = "select IdGrad from Grad where Naziv = ? or PostanskiBr = ? ";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, naziv);
            ps.setString(2, postanskiBr);
            ResultSet rs = ps.executeQuery();
         
            //ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                //System.out.println("***" + rs.getInt(1) + "***");
                //System.out.println("citiinsert retval "+ "-1");
                return -1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(zd20060_CityOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sql  = "insert into Grad(Naziv, PostanskiBr) values (?, ?)";
        try ( PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, naziv);
            ps.setString(2, postanskiBr);
            ps.executeUpdate();
         
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                //System.out.println("citiinsert retval "+ rs.getInt(1));
                return rs.getInt(1);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(zd20060_CityOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public int deleteCity(String... nazivi) {
        int retval = 0;
        
        for (String str : nazivi){
            try (PreparedStatement ps = conn.prepareStatement("delete Grad where Naziv = ?")){
                //Array arr = conn.createArrayOf("String", nazivi);
                ps.setString(1, str);
                int rv = ps.executeUpdate();
                if (rv != 0)
                    retval++;

            } catch (SQLException ex) {
                Logger.getLogger(zd20060_CityOperations.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return retval;
    }

    @Override
    public boolean deleteCity(int id) {
        
        String sql  = "delete from Grad where IdGrad = ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rv = ps.executeUpdate();
            return rv != 0;
        } catch (SQLException ex) {
            Logger.getLogger(zd20060_CityOperations.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public List<Integer> getAllCities() {
        List<Integer> list = new ArrayList<>();
        String sql  = "select IdGrad from Grad";
        try {
            Statement stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(zd20060_CityOperations.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
