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
@Table(name="jorge_movimento")
public class Movimento {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer ID_Movimento;
	
	@ManyToOne
	@JoinColumn(name="ID_Personagem")
	private Personagem characterOwner;

	@Column(unique=true)
	private String nomeMovimento;//Qualquer nome de 0 a 30 caracteres
	@Column
	private Double poderMovimento;//0 a 150
	@Column
	private Double precisaoMovimento;//0 a 100
	@Column
	private String classificacaoMovimento;//Mágico ou Físico
	

	public Personagem getCharacterOwner() {
		return characterOwner;
	}

	public void setCharacterOwner(Personagem characterOwner) {
		this.characterOwner = characterOwner;
	}

	public String getNomeMovimento() {
		return nomeMovimento;
	}

	public void setNomeMovimento(String nomeMovimento) {
		this.nomeMovimento = nomeMovimento;
	}

	public Double getPoderMovimento() {
		return poderMovimento;
	}

	public void setPoderMovimento(Double poderMovimento) {
		this.poderMovimento = poderMovimento;
	}

	public Double getPrecisaoMovimento() {
		return precisaoMovimento;
	}

	public void setPrecisaoMovimento(Double precisaoMovimento) {
		this.precisaoMovimento = precisaoMovimento;
	}

	public String getClassificacaoMovimento() {
		return classificacaoMovimento;
	}

	public void setClassificacaoMovimento(String classificacaoMovimento) {
		this.classificacaoMovimento = classificacaoMovimento;
	}

	public Movimento(){}
	
	public Movimento(Personagem characterOwner, String nomeMovimento, Double poderMovimento, Double precisaoMovimento,
			String classificacaoMovimento) {
		super();
		this.characterOwner = characterOwner;
		this.nomeMovimento = nomeMovimento;
		this.poderMovimento = poderMovimento;
		this.precisaoMovimento = precisaoMovimento;
		this.classificacaoMovimento = classificacaoMovimento;
	}
	
}
