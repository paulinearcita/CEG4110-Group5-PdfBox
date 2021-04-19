import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class TextEditing {
    public static void main(String args[]) throws IOException {
        //to load PDF where you
        PDDocument pdDocument = PDDocument.load(new File("C:/Users/wsucatslabs/Documents/test.pdf"));
        //get the page where you want to write code,for first page you need to use 0
        PDPage firstPage = pdDocument.getPage(0);

        //you can load new font if required, by using `ttf` file for that font
        PDFont pdfFont= PDType1Font.HELVETICA_BOLD;

        int fontSize = 9;
        //PDPageContentStream.AppendMode.APPEND this part is must if you want just add new data in exsitnig one
        PDPageContentStream contentStream = new PDPageContentStream(pdDocument, pdDocument.getPage(0),
                PDPageContentStream.AppendMode.APPEND, true, true);

        contentStream.setFont(pdfFont, fontSize);
        contentStream.setNonStrokingColor(Color.YELLOW);
        //for first Line
        contentStream.beginText();
        //For adjusting location of text on page you need to adjust this two values
        contentStream.newLineAtOffset(400,220);
        contentStream.showText("this is line first");
        contentStream.endText();

        //for second line
        contentStream.beginText();
        contentStream.newLineAtOffset(400,100);
        contentStream.showText("this is line second");
        contentStream.endText();

        //for  third line
        contentStream.beginText();
        contentStream.newLineAtOffset(400,330);
        contentStream.showText("this is line third");
        contentStream.endText();
        //and so on.

        //at last you need to close the document to save data
        contentStream.close();
        //this is for saving your PDF you can save with new name
        //or you can replace existing one by giving same name
        pdDocument.save("C:/Users/wsucatslabs/Documents/testcheck.pdf");
    }
}