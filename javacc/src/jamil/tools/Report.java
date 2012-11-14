package jamil.tools;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import jamil.LMP;
import jamil.LMPConstants;
import jamil.Token;

public class Report {

	private final static String RESERVED_WORD = "palavra reservada";
	private final static String SPECIAL_SYMBOL = "símbolo especial";
	private final static String STRIP_SYMBOLS = "[<>]";

	private static String type(Token token) {
		switch (token.kind) {
		// palavras reservadas
		case LMPConstants.BEGIN:
		case LMPConstants.END:
		case LMPConstants.IF:
		case LMPConstants.THEN:
		case LMPConstants.WHILE:
		case LMPConstants.DO:
			return RESERVED_WORD;
			// símbolos especiais
		case LMPConstants.DOT:
		case LMPConstants.ASSIGN:
		case LMPConstants.SEMICOLON:
		case LMPConstants.OPERATOR_GT:
		case LMPConstants.OPERATOR_LT:
		case LMPConstants.OPERATOR_EQ:
		case LMPConstants.OPERATOR_GE:
		case LMPConstants.OPERATOR_LE:
		case LMPConstants.LPAREN:
		case LMPConstants.RPAREN:
		case LMPConstants.PLUS:
		case LMPConstants.MULTIPLY:
		case LMPConstants.DIVIDE:
		case LMPConstants.MOD:
			return SPECIAL_SYMBOL;
			// classes especiais
		default:
			String temp = LMPConstants.tokenImage[token.kind];
			return temp.replaceAll(STRIP_SYMBOLS, "").toLowerCase();
		}
	}

	public static void generate(String filename) {
		System.out.format("%s | %s | %-20s | %s\n", "Linha", "Coluna", "Token",
				"Classificação");

		try {
			InputStream in = new FileInputStream(filename);
			LMP parser = new LMP(in);

			Token token;
			while ((token = parser.getNextToken()).kind != LMPConstants.EOF) {

				System.out.format("%03d   | %03d    | %-20s | %s\n",
						token.beginLine, token.beginColumn, token.image,
						type(token));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean write(String input, String filename) {
		FileWriter fw;
		try {
			fw = new FileWriter(filename);
		} catch (IOException ex) {
			return false;
		}

		BufferedWriter bw = new BufferedWriter(fw);
		try {

			bw.write("Classificação e extração de tokens");
			bw.newLine();
			bw.newLine();

			bw.write(String.format("%s | %s | %-20s | %s", "Linha", "Coluna",
					"Token", "Classificação"));
			bw.newLine();

			try {
				InputStream in = new FileInputStream(input);
				LMP parser = new LMP(in);

				Token token;
				while ((token = parser.getNextToken()).kind != LMPConstants.EOF) {

					bw.write(String.format("%03d   | %03d    | %-20s | %s",
							token.beginLine, token.beginColumn, token.image,
							type(token)));
					bw.newLine();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			bw.close();

			return true;
		} catch (IOException ex) {
			Logger.getLogger(Symbols.class.getName()).log(Level.SEVERE, null,
					ex);
		}

		return false;
	}

}
