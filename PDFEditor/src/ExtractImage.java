package extractimage;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class ExtractImage {

    /// ExtractImage accepts one String variable from outside
    /// These files MUST be full PDF locations with double \s
    public static void main(String CONTENT_PDF) {
        /// set the destination file path
        /// these files MUST be full locations with double \s upon completion
        Scanner userInput = new Scanner (System.in);
        System.out.print("Please enter the destination of the extracted image files excluding any file type: ");
        String destination = userInput.nextLine();
        
        try {
            PDDocument document = PDDocument.load(new File(CONTENT_PDF));
            /// Getting the resources for a page
            PDResources pdResources = document.getPage(0).getResources();
                
                for (COSName csName : pdResources.getXObjectNames()) {
                    System.out.println(csName);
                    PDXObject pdxObject = pdResources.getXObject(csName);
                    if (pdxObject instanceof PDImageXObject) {
                        PDStream pdStream = pdxObject.getStream();
                        PDImageXObject image = new PDImageXObject(pdStream, pdResources);
                        
                        /// image storage location and image name
                        File imgFile = new File(destination + 1 + ".png");
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
                        /// image storage location and image name
                        File imgFile = new File(destination + k + ".png");
                        ImageIO.write(image.getImage(), "png", imgFile);
                    }
                }
                j++;
            }
            document.close();
        } catch (IOException e) {
            /// Auto-generated catch block
            e.printStackTrace();
        }
    }
}
