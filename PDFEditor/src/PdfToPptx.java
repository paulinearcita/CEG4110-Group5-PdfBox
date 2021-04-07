import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

import javax.imageio.ImageIO;

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

/// Converts a pdf file to PowerPoint file. This class is created due to US016.
public class PdfToPptx {

    public static void main(String[] args) {

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
                }

                if(pdfFile.exists()) {
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
                    System.out.println("Successfully converted pdf file to pptx");

                } else {
                    System.out.println("Pdf file not found\n");
                }

                FileOutputStream out = new FileOutputStream(pdfFile.getName() + ".pptx");
                ppt.write(out);
                out.close();
                ppt.close();

            } catch (Exception e){
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.exit(0);

    }
}
