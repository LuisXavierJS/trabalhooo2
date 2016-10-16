package Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Model.Personagem;
import Model.Usuario;

public class PersonagemRepository {
	EntityManagerFactory personagensMFactory;
	EntityManager personagensManager;
	private static PersonagemRepository repositorioSingleton;

	public static PersonagemRepository repositorioPersonagem(){
		if(repositorioSingleton==null){
			repositorioSingleton = new PersonagemRepository();
		}
		return repositorioSingleton;
	}
	
	protected PersonagemRepository(){
		super();
		personagensMFactory = Persistence.createEntityManagerFactory("trabalhoOO2");
		personagensManager = personagensMFactory.createEntityManager();
	}
	
	public void salvarPersonagem(Personagem character){
		personagensManager.getTransaction().begin();
		personagensManager.persist(character);// or em.merge(character);
		personagensManager.getTransaction().commit();
		personagensMFactory.close();
	}
	
	public Personagem obterPorID(Integer id){
		personagensManager.getTransaction().begin();
		Personagem character = personagensManager.find(Personagem.class, id);
		personagensManager.getTransaction().commit();
		personagensMFactory.close();
		return character;
	}
	
	@SuppressWarnings("unchecked")
	public List<Personagem> listarTodosDoUsuario(Usuario userOwner){
		personagensManager.getTransaction().begin();
		Query consulta = personagensManager
		.createQuery("SELECT personagens FROM Personagem personagens where personagens.userOwner = :owner");
		consulta.setParameter("owner",userOwner);
		List<Personagem> personagens = (List<Personagem>) consulta.getResultList();
		personagensManager.getTransaction().commit();
		personagensMFactory.close();
		return personagens;
	}
}
