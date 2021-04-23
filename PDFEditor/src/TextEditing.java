import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.*;
import java.io.File;
import java.io.IOException;

//Adds text to PDF as a way of annotation
///Text can have unique font, color, size, and location. As of right now the program adds three seperate lines of text at unique positions
public class TextEditing {
    ///Main method invoked for preliminary testing
    public static void main(String args[]) throws IOException {
        PDDocument pdDocument = PDDocument.load(new File("C:/Users/wsucatslabs/Documents/test.pdf")); ///< PDF document object loaded from provided file path

        PDPage firstPage = pdDocument.getPage(0); ///< first page of the PDF loaded

        PDFont pdfFont= PDType1Font.HELVETICA_BOLD; //font

        int fontSize = 9; //font size

        PDPageContentStream contentStream = new PDPageContentStream(pdDocument, pdDocument.getPage(0),
                PDPageContentStream.AppendMode.APPEND, true, true); ///< output stream that will append text

        contentStream.setFont(pdfFont, fontSize); //set font and font size for the stream
        contentStream.setNonStrokingColor(Color.YELLOW); //set a stroking color

        //write the first line of text
        contentStream.beginText();
        contentStream.newLineAtOffset(400,220);
        contentStream.showText("this is line first");
        contentStream.endText();

        //write the second line of text
        contentStream.beginText();
        contentStream.newLineAtOffset(400,100);
        contentStream.showText("this is line second");
        contentStream.endText();

        //write the third line of text
        contentStream.beginText();
        contentStream.newLineAtOffset(400,330);
        contentStream.showText("this is line third");
        contentStream.endText();

        //close the stream
        contentStream.close();

        //save the document
        pdDocument.save("C:/Users/wsucatslabs/Documents/testcheck.pdf");
    }
}
