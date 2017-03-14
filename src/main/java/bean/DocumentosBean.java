/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Documentos;
import bd.DocumentosBD;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import model.Pessoa;

@ManagedBean
@SessionScoped
public class DocumentosBean implements Serializable{
    
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;
      @ManagedProperty(value = "#{fileUploadBean}")
    private FileUploadBean fileUploadBean;
    
    private Documentos objDocumentos;
    private DocumentosBD objDocumentosBD;

    public Documentos getObjDocumentos() {
        return objDocumentos;
    }

    public void setObjDocumentos(Documentos objDocumentos) {
        this.objDocumentos = objDocumentos;
    }

    public DocumentosBD getObjDocumentosBD() {
        return objDocumentosBD;
    }

    public void setObjDocumentosBD(DocumentosBD objDocumentosBD) {
        this.objDocumentosBD = objDocumentosBD;
    }
    
    public DocumentosBean(){
        this.objDocumentos = new Documentos();
        this.objDocumentosBD = new DocumentosBD();
    }
    public String salvaDocumentos() throws SQLException {
        
            try {
                this.objDocumentosBD.inserirDocumentos(fileUploadBean.getName(), fileUploadBean.getUrl(), loginBean.login.getId());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!", "OS DADOS FORAM INSERIDOS COM SUCESSO!"));
            } catch (SQLException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "FRACASSO!", "OS DADOS FORAM NAO INSERIDOS!"));
                e.printStackTrace();
            }

        

        return "mostra";
    }
    
    public String removerDocumentos(Documentos documento) throws SQLException {
        try {
            this.objDocumentosBD.removerDocumentos(documento);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!", "OS DADOS FORAM REMOVIDOS COM SUCESSO!"));
        } catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "FRACASSO!", "OS DADOS FORAM NAO INSERIDOS!"));
            e.printStackTrace();
        }
        this.objDocumentos = new Documentos();
        return "mostra";
    }
    
    public List<Documentos> getListarDocumentos(){
        List<Documentos> lstDocumentos = new ArrayList<Documentos>(); 
        try{
            System.out.println("o valor do id do login no momento eh: "+loginBean.login.getId());
            lstDocumentos = objDocumentosBD.listarDocumentos(loginBean.login.getId());
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!", "OS DADOS FORAM LISTADOS COM SUCESSO!"));
        }catch (SQLException e) {
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "FRACASSO!", "OS DADOS FORAM NAO LISTADOS!"));
            e.printStackTrace();
            
        }
        return lstDocumentos;
    }
    public String preparaEditar(Documentos documento){
        this.objDocumentos = documento;
        return  "mostra";
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public void setFileUploadBean(FileUploadBean fileUploadBean) {
        this.fileUploadBean = fileUploadBean;
    }
    
}
