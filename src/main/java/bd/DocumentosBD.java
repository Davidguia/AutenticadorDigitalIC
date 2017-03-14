/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import bean.FileUploadBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Documentos;
import bean.LoginBean;
import bean.NavigationBean;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author 004820350
 */
public class DocumentosBD {
  
    public void inserirDocumentos(String name, String url, int id) throws  SQLException{
         
        try {
            PreparedStatement pstm;
            Connection con = ConnBD.getConnection();

            con.setAutoCommit(false);
            pstm = con.prepareStatement("INSERT INTO imagens (nome,url,id_pessoa) VALUES (?,?,?)");
            pstm.setString(1, name);
            pstm.setString(2, url);
            pstm.setInt(3, id);
            
            pstm.executeUpdate();
            con.commit();
            
    
    }catch (SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
        
   
     public void removerDocumentos(Documentos objDocumentos) throws  SQLException{
        
        String sql = "DELETE FROM imagens WHERE id = ?";
        PreparedStatement ps = ConnBD.getConnection().prepareStatement(sql);
        ps.setInt(1, objDocumentos.getId());
        ps.execute();
        ps.close();
        ConnBD.getConnection().close();
     }
    
    public void alterarDocumentos(Documentos objDocumentos) throws  SQLException{
        String sql = "UPDATE imagens SET nome = ?, url = ? WHERE id = ?";
        PreparedStatement ps = ConnBD.getConnection().prepareStatement(sql);
        ps.setString(1, objDocumentos.getNome());
        ps.setString(2, objDocumentos.getUrl());
        ps.setInt(6, objDocumentos.getId());
        ps.execute();
        ps.close();
        ConnBD.getConnection().close();
          }

        
    
    public List<Documentos> listarDocumentos(int id) throws  SQLException{
    List<Documentos> lstDocumentos = new ArrayList<>();       
    String sql = "SELECT * FROM imagens where id_pessoa = ?";
        try (PreparedStatement ps = ConnBD.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Documentos objDocumentos = new Documentos();
                objDocumentos.setId(rs.getInt("id"));
                objDocumentos.setNome(rs.getString("nome"));
                objDocumentos.setId_pessoa(rs.getInt("id_pessoa"));
                objDocumentos.setUrl(rs.getString("url"));
                lstDocumentos.add(objDocumentos);
            }   }
    ConnBD.getConnection().close();
    return lstDocumentos;
    }
    
}
