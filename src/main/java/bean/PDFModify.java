package bean;

import com.itextpdf.text.Annotation;
import java.io.FileOutputStream;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import java.io.File;
import com.itextpdf.text.Image;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class PDFModify {

    GenerateQRCode generatedQRCode = new GenerateQRCode();

    public void setGeneratedQRCode(GenerateQRCode generatedQRCode) {
        this.generatedQRCode = generatedQRCode;
    }

    public void modificaPDF(String url, String nome) {
        try {

            String qrCodeText = url;
            String filePath = "C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\qrcode\\" +nome+".png";
            int size = 125;
            String fileType = "png";
            File qrFile = new File(filePath);
            generatedQRCode.createQRImage(qrFile, qrCodeText, size, fileType);
            System.out.println("DONE");
            //Create PdfReader instance.
            PdfReader pdfReader = new PdfReader("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\fotos\\" +nome);

            //Create PdfStamper instance.
            PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\pdf\\" +nome));
            PdfContentByte content = pdfStamper.getOverContent(1);
            Image image = Image.getInstance("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\qrcode\\" +nome+".png");

            //Get the number of pages in pdf.
            

            //Iterate the pdf through pages.
           
                image.scaleAbsolute(100, 100);
                image.setAbsolutePosition(0, 0);
                image.setBorder(0);
                image.setBorderWidth(0.0f);
                image.setAnnotation(new Annotation(0, 0, 0, 0, 3));
                content.addImage(image);
                
          

            //Close the pdfStamper.
            pdfStamper.close();

            System.out.println("PDF modified successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
