import org.jetbrains.annotations.NotNull;

import java.io.*;

public class Main {
    public static void main(@NotNull String[] args) throws Exception {
        /*if (args.length < 1) {
            System.out.println("java Main <filename>");
            System.exit(1);
        }*/

        File input = new File("test.txt");
        Reader reader = new FileReader(input);
        Lexer lexer = new Lexer(reader);
        LookAhead1 look = new LookAhead1(lexer);

        Parser parser = new Parser(look);
        Corps c = null;
        try {
            c = parser.documentNonTerm();
            System.out.println("The code is correct");
        } catch (Exception e) {
            System.out.println("The code is not correct.");
            System.out.println(e);
            System.exit(1);
        }

        FileWriter f;
        String html = c.docHtml();
        try {
            f = new FileWriter(new File("test.html"));
            f.write(html);
            f.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
