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
public class zd200160_UserOperations implements rs.etf.sab.operations.UserOperations{

    Connection conn = DB.getInstance().getConnection();
    
    @Override
    public boolean insertUser(String korIme, String ime, String prezime, String pass) {
        
        //System.out.println(korIme + " " + ime + " " +prezime + " " + pass + " ");
        
        boolean b1 = Character.isLowerCase(ime.charAt(0));
        boolean b2 = Character.isLowerCase(prezime.charAt(0));
        boolean b3 = (pass.length() >8);
        if(b1 | b2 | !b3) return false;
        
        String sql = "select * from Korisnik where KorIme = ? ";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, korIme);
            ResultSet rs = ps.executeQuery();
            //ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                //System.out.println("fail");
                //System.out.println(rs.getInt(1));
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(zd200160_UserOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sql  = "insert into Korisnik(KorIme, Ime, Prezime, Sifra) values (?, ?, ?, ?)";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, korIme);
            ps.setString(2, ime);
            ps.setString(3, prezime);
            ps.setString(4, pass);
            int rv = ps.executeUpdate();
            //ResultSet rs = ps.getGeneratedKeys();
            //System.out.println(rv);
            return rv != 0;
        } catch (SQLException ex) {
            Logger.getLogger(zd200160_UserOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        }

    @Override
    public int declareAdmin(String korIme) {
        
        //System.out.println("make admin: " + korIme);
        String sql = "select * from Korisnik where KorIme = ? ";
        
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, korIme);
            ResultSet rs = ps.executeQuery();
         
            //ResultSet rs = ps.getGeneratedKeys();
            if (!rs.next()) {
                //System.out.println("fail 2");
                //System.out.println(rs.getInt(1));
                return 2;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(zd200160_UserOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sql = "select * from Administrator where KorIme = ? ";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, korIme);
            ResultSet rs = ps.executeQuery();
         
            //ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                //System.out.println("fail 1");
                //System.out.println(rs.getInt(1));
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(zd200160_UserOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sql  = "insert into Administrator(KorIme) values (?)";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, korIme);
            ps.executeUpdate();
            //ResultSet rs = ps.getGeneratedKeys();
            //System.out.println(rv);
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(zd200160_UserOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 2;
    }

    @Override
    public Integer getSentPackages(String... strings) {
        
        int retval = 0;
        for (String str : strings){
            try (PreparedStatement ps = conn.prepareStatement("select * from Korisnik where KorIme = ?")){
                ps.setString(1, str);
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                    retval++;

            } catch (SQLException ex) {
                Logger.getLogger(zd200160_UserOperations.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (retval == 0) return null;
        else retval = 0;
        
        String sql  = "select count(*) from Paket where KorImeZahtevaoc =  ? group by KorImeZahtevaoc";
        for (String str : strings){
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, str);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
                
                retval += rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(zd200160_UserOperations.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        }
        return retval;
    }

    @Override
    public int deleteUsers(String... strings) {
       int retval = 0;
        for (String str : strings){
            try (PreparedStatement ps = conn.prepareStatement("delete Korisnik where KorIme = ?")){
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
    public List<String> getAllUsers() {
        List<String> list = new ArrayList<>();
        String sql  = "select KorIme from Korisnik";
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
    
    
}
