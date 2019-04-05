package hassan_code;

import java.io.FileWriter;
import java.io.PrintWriter;

public class LexProgram {

    // Get the input and output from the command line arguments
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Usage: java LexParser <input file> <output file>");
            return;
        }

        LexParser parser = new LexParser(args[0]);
        Lexeme lexeme = parser.getNext();

        PrintWriter output = new PrintWriter(new FileWriter(args[1]));

        while (lexeme != null) {
            System.out.println(lexeme);
            output.println(lexeme);
            lexeme = parser.getNext();
        }

        output.close();
        
        System.out.println("Lexical analysis complete.");
    }
}
