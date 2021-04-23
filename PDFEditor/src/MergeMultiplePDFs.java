package mergemultiplepdfs;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;

public class MergeMultiplePDFs {

    /// MergeMultiplePDFs accepts two file variables from outside
    /// These files MUST be full PDF locations with double \\s
    public static void main(File file1, File file2) throws IOException {
                
        /// instantiating the PDFMergerUtility class
        PDFMergerUtility pdfMerger = new PDFMergerUtility();

        /// set the destination file path
        /// these files MUST be full locations with double \s upon completion
       
        Scanner userInput = new Scanner (System.in);
        System.out.print("Please enter the full destination of the files to be merged: ");
        String destination = userInput.nextLine();
       
        pdfMerger.setDestinationFileName(destination);

        /// add all source files, to be merged, to pdfMerger
        pdfMerger.addSource(file1);
        pdfMerger.addSource(file2);

        /// merge documents
        pdfMerger.mergeDocuments(null);

        /// success statement
        System.out.println("PDF Documents merged to a single file");
    }

}
