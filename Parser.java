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
		Expression expression = null;
		expression = l.stack.pop();
		//System.out.println(expression.show());
		
		System.out.println(l.stack.size()); 
		while (!l.stack.empty()) {
			System.out.println(l.stack.pop().show());
		}
		//System.out.println(expression.show());
		
		while (!(l.stack.peek() instanceof MainNode)) {
			if (l.stack.peek() instanceof SequenceNode) {
				SequenceNode seq = (SequenceNode) l.stack.pop();
				System.out.println(seq.show());
				seq.setSecondStatement(expression);
				expression = seq;
			}
		} 
		l.stack.pop();
		l.stack.push(new MainNode(expression));
		System.out.println((l.stack.pop()).show());
		//System.out.println(((ExprList) l.stack.pop()).interpret().show());

	}
}