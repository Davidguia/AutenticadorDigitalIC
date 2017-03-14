package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Aluno;
import bd.AlunoBD;
//import model.Login;
import java.io.Serializable;
import java.sql.SQLException;
import static javax.accessibility.AccessibleRole.LIST;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.ArrayList;
import javax.faces.bean.ManagedProperty;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Estudos
 */
@ManagedBean
@SessionScoped
public class AlunoBean implements Serializable{
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;

    private Aluno objAluno;
    private AlunoBD objAlunoBD;
     

    public AlunoBean() {
        this.objAluno = new Aluno();
        this.objAlunoBD = new AlunoBD();

    }
    
    public Aluno getAluno(){
        return objAluno;
    }
    
    public void setObjAluno(Aluno objAluno){
        this.objAluno = objAluno;
    }

    public String salvaAluno() throws SQLException {
        if (this.objAluno.getId() != 0) {
            try {
                this.objAlunoBD.alterarAluno(this.objAluno);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!", "OS DADOS FORAM ALTERADOS COM SUCESSO!"));
                this.objAluno = new Aluno();
                this.objAlunoBD = new AlunoBD();
                
            } catch (SQLException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "FRACASSO!", "OS DADOS FORAM NAO ALTERADOS!"));
                e.printStackTrace();
            }

            this.objAluno = new Aluno();
        } else {
            try {
                this.objAlunoBD.inserirAluno(this.objAluno);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!", "OS DADOS FORAM INSERIDOS COM SUCESSO!"));
                this.objAluno = new Aluno();
                this.objAlunoBD = new AlunoBD();
            } catch (SQLException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "FRACASSO!", "OS DADOS FORAM NAO INSERIDOS!"));
                e.printStackTrace();
            }

        }

        return "mostra";
    }

    public String removerAluno(Aluno aluno) throws SQLException {
        try {
            this.objAlunoBD.removerAluno(aluno);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!", "OS DADOS FORAM REMOVIDOS COM SUCESSO!"));
        } catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "FRACASSO!", "OS DADOS FORAM NAO INSERIDOS!"));
            e.printStackTrace();
        }
        this.objAluno = new Aluno();
        return "aluno";
    }
    
    public List<Aluno> getListarAluno(){
        List<Aluno> lstAluno = new ArrayList<Aluno>(); 
        try{
            lstAluno = objAlunoBD.listarAluno();
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!", "OS DADOS FORAM LISTADOS COM SUCESSO!"));
        }catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "FRACASSO!", "OS DADOS FORAM NAO LISTADOS!"));
            e.printStackTrace();
            
        }
        return lstAluno;
    }
    
     public List<Aluno> getListarUsuario(){
        List<Aluno> lstAluno = new ArrayList<Aluno>(); 
        try{
            lstAluno = objAlunoBD.listarUsuario(loginBean.login.getId());
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!", "OS DADOS FORAM LISTADOS COM SUCESSO!"));
        }catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "FRACASSO!", "OS DADOS FORAM NAO LISTADOS!"));
            e.printStackTrace();
            
        }
        return lstAluno;
    }
    
    public String preparaEditar(Aluno aluno){
        this.objAluno = aluno;
        return  "mostra";
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }
   
       
}
