package mergemultiplepdfs;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;

public class MergeMultiplePDFs {

    public static void main(String[] args) throws IOException {
        // load pdf files to be merged
        // these files MUST be full locations with double \s
        File file1 = new File("C:\\Users\\Joshua\\Documents\\GitHub\\CEG4110-Group5-PdfBox\\MergeMultiplePDFs\\src\\mergemultiplepdfs\\Homework 1.pdf");
        File file2 = new File("C:\\Users\\Joshua\\Documents\\GitHub\\CEG4110-Group5-PdfBox\\MergeMultiplePDFs\\src\\mergemultiplepdfs\\Homework 2.pdf");

        // instantiating the PDFMergerUtility class
        PDFMergerUtility pdfMerger = new PDFMergerUtility();

        // set the destination file path
        // these files MUST be full locations with double \s
        pdfMerger.setDestinationFileName("C:\\Users\\Joshua\\Documents\\merged.pdf");

        // add all source files, to be merged, to pdfMerger
        pdfMerger.addSource(file1);
        pdfMerger.addSource(file2);

        // merge documents
        pdfMerger.mergeDocuments(null);
        
        // success statement
        System.out.println("PDF Documents merged to a single file");
    }

}
