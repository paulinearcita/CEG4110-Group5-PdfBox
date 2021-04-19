import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.poi.sl.usermodel.PictureData.PictureType;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFPictureData;
import org.apache.poi.xslf.usermodel.XSLFPictureShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class PdfToPptx {

    public static void main(String[] args) {

        String filename = "test.pptx";
        try{

            File pdfFile = new File("C:\\Users\\arcit\\Desktop\\SPRING 2021\\CEG 4110\\CEG4110-Group5-PdfBox\\PDFEditor\\PdfToPptFiles\\pdfFileTest.pdf");

            /// try and catch block for handling exceptions for dealing with input/output stream
            try{
                /// To create an empty PowerPoint SlideShow
                XMLSlideShow ppt = new XMLSlideShow();

                String destinationDir = pdfFile.getName();

                File destinationFile = new File(destinationDir);
                if(!destinationFile.exists()){
                    destinationFile.mkdir();
                    System.out.println("Folder created at " + destinationFile.getAbsolutePath());
                }

                if(pdfFile.exists()) {
                    System.out.println("Images are copied here: " + destinationFile.getName());
                    PDDocument document = PDDocument.load(pdfFile);
                    PDPageTree list = document.getPages();

                    String fName = pdfFile.getName().replace(".pdf", "");
                    PDFRenderer pdfRenderer = new PDFRenderer(document);

                    for (int i = 0; i < list.getCount(); i++) {
                        PDPage page = list.get(i);

                        BufferedImage image = pdfRenderer.renderImageWithDPI(i, 300, ImageType.RGB);
                        File outputFile = new File(fName + (i + 1) + ".png");
                        ImageIO.write(image, "png", outputFile);

                        ppt.setPageSize(new java.awt.Dimension(1280, 720));

                        XSLFSlide slide = ppt.createSlide();
                        byte[] pictureData = IOUtils.toByteArray(new FileInputStream(outputFile.getAbsolutePath()));

                        XSLFPictureData pd = ppt.addPicture(pictureData, PictureType.PNG);
                        XSLFPictureShape pic = slide.createPicture(pd);
                        pic.setAnchor(new java.awt.Rectangle(0, 0, 1280, 720));
                    }

                    document.close();
                    System.out.println("Converted images are saved at " + destinationFile.getAbsolutePath());

                } else {
                    System.out.println("Pdf file not found\n");
                }

                FileOutputStream out = new FileOutputStream(pdfFile.getName() + ".pptx");
                ppt.write(out);
                out.close();
                ppt.close();

            } catch (Exception e){
                //System.out.println("File Failure: " + filename);
                e.printStackTrace();
            }

        } catch (Exception e) {
            System.out.println("FILE FAILED: " + filename);
        }

        System.exit(0);

    }
}
