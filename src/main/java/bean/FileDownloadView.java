/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

 
import java.io.FileInputStream;
import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

 
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
 
@ManagedBean
@RequestScoped
public class FileDownloadView {
     
    public String teste;
    
   
     
    public StreamedContent baixar(String nome)throws Exception{  
        
        StreamedContent file;
        InputStream stream = new FileInputStream("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\pdf\\"+nome);
        teste = "C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\pdf\\"+nome;
        System.out.println("variavel teste: "+teste);
        file = new DefaultStreamedContent(stream, "application/pdf", nome);
        return file;
    }
 
    
}