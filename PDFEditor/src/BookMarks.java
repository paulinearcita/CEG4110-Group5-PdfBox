
import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PageMode;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageDestination;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageFitWidthDestination;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;

public class BookMarks {

    public static void main(String[] args) {


        try (PDDocument document = PDDocument.load(new File("C:\\Priya\\Priya.pdf"))) {
            ///Created the documentOutline
            PDDocumentOutline documentOutline = new PDDocumentOutline();
            document.getDocumentCatalog().setDocumentOutline(documentOutline);
            PDOutlineItem pagesOutline = new PDOutlineItem();
            pagesOutline.setTitle("Document");
            documentOutline.addLast(pagesOutline);

            for (int i = 0; i < document.getNumberOfPages(); i++) {
                ///Counts the pages and set the page numbers
                PDPageDestination pageDestination = new PDPageFitWidthDestination();
                pageDestination.setPage(document.getPage(i));

                PDOutlineItem bookmark = new PDOutlineItem();
                bookmark.setDestination(pageDestination);
                bookmark.setTitle("Page : " + (i + 1));
                pagesOutline.addLast(bookmark);
            }

            pagesOutline.openNode();
            documentOutline.openNode();
            ///show the outlines when opening the file
            document.getDocumentCatalog().setPageMode(PageMode.USE_OUTLINES);

            ///Save the document
            document.save("C:\\Priya\\Priya.pdf");
            System.out.println("Documents save successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
