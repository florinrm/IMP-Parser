import java.io.*;
import java.util.*;

public class Parser {
	public static String addNewline(String print) {
		Scanner scanner = new Scanner(print);
		String build = "";
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			build += "  " + line + "\n";
		}
		scanner.close();
		return build;
	}
	
	public static void main (String[] args) throws IOException {
		Lexer l = new Lexer(new FileReader(args[0]));

		l.yylex();
        System.out.println((l.stack.pop()).show());
		//System.out.println(((ExprList) l.stack.pop()).show());
		//System.out.println(((ExprList) l.stack.pop()).interpret().show());

	}
}