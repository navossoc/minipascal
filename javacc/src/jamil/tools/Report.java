package jamil.tools;

import java.io.FileInputStream;
import java.io.InputStream;

import jamil.LMP;
import jamil.LMPConstants;
import jamil.Token;

public class Report {

	private static String kindOf(Token token) {
		return LMPConstants.tokenImage[token.kind];
	}

	public static void generate(String filename) {
		System.out.format("| %s | %s | %-15s | %-50s\n", "Linha", "Coluna",
				"Kind", "Lexeme");

		try {
			InputStream in = new FileInputStream(filename);
			LMP parser = new LMP(in);

			for (;;) {
				Token token;
				token = parser.getNextToken();
				System.out.println(String.format(
						"| %03d   | %03d    | %-15s | %-50s", token.beginLine,
						token.beginColumn, kindOf(token), token.image));
				if (kindOf(token).equals("<EOF>"))
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
