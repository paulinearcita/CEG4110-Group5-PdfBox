import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.IOException;

public class DocumentCreation {
    
   public static void main (String args[]) throws IOException {
       
      //Creating PDF document object 
      PDDocument document = new PDDocument();
      PDPage my_page = new PDPage();
      document.addPage(my_page);
       
      //Saving the document
      document.save("C:/Users/wsucatslabs/Documents/PDFTest/my_doc.pdf");
         
      System.out.println("PDF created");  
    
      //Closing the document  
      document.close();

   }  
}