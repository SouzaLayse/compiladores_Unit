// Esse arquivo serve para ler um arquivo, colocar esse arquivo em um vetor de caracteres e gerar uma string em função disso

package br.com.compiladorLexico;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import br.com.compiladorException.IsiLexicaException;

public class IsiScanner {
	
	// 1º
	private char[] content; // vetor de caracteres
	private int estado; // estado
	private int posicao; // posicao
	
	//1º - construtor
	public IsiScanner(String filename) {
		try {
			String txtConteudo;
			txtConteudo = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
			System.out.println("DEBUG -------------");
			content = txtConteudo.toCharArray();
			System.out.println(txtConteudo);
			System.out.println("--------------------");
			posicao = 0;
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	// 3º 
	// método mais importante, pois permite retornar tudo que precisar do modelo do autômato, principalmente 
	// aqui será lido caracter por caracter
	public Token nextToken() {
		
		char currentChar; // para verificar o próximo char 
		Token token; // para criar o objeto token da classe Token
		String termo = " "; // string para armazenar o char 
		
		if(isFinalArquivo()) {
			return null;
		}
		
		estado = 0;
		
		while(true) {
			
			currentChar = nextChar(); // recebe o próximo char 
		
			// ler todos os estados do automato e reconhecer os identificadores, números, 
			switch (estado){
			case 0:
				if(isChar(currentChar)) { // é caracter
					termo += currentChar; // armazena cada char
					estado = 1;
				}
				else if(isDigit(currentChar)) { //é digito
					estado = 3;
					termo += currentChar; // armazena cada char
				}
				else if(isSpace(currentChar)) { // é espaço
					estado = 0;
				}
				else if(!isOperator(currentChar)) { // é operador
					estado = 5;
				}
				else {
					throw new IsiLexicaException(" Símbolo não reconhecido");
				}
					
				break;
			case 1: // caracter ou dígito -> estado 1
				if(isChar(currentChar)||isDigit(currentChar)) {  //quando for \n -> vai para o estado 2, case 2 e retorna token
					estado = 1;
					termo += currentChar; // armazena cada char 
				}
				else if(isSpace(currentChar) || isOperator(currentChar)){
					estado = 2;
				} 
				else {
					throw new IsiLexicaException(" Identificador mal formado");
				}
				break;
			case 2: // tokens -> estado 2 (final)
				back();
				token = new Token();
				token.setType(Token.tk_indentificador);
				//token.setText(termo);
				return token;
			case 3: // ler qualquer outro token que não seja letra -> estado 3
				if(isDigit(currentChar)) {
					estado = 3;
					termo += currentChar; // armazena cada char
				}
				else if(!isChar(currentChar)) { // leu qualquer coisa que não é um caracter
					estado = 4; 
				}
				else {
					throw new IsiLexicaException(" Número não reconhecido"); // se leu um caracter
				}
				break;
			case 4:// tokens -> estado 4 (final)
				token = new Token();
				token.setType(Token.tk_numeros);
				//token.setText(termo);
				back();
				return token;
			case 5: 
				termo += currentChar;
				token = new Token();
				token.setType(Token.tk_operador);
				//token.setText(termo);
				return token;
			}
			
		}
	}
	
	// 2º - criando funções utilitárias para verificar os tokens
	private boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}
	
	private boolean isChar(char c) {
		return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
	}
	
	private boolean isOperator(char c) {
		return c == '>' || c == '<' || c == '=' || c == '!';
	}	
	
	private boolean isSpace(char c) {
		return c == ' ' || c == '\t' || c == '\n' || c == '\r';
	}
	
	private char nextChar() {
		return content[posicao++];
	}
	
	private boolean isFinalArquivo() {
		return posicao == content.length;
	}
	
	private void back() {
		posicao-- ;
	}

}
