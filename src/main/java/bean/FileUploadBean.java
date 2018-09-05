/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import bd.CredencialBD;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import java.sql.SQLException;
import javax.faces.bean.ManagedProperty;
import model.Documentos;
import bd.DocumentosBD;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorConfig;
import java.util.Scanner;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "fileUploadBean")
@SessionScoped

public class FileUploadBean implements Serializable {

    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;
    
    CredencialBD repositorio = new CredencialBD();
    GoogleAuthenticator gauth = new GoogleAuthenticator();
       
    PDFModify pdfModify = new PDFModify();
    private String name;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DocumentosBD getDocumentosBD() {
        return documentosBD;
    }

    public void setDocumentosBD(DocumentosBD documentosBD) {
        this.documentosBD = documentosBD;
    }

    DocumentosBD documentosBD = new DocumentosBD();

    private Documentos objOutros = new Documentos();
    private OutrosBean outrosBean = new OutrosBean();

    public OutrosBean getOutrosBean() {
        return outrosBean;
    }

    public void setOutrosBean(OutrosBean outrosBean) {
        this.outrosBean = outrosBean;
    }

    public Documentos getObjOutros() {
        return objOutros;
    }

    public void setObjOutros(Documentos objOutros) {
        this.objOutros = objOutros;
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private UploadedFile resume;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    private String filePath;

    public UploadedFile getResume() {
        return resume;
    }

    public void setResume(UploadedFile resume) {
        this.resume = resume;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String uploadResume() throws IOException, SQLException {
            UploadedFile uploadedPhoto = getResume();
            //System.out.println("Name " + getName());
            //System.out.println("tmp directory" System.getProperty("java.io.tmpdir"));
            //System.out.println("File Name " + uploadedPhoto.getFileName());
            //System.out.println("Size " + uploadedPhoto.getSize());
            this.filePath = "D://Documents//fotos//";

            objOutros.setUrl(this.filePath);
            byte[] bytes = null;

            if (null != uploadedPhoto) {
                bytes = uploadedPhoto.getContents();
                String filename = FilenameUtils.getName(uploadedPhoto.getFileName());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(this.filePath + filename)));
                stream.write(bytes);
                stream.close();

            }

            return "success";
    }

    /*  The above code is for file upload using simple mode. */
    //This below code is for file upload with advanced mode.
    public void uploadPhoto(FileUploadEvent e) throws IOException, SQLException {

        CredencialBD repositorio = new CredencialBD();
        GoogleAuthenticator gauth = new GoogleAuthenticator();
        gauth.setCredentialRepository(repositorio);
        String user = "David";
        
        System.out.println("Username: " + user);
//        System.out.println("Chave: " + repositorio.getSecretKey(loginBean.login.getUname()));
        System.out.println("Chave: " + repositorio.getSecretKey(user));
        System.out.println("TOTP: " + gauth.getTotpPassword(repositorio.getSecretKey(user)));
        boolean authorize = gauth.authorizeUser(user,
                gauth.getTotpPassword(repositorio.getSecretKey(user)));
        if (authorize) {

            UploadedFile uploadedPhoto = e.getFile();

//            this.filePath = "D:/Users/" + System.getProperty("user.name") + "/Documents/fotos/";
            this.filePath = "D:/Documents/fotos/";

            byte[] bytes = null;

            //System.out.println("ID do usuário - " + loginBean.getId());
            if (null != uploadedPhoto) {
                bytes = uploadedPhoto.getContents();
                String filename = FilenameUtils.getName(uploadedPhoto.getFileName());
                this.setName(filename);
                this.setUrl(filePath);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(this.filePath + filename)));
                stream.write(bytes);
                stream.close();
                documentosBD.inserirDocumentos(getName(), getUrl(), loginBean.login.getId());
                pdfModify.modificaPDF(this.url, this.getName());

            }

            FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, uploadedPhoto.getFileName(), ""));
        } else {
            FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "deu erro na autorização fdp", ""));
        }
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

}
