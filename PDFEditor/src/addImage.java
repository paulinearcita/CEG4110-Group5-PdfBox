import java.io.File;
import java.util.Scanner;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class addImage {
    public static void main(String args[]) throws Exception {

        //Loading an existing document
        File file = new File("C://temp//sample_form.pdf");
        PDDocument doc = PDDocument.load(file);

        ///Listing the number of existing pages
        int noOfPages = doc.getNumberOfPages();

        ///Showing the number of pages in the pdf document to the console.
        System.out.print("Total Number of Pages: " + noOfPages);
        System.out.println();

        System.out.print("Enter the Page Number where you want Image in PDF ");
        System.out.println();

        ///Scanner object to get the input from the console
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        /// Taking inputs from the user
        System.out.print("Enter the x coordinate position where you want Image in PDF ");
        System.out.println();
        int x = in.nextInt();

        System.out.print("Enter the y coordinate position where you want Image in PDF ");
        System.out.println();
        int y = in.nextInt();

        System.out.print("Enter the width of the image ");
        System.out.println();
        int width = in.nextInt();

        System.out.print("Enter the width of the image ");
        System.out.println();
        int height = in.nextInt();

        if  (a == 0) {
            System.out.print("Error: You cannot add Image on page 0. Please enter a number > 0 ");
        }
        ///If the input is greater than number of pages in the document
        else if (a > noOfPages) {
            System.out.print("Error: Page Number Not In the document. ");
        }
        ///Add the Image to the page number specified by the user
        else  {
            //Retrieving the page
            PDPage page = doc.getPage(a-1);
            //Creating PDImageXObject object
            PDImageXObject pdImage = PDImageXObject.createFromFile("C://temp//image.png",doc);
            //creating the PDPageContentStream object
            PDPageContentStream contents = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND, true);
            //Drawing the image in the PDF document
            contents.drawXObject( pdImage,x,y,width,height);
            System.out.println("Image inserted");
            //Closing the PDPageContentStream object
            contents.close();
        }
        //Saving the document
        doc.save(new File("C://temp//sample_form.pdf"));

        //Closing the document
        doc.close();
    }
}