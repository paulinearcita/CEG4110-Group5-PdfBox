import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/// Standard class that extracts phone numbers from the pdf file

public class ExtractPhoneNumber {
    public static void main(String[] args) throws IOException {

        /// adding pdf file location
        File fileName = new File("C:/Users/renis/Desktop/test.pdf");
        PDDocument doc = PDDocument.load(fileName);

        /// using StringBuilder to extract phone number
        StringBuilder sb = new StringBuilder();
        PDFTextStripper stripper = new PDFTextStripper();

        /// Adding text to StringBuilder from the pdf
        sb.append(stripper.getText(doc));

        /// using RegEx
        Pattern p = Pattern.compile("\\s\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\s");

        /// Matcher is found pattern
        Matcher m = p.matcher(sb);
        while (m.find()) {
            /// group() method stores phone number
            System.out.println("Phone number is:" + m.group());
        }

        if (doc != null) {
            doc.close();
        }
        System.out.println("\nPhone Number extracted!");
    }
}
