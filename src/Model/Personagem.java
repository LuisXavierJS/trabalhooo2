package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="jorge_personagem")
public class Personagem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer ID_Personagem;
	
	@ManyToOne
	@JoinColumn(name="ID_Usuario")
	private Usuario userOwner;
	
	@Column
	private Double poderDoPersonagem;
	@Column
	private String urlImagemPersonagem;
	@Column
	private String nomePersonagem;//Qualquer nome de 0 a 30 caracteres
	@Column
	private String classePersonagem;// Mago ou Guerreiro
	@Column
	private Double hp;
	@Column
	private Double ataqueFisico;
	@Column
	private Double ataqueEspecial;
	@Column
	private Double defesaFisica;
	@Column
	private Double defesaEspecial;
	@Column
	private Double velocidade;
	
	public Usuario getUserOwner() {
		return userOwner;
	}
	public void setUserOwner(Usuario userOwner) {
		this.userOwner = userOwner;
	}
	public String getNomePersonagem() {
		return nomePersonagem;
	}
	public void setNomePersonagem(String nomePersonagem) {
		this.nomePersonagem = nomePersonagem;
	}
	public String getClassePersonagem() {
		return classePersonagem;
	}
	public void setClassePersonagem(String classePersonagem) {
		this.classePersonagem = classePersonagem;
	}
	public Double getAtaqueFisico() {
		return ataqueFisico;
	}
	public void setAtaqueFisico(Double ataqueFisico) {
		this.ataqueFisico = ataqueFisico*10;
	}
	public Double getAtaqueEspecial() {
		return ataqueEspecial;
	}
	public void setAtaqueEspecial(Double ataqueEspecial) {
		this.ataqueEspecial = ataqueEspecial*10;
	}
	public Double getDefesaFisica() {
		return defesaFisica;
	}
	public void setDefesaFisica(Double defesaFisica) {
		this.defesaFisica = defesaFisica*10;
	}
	public Double getDefesaEspecial() {
		return defesaEspecial;
	}
	public void setDefesaEspecial(Double defesaEspecial) {
		this.defesaEspecial = defesaEspecial*10;
	}
	public Double getVelocidade() {
		return velocidade;
	}
	public void setVelocidade(Double velocidade) {
		this.velocidade = velocidade*10;
	}
	public Double getHp() {
		return hp;
	}
	public void setHp(Double hp) {
		this.hp = hp*10;
	}
	public Double getPoderDoPersonagem() {
		return poderDoPersonagem;
	}
	public void setPoderDoPersonagem(Double poderDoPersonagem) {
		this.poderDoPersonagem = poderDoPersonagem;
	}
	public String getUrlImagemPersonagem() {
		return urlImagemPersonagem;
	}
	public void setUrlImagemPersonagem(String urlImagemPersonagem) {
		this.urlImagemPersonagem = urlImagemPersonagem;
	}
	public Integer getID_Personagem() {
		return ID_Personagem;
	}
	public Personagem(){}
	
	public Personagem(Usuario userOwner, String nomePersonagem, String classePersonagem, Double hp, Double ataqueFisico,
			Double ataqueEspecial, Double defesaFisica, Double defesaEspecial, Double velocidade, String urlImagemPersonagem,Double poderDoPersonagem) {
		super();
		this.urlImagemPersonagem = urlImagemPersonagem;
		this.poderDoPersonagem = poderDoPersonagem;
		this.userOwner = userOwner;
		this.nomePersonagem = nomePersonagem;
		this.classePersonagem = classePersonagem;
		this.hp = hp;
		this.ataqueFisico = ataqueFisico;
		this.ataqueEspecial = ataqueEspecial;
		this.defesaFisica = defesaFisica;
		this.defesaEspecial = defesaEspecial;
		this.velocidade = velocidade;
	}
	
	
}
