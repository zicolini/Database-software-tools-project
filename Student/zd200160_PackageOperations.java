/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Student;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.sql.*;

/**
 *
 * @author zd200160d
 */
public class zd200160_PackageOperations implements rs.etf.sab.operations.PackageOperations{

    Connection conn = DB.getInstance().getConnection();
    
    @Override
    public int insertPackage(int opstina1, int opstina2, String korIme, int tip, BigDecimal tezina) {
        String sql  = "insert into Paket(PolaznaOpstina, OdredisnaOpsina, KorImeZahtevaoc, TipPaketa, Tezina) values (?, ?, ?, ?, ?)";
        try ( PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, opstina1);
            ps.setInt(2, opstina2);
            ps.setString(3, korIme);
            ps.setInt(4, tip);
            ps.setBigDecimal(5, tezina);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return -1;
            //Logger.getLogger(zd200160_CourierRequestOperation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public int insertTransportOffer(String korIme, int paketId, BigDecimal proc) {
        String sql  = "insert into Ponuda(KorIme, IdPaket, Procenat, Status) values (?, ?, ?, ?)";
        try ( PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, korIme);
            ps.setInt(2, paketId);
            ps.setBigDecimal(3, proc);
            ps.setInt(4, 0);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return -1;
            //Logger.getLogger(zd200160_PackageOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public boolean acceptAnOffer(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Integer> getAllOffers() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Pair<Integer, BigDecimal>> getAllOffersForPackage(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean deletePackage(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean changeWeight(int i, BigDecimal bd) {
        String sql  = "update Paket set Tezina = ? where IdPaketa = ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBigDecimal(1, bd);
            ps.setInt(2, i);
            int rv = ps.executeUpdate();
            //ResultSet rs = ps.getGeneratedKeys();
            //System.out.println(rv);
            //System.out.println("uneto!");
            return rv != 0;
        } catch (SQLException ex) {
            Logger.getLogger(zd200160_PackageOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean changeType(int i, int i1) {
        String sql  = "update Paket set TipPaketa = ? where IdPaketa = ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, i1);
            ps.setInt(2, i);
            int rv = ps.executeUpdate();
            //ResultSet rs = ps.getGeneratedKeys();
            //System.out.println(rv);
            //System.out.println("uneto!");
            return rv != 0;
        } catch (SQLException ex) {
            return false;
            //Logger.getLogger(zd200160_PackageOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return false;
    }

    @Override
    public Integer getDeliveryStatus(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public BigDecimal getPriceOfDelivery(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Date getAcceptanceTime(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Integer> getAllPackagesWithSpecificType(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Integer> getAllPackages() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Integer> getDrive(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int driveNextPackage(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
