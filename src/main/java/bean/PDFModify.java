package bean;

import com.itextpdf.text.Annotation;
import java.io.FileOutputStream;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import java.io.File;
import com.itextpdf.text.Image;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;
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
            // formatando local/data
            String qrCodeText = "Este documento foi assinado digitalmente.";
//            String filePath = "D:\\Users\\"+System.getProperty("user.name")+"\\Documents\\qrcode\\" +nome+".png";
            String filePath = "D:\\Documents\\qrcode\\" +nome+".png";
            int size = 125;
            String fileType = "png";
            File qrFile = new File(filePath);
            generatedQRCode.createQRImage(qrFile, qrCodeText, size, fileType);
            System.out.println("DONE");
            //Create PdfReader instance.
//            PdfReader pdfReader = new PdfReader("D:\\Users\\"+System.getProperty("user.name")+"\\Documents\\fotos\\" +nome);
            PdfReader pdfReader = new PdfReader("D:\\Documentos\\fotos\\" +nome);

            //Create PdfStamper instance.
//            PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream("D:\\Users\\"+System.getProperty("user.name")+"\\Documents\\pdf\\" +nome));
            PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream("D:\\Documents\\pdf\\" +nome));
            PdfContentByte content = pdfStamper.getOverContent(1);
//            Image image = Image.getInstance("D:\\Users\\"+System.getProperty("user.name")+"\\Documents\\qrcode\\" +nome+".png");
            Image image = Image.getInstance("D:\\Documents\\qrcode\\" +nome+".png");

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
