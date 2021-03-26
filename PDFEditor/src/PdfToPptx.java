import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

import javax.imageio.ImageIO;

import java.io.FileOutputStream;

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

public class PdfToPptx {

    public static void main(String[] args) {

        String filename = "test.pptx";
        /// try and catch block for handling exceptions for dealing with input/output stream
        try{
            /// To create an empty PowerPoint SlideShow
            XMLSlideShow ppt = new XMLSlideShow();

            /// Creating a file
            File file = new File(filename);

            /// Creating output stream for filename "file"
            FileOutputStream out = new FileOutputStream(file);

            /// Creating a slide for the ppt
            XSLFSlide slide1 = ppt.createSlide();

            /// Writing file to the output stream
            ppt.write(out);

            System.out.println("Powerpoint file created successfully!");

            /// Closing output stream
            out.close();

        } catch (Exception e){
            System.out.println("File Failure: " + filename);
            e.printStackTrace();
        }

    }
}
