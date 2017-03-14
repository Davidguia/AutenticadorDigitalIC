/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Professor;
import java.util.List;
import java.sql.Connection;
import bean.UseBCrypt;

/**
 *
 * @author Estudos
 */
public class ProfessorBD {
    
    public void inserirProfessor(Professor objProfessor) throws  SQLException{
         
        try {
            PreparedStatement pstm;
            Connection con = ConnBD.getConnection();

            con.setAutoCommit(false);
            pstm = con.prepareStatement("INSERT INTO pessoa (tipo, nome,telefone,cpf,siape,email,senha) VALUES (?, ?, ?, ?, ?, ?, ?)");
            pstm.setInt(1, objProfessor.getTipo());
            pstm.setString(2, objProfessor.getNome());
            pstm.setString(3, objProfessor.getTelefone());
            pstm.setString(4, objProfessor.getCpf());
            pstm.setString(5, objProfessor.getSiape());
            pstm.setString(6, objProfessor.getEmail());
            pstm.setString(7, UseBCrypt.hashPassword(objProfessor.getSenha()));
            pstm.executeUpdate();
            
            
            con.commit();
            
    
    }catch (SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    public void removerProfessor(Professor objProfessor) throws  SQLException{
        String sql = "DELETE FROM pessoa WHERE id = ?";
        PreparedStatement ps = ConnBD.getConnection().prepareStatement(sql);
        ps.setInt(1, objProfessor.getId());
        ps.execute();
        ps.close();
        ConnBD.getConnection().close();
     }
    
    public void alterarProfessor(Professor objProfessor) throws  SQLException{
        String sql = "UPDATE pessoa SET tipo = ?, nome = ?, telefone = ?, cpf = ?, siape = ? email = ?, senha = ? WHERE id = ?";
        PreparedStatement ps = ConnBD.getConnection().prepareStatement(sql);
        ps.setInt(1, objProfessor.getTipo());
        ps.setString(2, objProfessor.getNome());
        ps.setString(3, objProfessor.getTelefone());
        ps.setString(4, objProfessor.getCpf());
        ps.setString(5, objProfessor.getSiape());
        ps.setString(6, objProfessor.getEmail());
        ps.setString(7, objProfessor.getSenha());
        ps.setInt(8, objProfessor.getId());
        ps.execute();
        ps.close();
        ConnBD.getConnection().close();
    }
    public List<Professor> listarProfessor() throws  SQLException{
    List<Professor> lstProfessor = new ArrayList<Professor>();       
    String sql = "SELECT * FROM pessoa";
    PreparedStatement ps = ConnBD.getConnection().prepareStatement(sql);
    ResultSet rs = ps.executeQuery();
    while(rs.next()){
        Professor objProfessor = new Professor();
        objProfessor.setId(rs.getInt("id"));
        objProfessor.setId(rs.getInt("tipo"));
        objProfessor.setNome(rs.getString("nome"));
        objProfessor.setTelefone(rs.getString("telefone"));
        objProfessor.setCpf(rs.getString("cpf"));
        objProfessor.setSiape(rs.getString("siape"));
        objProfessor.setEmail(rs.getString("email"));
        objProfessor.setSenha(rs.getString("senha"));
        lstProfessor.add(objProfessor);
    }
    
    ps.close();
    ConnBD.getConnection().close();
    return lstProfessor;
    }
    
     public List<Professor> listarUsuario(int id) throws  SQLException{
    List<Professor> lstProfessor = new ArrayList<Professor>();  
     
    String sql = "SELECT * FROM pessoa where id = ?";
    PreparedStatement ps = ConnBD.getConnection().prepareStatement(sql);
    ps.setInt(1, id);
    ResultSet rs = ps.executeQuery();
   
         
    while(rs.next()){
        Professor objProfessor = new Professor();
        objProfessor.setId(rs.getInt("id"));
        objProfessor.setId(rs.getInt("tipo"));
        objProfessor.setNome(rs.getString("nome"));
        objProfessor.setTelefone(rs.getString("telefone"));
        objProfessor.setCpf(rs.getString("cpf"));
        objProfessor.setSiape(rs.getString("siape"));
        objProfessor.setEmail(rs.getString("email"));
        objProfessor.setSenha(rs.getString("senha"));
        lstProfessor.add(objProfessor);
    }
    
    ps.close();
    ConnBD.getConnection().close();
    return lstProfessor;
    }

   
  
    
}
