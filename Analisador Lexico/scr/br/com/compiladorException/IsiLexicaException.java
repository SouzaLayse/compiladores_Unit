package br.com.compiladorException;

// criando a própria exceção
// extends -> estendendo outra classe

public class IsiLexicaException extends RuntimeException {
	
	public IsiLexicaException(String mensagem) {
		super(mensagem);
	}

}


