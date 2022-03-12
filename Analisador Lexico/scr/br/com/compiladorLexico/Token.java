package br.com.compiladorLexico;

public class Token {
	
	// declarando os tokens
	public static final int tk_indentificador = 0;
	public static final int tk_numeros = 1;
	public static final int tk_operador = 2;
	public static final int tk_pontuacao = 3;
	public static final int tk_atribuicao = 4;
	
	private int type;
	private String text;
	
	// criando o construtor
	public Token(int type, String text) {
		super();
		this.type = type;
		//this.text = text;
	}
	
	public Token () {
		super();
	}

	// Todos os gets e sets
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	//public String getText() {
	//	return text;
	//}

	//public void setText(String text) {
		//this.text = text;
	//}

	public static int getTkIndentificador() {
		return tk_indentificador;
	}

	public static int getTkNumeros() {
		return tk_numeros;
	}

	public static int getTkOperador() {
		return tk_operador;
	}

	public static int getTkPontuacao() {
		return tk_pontuacao;
	}

	public static int getTkAtribuicao() {
		return tk_atribuicao;
	}
	
	@Override
	public String toString() {
		//return "Token [type=" + type + ", text=" + "]";
		return "Token [type=" + type + "]";
	}
}


