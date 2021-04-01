package searchString;

import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class searchString {
    private static int counter;
    protected int pageCurr;
    private JFrame frame;
    private JPanel panel;

    public static void main(String[] args) throws IOException {
        String str1 = null;
        String strSearch = "Edward"; /// String to search for

        try {
            PDFParser parser = new PDFParser(new RandomAccessFile(new File("D:/Test.pdf"), "r"));
            parser.parse();

            /// Strip string from the document
            PDDocument doc = new PDDocument(parser.getDocument());
            PDFTextStripper stripper = new PDFTextStripper();
            str1 = stripper.getText(doc);

            /// Get pages containing string
            System.out.println("Pages: ");
            for (int pgNum = 1; pgNum <= doc.getNumberOfPages(); pgNum++) {
                PDFTextStripper s = new PDFTextStripper();
                s.setStartPage(pgNum);
                s.setEndPage(doc.getNumberOfPages());
                String str2 = s.getText(doc);
                if (str2.contains(strSearch)) {
                    System.out.print(pgNum + " ");
                }
            }

            doc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }

        /// Use a scanner
        Scanner scan = new Scanner(str1);

        int num = 0;    /// Counter
        /// While loop through each line
        while (scan.hasNext()) {
            String result = scan.nextLine();
            if (result.contains(strSearch)) {
                num = num + 1;
            }
        }
        counter = num;
        System.out.println("\nString Count: " + num);

        /// RUN GUI
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    searchString gui = new searchString();
                    gui.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /// Initialize the GUI
    public searchString() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();

        JLabel label = new JLabel();
        label.setText(String.valueOf(counter));

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(label);

        frame.add(panel, BorderLayout.CENTER);
        frame.setResizable(false);
        frame.setTitle("PDF Search String Tool");
        frame.setBounds(100, 100, 1080, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
