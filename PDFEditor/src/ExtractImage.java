package extractimage;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class ExtractImage {

    //  The path to PDF to be extracted from
    // public static final String CONTENT_PDF = "C:\\Users\\Joshua\\Downloads\\PDFBOX-Task006-testing.pdf";
    // Blank test commented out    
    public static final String CONTENT_PDF = "C:\\Users\\Joshua\\Pictures\\Intro to Software\\Sermon (17 March 2021) (1).pdf";

    public static void main(String[] args) {
        try {
            PDDocument document = PDDocument.load(new File(CONTENT_PDF));
            // Getting the resources for a page

            PDResources pdResources = document.getPage(0).getResources();
                
                for (COSName csName : pdResources.getXObjectNames()) {
                    System.out.println(csName);
                    PDXObject pdxObject = pdResources.getXObject(csName);
                    if (pdxObject instanceof PDImageXObject) {
                        PDStream pdStream = pdxObject.getStream();
                        PDImageXObject image = new PDImageXObject(pdStream, pdResources);
                        
                        // image storage location and image name
                        File imgFile = new File("C:\\Users\\Joshua\\Pictures\\Intro to Software\\TestImage" + 1 + ".png");
                        ImageIO.write(image.getImage(), "png", imgFile);
                    }
                }
            
            int i = document.getNumberOfPages();
            int j = 0;

            while (i > j) {

                pdResources = document.getPage(j).getResources();
                int k = 1;
                for (COSName csName : pdResources.getXObjectNames()) {
                    System.out.println(csName);
                    PDXObject pdxObject = pdResources.getXObject(csName);
                    if (pdxObject instanceof PDImageXObject) {
                        PDStream pdStream = pdxObject.getStream();
                        PDImageXObject image = new PDImageXObject(pdStream, pdResources);
                        k++;
                        // image storage location and image name
                        File imgFile = new File("C:\\Users\\Joshua\\Pictures\\Intro to Software\\TestImage" + k + ".png");
                        ImageIO.write(image.getImage(), "png", imgFile);
                    }
                }
                j++;
            }
            document.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
