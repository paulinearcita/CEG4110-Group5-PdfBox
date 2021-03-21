package searchString;

import java.io.IOException;
import java.util.Scanner;

public class searchString {
    public static void main(String[] args) throws IOException {
        String str1 = null;
        String strSearch = "Sample_string_here";

        ///        use a try and catch here
        ///        try:    open sample document
        ///                parse document
        ///                use pdfStripper to get text from document -> str1
        ///                close sample document
        ///        catch:  file not found

        /// TEST TEST TEST
        /// Use a scanner
        Scanner scan = new Scanner(str1);

        int num = 0;    /// Counter
        /// While loop through each line
        while (scan.hasNext()) {
            String result = scan.nextLine();
            /// If str1 contains strSearch, count how many times it is found
            if (result.contains(strSearch)) {
                num = num + 1;
            }
        }
        System.out.println("" + num);
    }
}
