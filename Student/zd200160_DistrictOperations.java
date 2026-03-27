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
public class zd200160_DistrictOperations implements rs.etf.sab.operations.DistrictOperations{

    Connection conn = DB.getInstance().getConnection();
    
    @Override
    public int insertDistrict(String naziv, int idG, int x, int y) {
        //System.out.println(naziv + " " + idG + " " + x + " " + y + " ");
        
        String sql = "select * from Grad where IdGrad = ? ";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idG);
            ResultSet rs = ps.executeQuery();
         
            //ResultSet rs = ps.getGeneratedKeys();
            if (!rs.next()) {
                //System.out.println("***" + rs.getInt(1)+ "***");
                return -1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(zd20060_CityOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sql  = "insert into Opstina(Naziv, X, Y, IdGrad) values (?, ?, ?, ?)";
        try ( PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, naziv);
            ps.setInt(2, x);
            ps.setInt(3, y);
            ps.setInt(4, idG);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                //System.out.println("retval " + rs.getInt(1));
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(zd20060_CityOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public int deleteDistricts(String... strings) {
        int retval = 0;
        for (String str : strings){
            try (PreparedStatement ps = conn.prepareStatement("delete Opstina where Naziv = ?")){
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
    public boolean deleteDistrict(int id) {
       String sql  = "delete from Opstina where IdOpstina = ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rv = ps.executeUpdate();
            return rv != 0;
        } catch (SQLException ex) {
            Logger.getLogger(zd20060_CityOperations.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }}

    @Override
    public int deleteAllDistrictsFromCity(String grad) {
        String sql  = "delete from Opstina where IdGrad = (select IdGrad from Grad where Naziv = ?)";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, grad);
            int rv = ps.executeUpdate();
            return rv;
        } catch (SQLException ex) {
            Logger.getLogger(zd20060_CityOperations.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public List<Integer> getAllDistrictsFromCity(int idG) {
            List<Integer> list = new ArrayList<>();
            String sql  = "select IdOpstina from Opstina where idGrad = ?";
            try ( PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, idG);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(zd20060_CityOperations.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<Integer> getAllDistricts() {
        List<Integer> list = new ArrayList<>();
        String sql  = "select IdOpstina from Opstina";
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
