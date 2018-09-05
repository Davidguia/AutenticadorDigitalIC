/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import bean.UseBCrypt;
import com.warrenstrange.googleauth.ICredentialRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Aluno;
import model.Credencial;

/**
 *
 * @author Helder
 */
public class CredencialBD implements ICredentialRepository {

    @Override
    public String getSecretKey(String userName) {
        String result = "";
        String sql = "SELECT secretkey FROM credenciais where username LIKE ?";
        PreparedStatement ps;
        try {
            ps = ConnBD.getConnection().prepareStatement(sql);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            rs.first();
            result = rs.getString("secretKey");
            ps.close();
            ConnBD.getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(CredencialBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public void saveUserCredentials(String userName, String secretKey, int validationCode, List<Integer> scratchCodes) {
        try {
            PreparedStatement pstm;
            Connection con = ConnBD.getConnection();

            con.setAutoCommit(false);
            pstm = con.prepareStatement("INSERT INTO credenciais (username, secretkey) VALUES (?, ?)");
            pstm.setString(2, userName);
            pstm.setString(3, UseBCrypt.hashPassword(secretKey));
            pstm.executeUpdate();

            con.commit();

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
