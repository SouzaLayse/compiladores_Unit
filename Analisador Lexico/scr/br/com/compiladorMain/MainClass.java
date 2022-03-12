package br.com.compiladorMain;

import br.com.compiladorException.IsiLexicaException; // import da exceção léxica
import br.com.compiladorLexico.IsiScanner; // import do scanner
import br.com.compiladorLexico.Token; // import do token

public class MainClass {

	public static void main(String[] args) {
		
		try {
		IsiScanner scannerLexico = new IsiScanner("input.isi");
		Token token;
		
		do{
			token = scannerLexico.nextToken();
			if(token != null) {
				//System.out.println(token);
				System.out.println(token.toString());
			}
		}while(token != null);
		} catch (IsiLexicaException e) { // tratamento especifico (condição para a exceção)
			System.out.println(" \n Erro Léxico!" + e.getMessage());
			
		} catch (Exception e) { // tratamento generico
			System.out.println("\n Erro Genérico!");
		}

	}

}


