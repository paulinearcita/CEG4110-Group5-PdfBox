import org.apache.pdfbox.contentstream.PDFStreamEngine;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

///Extracts images from a PDF File.
///Extends the PDFStreamEngine so that individual pages can be processed for images. Also throws IO exception.
public class ImageExtraction extends PDFStreamEngine
{
    public ImageExtraction() throws IOException
    {
    }

    public int imageNumber = 1;
    ///Main method invoked for preliminary testing
    public static void main( String[] args ) throws IOException
    {
        PDDocument document = null;
        String fileName = "C:/Users/wsucatslabs/Documents/test.pdf";
        try
        {
            document = PDDocument.load( new File(fileName) );
            ImageExtraction printer = new ImageExtraction(); ///< stream engine used to process individual pages
            int pageNum = 0;
            for( PDPage page : document.getPages() ) //repeat this loop for each page in the PDF
            {
                pageNum++;
                System.out.println( "Processing page: " + pageNum );
                printer.processPage(page); //call the processPage method of the PDFStreamEngine object defined earlier, and send each page as a parameter
            }
        }
        finally
        {
            if( document != null )
            {
                document.close(); //once all pages have been processed close the document
            }
        }
    }
    @Override
    ///Override the processOperator method of the PDFStreamEngine
    protected void processOperator( Operator operator, List<COSBase> operands) throws IOException
    {
        String operation = operator.getName();
        if( "Do".equals(operation) )
        {
            //scan all objects on the page and if it is an image, perform image IO
            COSName objectName = (COSName) operands.get( 0 );
            PDXObject xobject = getResources().getXObject( objectName );
            if( xobject instanceof PDImageXObject)
            {
                PDImageXObject image = (PDImageXObject)xobject;

                //same image to local path
                BufferedImage bImage = image.getImage();
                ImageIO.write(bImage,"JPG",new File("C:/Users/wsucatslabs/Documents/extractedimage_"+imageNumber+".jpg"));
                System.out.println("Image saved.");
                imageNumber++;

            }
            //if object is a form, show the form
            else if(xobject instanceof PDFormXObject)
            {
                PDFormXObject form = (PDFormXObject)xobject;
                showForm(form);
            }
        }
        //for all other object types just call the standard processOperator from the superclass
        else
        {
            super.processOperator( operator, operands);
        }
    }

}
