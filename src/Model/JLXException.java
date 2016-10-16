package Model;

import java.io.IOException;

public class JLXException extends IOException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String mensagem;
	String titulo;
	public JLXException(String mensagem, String titulo){
		super();
		setMensagem(mensagem);
		setTitulo(titulo);
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
}
