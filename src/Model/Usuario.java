package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="jorge_usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer ID_Usuario;
	
	@Column(unique=true)
	private String nome;
	
	@Column
	private String senha;
	
	@Column
	private Double pontos;
	
	public Usuario(){}
	
	public Usuario(String nome, String senha) throws JLXException{
		pontos = 100.0;
		setNome(nome);
		setSenha(senha);
	}

	public Integer getID_Usuario() {
		return ID_Usuario;
	}

	public void setID_Usuario(Integer iD_Usuario) {
		ID_Usuario = iD_Usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome)  throws JLXException{
		if(nome.length()>25||nome.length()<5){
			JLXException ex = new JLXException("O nome de usuário deve ter entre 5 e 25 caracteres.","USERNAME INVÁLIDO");
			throw ex;
		}
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) throws JLXException{
		if(senha.length()>30||senha.length()<6){
			JLXException ex = new JLXException("A senha de usuário deve ter entre 6 e 30 caracteres.","SENHA INVÁLIDA");
			throw ex;
		}
		this.senha = senha;
	}

	public Double getPontos() {
		return pontos;
	}

	public void setPontos(Double pontos) {
		this.pontos = pontos;
	}
	
	
}
