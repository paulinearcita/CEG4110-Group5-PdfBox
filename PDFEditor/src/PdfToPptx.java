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

            /// Pdf file to be converted
            /// Include the pathname of file for concise access
            File pdfFile = new File("C:\\Users\\arcit\\Desktop\\SPRING 2021\\CEG 4110\\CEG4110-Group5-PdfBox\\PDFEditor\\PdfToPptFiles\\pdfFileTest.pdf");

            /// try and catch block for handling exceptions for dealing with input/output stream
            try{
                /// PowerPoint file
                /// To create an empty PowerPoint SlideShow to be able to add the converted pdf file. It uses XMLSlideShow under poi library.
                XMLSlideShow ppt = new XMLSlideShow();

                /// Destination filename
                /// This will be the filename of the converted pdf file to PowerPoint
                String destinationFName = pdfFile.getName();

                /// Destination File
                /// This will be the PowerPoint file of the converted pdf file
                File destinationFile = new File(destinationFName);
                if(!destinationFile.exists()){
                    destinationFile.mkdir();
                }

                if(pdfFile.exists()) {
                    /// Pdf document
                    /// PDDocument is a class in PdfBox. More info here: https://pdfbox.apache.org/docs/2.0.2/javadocs/org/apache/pdfbox/pdmodel/PDDocument.html
                    PDDocument document = PDDocument.load(pdfFile);

                    /// List of pages in Pdf document
                    /// Used PDPageTree which is a class of PdfBox. More info here: https://pdfbox.apache.org/docs/2.0.5/javadocs/org/apache/pdfbox/pdmodel/PDPageTree.html
                    PDPageTree list = document.getPages();

                    /// Pdf filename
                    /// fName will have the pdf filename to remove .pdf
                    String fName = pdfFile.getName().replace(".pdf", "");

                    /// Pdf Rendered
                    /// PDFRenderer is a class in PdfBox. It is use to render a pdf document to BufferedImage: More info here: https://pdfbox.apache.org/docs/2.0.3/javadocs/org/apache/pdfbox/rendering/PDFRenderer.html
                    PDFRenderer pdfRenderer = new PDFRenderer(document);

                    for (int i = 0; i < list.getCount(); i++) {

                        /// page of pdf document
                        /// Used PDPage which is a class of PdfBox. More info here: https://pdfbox.apache.org/docs/2.0.2/javadocs/org/apache/pdfbox/pdmodel/PDPage.html
                        PDPage page = list.get(i);

                        /// converted pdf page to image
                        /// BufferedImage is a Java Image class. More info here: https://docs.oracle.com/javase/7/docs/api/java/awt/image/BufferedImage.html
                        BufferedImage image = pdfRenderer.renderImageWithDPI(i, 300, ImageType.RGB);

                        /// image output file
                        /// Creates a new File for the image
                        File outputFile = new File(fName + (i + 1) + ".png");

                        /// Write the image into the outputFile
                        ImageIO.write(image, "png", outputFile);

                        /// To set the page size of the PowerPoint with given dimension
                        ppt.setPageSize(new java.awt.Dimension(1280, 720));

                        /// Slide in PowerPoint
                        /// Creating a slide in PowerPoint file through XSLFSlide class of PdfBox. More info here: https://poi.apache.org/apidocs/dev/org/apache/poi/xslf/usermodel/XSLFSlide.html
                        XSLFSlide slide = ppt.createSlide();

                        /// byte array for data of image
                        /// It uses IOUtils to convert image file. More info here: https://commons.apache.org/proper/commons-io/apidocs/org/apache/commons/io/IOUtils.html
                        byte[] pictureData = IOUtils.toByteArray(new FileInputStream(outputFile.getAbsolutePath()));

                        /// To add image to the slide
                        /// Used XSLFPictureData from Apache poi. More info here: https://poi.apache.org/apidocs/dev/org/apache/poi/xslf/usermodel/XSLFPictureData.html
                        XSLFPictureData pd = ppt.addPicture(pictureData, PictureType.PNG);

                        /// Picture shape
                        /// Uses XSLFPictureShape from Apache poi library. More info here: https://poi.apache.org/apidocs/dev/org/apache/poi/xslf/usermodel/XSLFPictureShape.html
                        XSLFPictureShape pic = slide.createPicture(pd);

                        /// To position the shape within the canvas.
                        /// More info on setAnchor: https://poi.apache.org/apidocs/dev/org/apache/poi/xslf/usermodel/XSLFSimpleShape.html#setAnchor-java.awt.geom.Rectangle2D-
                        pic.setAnchor(new java.awt.Rectangle(0, 0, 1280, 720));
                    }

                    document.close();
                    System.out.println("Successfully converted pdf file to pptx");

                } else {
                    System.out.println("Pdf file not found\n");
                }

                /// output file of PowerPoint
                FileOutputStream out = new FileOutputStream(pdfFile.getName() + ".pptx");

                /// write into outfile with ppt
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
