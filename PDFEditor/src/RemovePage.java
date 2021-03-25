import org.apache.pdfbox.pdmodel.PDDocument;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.System.exit;

public class RemovePage {
    public static void main(String[] args)throws IOException {

        //Loading an existing document
        File file = new File("C://temp//sample_form.pdf");
        PDDocument doc = PDDocument.load(file);

        //Listing the number of existing pages
        int noOfPages = doc.getNumberOfPages();

        //Showing the number of pages in the pdf document to the console.
        System.out.print("Total Number of Pages: " + noOfPages);
        System.out.println();

        //If Number of pages equals to 0 then display error that the document is blank
        if (noOfPages == 0) {
            System.out.print("Error: The Document is Blank ");
        }

        //If Number of pages not equals to 0 then move ahead and remove the page specified by the user.
        else {
            //Getting the page number from console which will be deleted
            System.out.print("Enter the Page Number to be Removed OR Enter 0 to not delete anything ");

            //Scanner object to get the input from the console
            Scanner in = new Scanner(System.in);
            int a = in.nextInt();

            //If the input is 0 then do not remove anything
            if  (a == 0) {
                System.out.print("No Pages Are Removed ");
            }
            //If the input is greater than number of pages in the document
            else if (a > noOfPages) {
                System.out.print("Page Number Not In the document. ");
            }
            //Remove the page specified by the user
            else {
                doc.removePage(a);
                System.out.println("Page number: " + a + " removed successfully.");
            }
        }

        //Saving the document
        doc.save(new File("C://temp//sample_form.pdf"));

        //Closing the document
        doc.close();
    }
}  