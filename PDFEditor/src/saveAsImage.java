import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/// Standard class which converts pdf pages into image files

public class saveAsImage {
    public static void main(String args[]) throws IOException {

        /// this was a test pdf file
        PDDocument document = PDDocument.load(new File("C:/Users/renis/Desktop/project1.pdf"));
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        for (int page = 0; page < document.getNumberOfPages(); ++page)
        {
            BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);

            /// suffix in filename will be used as the file format
            ImageIOUtil.writeImage(bim, document + "-" + (page+1) + ".png", 300);
            System.out.println("Image file extracted");
        }
        document.close();
    }
}
