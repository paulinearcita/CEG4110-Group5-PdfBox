import java.io.FileInputStream;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;

public class Encryption {


    public static void main(String args[]) {

        try {
            /// load Document object with existing pdf.
            PDDocument pdDocument = PDDocument.load(new FileInputStream("C:\\Priya\\Random.pdf"));
            /// Define an encryption key length
            int encryptionKeyLength = 256;

            /// Setup the Access permissions.
            AccessPermission accessPermission = new AccessPermission();

            /// StandardProtectionPolicy class takes owner password, user
            /// password and the permissions
            StandardProtectionPolicy standardProtectionPolicy = new StandardProtectionPolicy("priya", "password",
                    accessPermission);
            standardProtectionPolicy.setEncryptionKeyLength(encryptionKeyLength);

            /// time to protect the pdf
            pdDocument.protect(standardProtectionPolicy);
            pdDocument.save("C:\\Priya\\Random.pdf");
            pdDocument.close();

            System.out.println("PDF is now password protected!!!");

        } catch (IOException ioe) {
            System.out.println("Error while reading pdf" + ioe.getMessage());
        }

    }
}
