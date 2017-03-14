/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import bean.UseBCrypt;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Outros;
import java.util.List;
import java.sql.Connection;

/**
 *
 * @author Estudos
 */
public class OutrosBD {
    
    public void inserirOutros(Outros objOutros) throws  SQLException{
         
        try {
            PreparedStatement pstm;
            Connection con = ConnBD.getConnection();
          
            con.setAutoCommit(false);
            pstm = con.prepareStatement("INSERT INTO pessoa (tipo,nome,telefone,cpf,email,senha) VALUES (? ,?, ?, ?, ?, ?)");
            pstm.setInt(1, objOutros.getTipo());
            pstm.setString(2, objOutros.getNome());
            pstm.setString(3, objOutros.getTelefone());
            pstm.setString(4, objOutros.getCpf());
            pstm.setString(5, objOutros.getEmail());
            pstm.setString(6, UseBCrypt.hashPassword(objOutros.getSenha()));
            pstm.executeUpdate();
            
            
            con.commit();
            
    
    }catch (SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    public void removerOutros(Outros objOutros) throws  SQLException{
        String sql = "DELETE FROM pessoa WHERE id = ?";
        PreparedStatement ps = ConnBD.getConnection().prepareStatement(sql);
        ps.setInt(1, objOutros.getId());
        ps.execute();
        ps.close();
        ConnBD.getConnection().close();
     }
    
    public void alterarOutros(Outros objOutros) throws  SQLException{
        String sql = "UPDATE pessoa SET tipo = ?, nome = ?, telefone = ?, cpf = ?, email = ?, senha = ? WHERE id = ?";
        PreparedStatement ps = ConnBD.getConnection().prepareStatement(sql);
        ps.setInt(1, objOutros.getTipo());
        ps.setString(2, objOutros.getNome());
        ps.setString(3, objOutros.getTelefone());
        ps.setString(4, objOutros.getCpf());
        ps.setString(5, objOutros.getEmail());
        ps.setString(6, objOutros.getSenha());
        ps.setInt(7, objOutros.getId());
        ps.execute();
        ps.close();
        ConnBD.getConnection().close();
    }
    public List<Outros> listarOutros() throws  SQLException{
    List<Outros> lstOutros = new ArrayList<Outros>();       
    String sql = "SELECT * FROM pessoa";
    PreparedStatement ps = ConnBD.getConnection().prepareStatement(sql);
    ResultSet rs = ps.executeQuery();
    while(rs.next()){
        Outros objOutros = new Outros();
        objOutros.setId(rs.getInt("id"));
        objOutros.setId(rs.getInt("tipo"));
        objOutros.setNome(rs.getString("nome"));
        objOutros.setTelefone(rs.getString("telefone"));
        objOutros.setCpf(rs.getString("cpf"));
        objOutros.setEmail(rs.getString("email"));
        objOutros.setSenha(rs.getString("senha"));
        lstOutros.add(objOutros);
    }
    
    ps.close();
    ConnBD.getConnection().close();
    return lstOutros;
    }
    
     public List<Outros> listarUsuario(int id) throws  SQLException{
    List<Outros> lstOutros = new ArrayList<Outros>();  
     
    String sql = "SELECT * FROM pessoa where id = ?";
    PreparedStatement ps = ConnBD.getConnection().prepareStatement(sql);
    ps.setInt(1, id);
    ResultSet rs = ps.executeQuery();
   
         
    while(rs.next()){
        Outros objOutros = new Outros();
        objOutros.setId(rs.getInt("id"));
        objOutros.setId(rs.getInt("tipo"));
        objOutros.setNome(rs.getString("nome"));
        objOutros.setTelefone(rs.getString("telefone"));
        objOutros.setCpf(rs.getString("cpf"));
        objOutros.setEmail(rs.getString("email"));
        objOutros.setSenha(rs.getString("senha"));
        lstOutros.add(objOutros);
    }
    
    ps.close();
    ConnBD.getConnection().close();
    return lstOutros;
    }

   
  
    
}
