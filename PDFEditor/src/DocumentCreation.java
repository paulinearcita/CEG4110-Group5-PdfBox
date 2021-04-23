import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.IOException;

///Creates empty PDF with a single page.
///creates PDF at a apecified path.
public class DocumentCreation {
   ///Main method created for preliminary testing
   public static void main (String args[]) throws IOException {
       
      //Creating PDF document object 
      PDDocument document = new PDDocument(); ///< A PDF document variable
      PDPage my_page = new PDPage(); ///< A PDF page variable
      document.addPage(my_page);
       
      //Saving the document
      document.save("C:/Users/wsucatslabs/Documents/PDFTest/my_doc.pdf");
         
      System.out.println("PDF created");  
    
      //Closing the document  
      document.close();

   }  
}
