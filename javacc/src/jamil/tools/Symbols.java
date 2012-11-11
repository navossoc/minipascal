package jamil.tools;

import java.util.Set;
import java.util.TreeSet;

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

	public static void write() {
		// TODO: escrever conteúdo num arquivo
	}

}
