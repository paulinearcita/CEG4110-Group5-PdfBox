package searchString;

import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class searchString {

    public static void main(String[] args) throws IOException {
        /// Strings for all text in PDf file, PDF file name, and search string
        String allText,
                filename,
                strSearch;

        /// Take user input for name of PDF file and string to search for
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter document name (pathname): ");
        filename = sc.nextLine();
        System.out.print("Enter string to search for: ");
        strSearch = sc.nextLine();

        /// Print out user input
        System.out.println("\nDocument name: " + filename);
        System.out.println("Searching for string: " + strSearch);

        try {
            /// Open PDF file and use PDFParser to parse it
            PDFParser parser = new PDFParser(new RandomAccessFile(new File(filename), "r"));
            parser.parse();

            /// Get PDF file and strip all of its text one string
            PDDocument doc = new PDDocument(parser.getDocument());
            PDFTextStripper stripper = new PDFTextStripper();
            allText = stripper.getText(doc);

            /// Load original PDF file to be used to create a copy with pages of searched string
            File originalPdf = new File("D:/Test.pdf");
            PDDocument origDoc = PDDocument.load(originalPdf);
            PDDocument newDoc = new PDDocument();

            /// Get pages of original PDF file containing string and print page numbers
            System.out.println("Pages where string is found: ");
            for (int pgNum = 1; pgNum <= doc.getNumberOfPages(); pgNum++) {

                /// Strip text from each page one by one
                PDFTextStripper stripper2 = new PDFTextStripper();
                stripper2.setStartPage(pgNum);
                stripper2.setEndPage(pgNum);
                String str = stripper2.getText(doc);

                /// Print the page number where string is found
                if (str.contains(strSearch)) {
                    System.out.print(pgNum + " ");

                    /// Add pages where string is found into the new PDF file
                    newDoc.addPage(origDoc.getPage(pgNum - 1));
                }
            }

            /// Scanner for getting each line inside PDF file
            Scanner scan = new Scanner(allText);

            /// Counter for the number of times string is found
            int num = 0;

            /// Count the number of times the string is found
            /// Scan each line of the PDF file
            while (scan.hasNext()) {
                String result = scan.nextLine();
                if (result.contains(strSearch)) {
                    num = num + 1;
                }
            }

            /// Print the number of times string is found
            System.out.println("\nString Count: " + num);

            /// Save the new PDF file and print out message
            newDoc.save("D:/NewFile.pdf");
            System.out.println("\nNew File created.");

            /// Close all PDF files
            newDoc.close();
            origDoc.close();
            doc.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }

        /// Print message for exit
        System.out.println("Search string done.");
    }
}

