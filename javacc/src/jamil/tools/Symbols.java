package jamil.tools;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Symbols {

	public static Set<String> identifiers = new TreeSet<String>();

	public static void add(String identifier) {
		Symbols.identifiers.add(identifier);
	}

	public static void print() {
		for (String identifier : identifiers) {
			System.out.println(identifier);
		}
	}

	public static boolean write(String filename) {
		FileWriter fw;
		try {
			fw = new FileWriter(filename);
		} catch (IOException ex) {
			return false;
		}

		BufferedWriter bw = new BufferedWriter(fw);
		try {

			bw.write("Lista de símbolos");
			bw.newLine();
			bw.newLine();
			
			// Identificadores
			for (String identifier : identifiers) {
				bw.write(identifier);
				bw.newLine();
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
