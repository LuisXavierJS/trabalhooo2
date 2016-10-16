package Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Model.Movimento;
import Model.Personagem;

public class MovimentoRepository {
	private static MovimentoRepository movimentoRepositorioSingleton;
	EntityManagerFactory movimentosMFactory;
	EntityManager movimentosManager;
	
	public static MovimentoRepository repositorioMovimento(){
		if(movimentoRepositorioSingleton==null){
			movimentoRepositorioSingleton = new MovimentoRepository();
		}
		return movimentoRepositorioSingleton;
	}
	
	protected MovimentoRepository(){
		super();
		movimentosMFactory = Persistence.createEntityManagerFactory("trabalhoOO2");
		movimentosManager = movimentosMFactory.createEntityManager();
	}
	
	public void salvarMovimento(Movimento move){
		movimentosManager.getTransaction().begin();
		movimentosManager.persist(move);// or em.merge(character);
		movimentosManager.getTransaction().commit();
		movimentosMFactory.close();
	}
	
	public Movimento obterPorID(Integer id){
		movimentosManager.getTransaction().begin();
		Movimento move = movimentosManager.find(Movimento.class, id);
		movimentosManager.getTransaction().commit();
		movimentosMFactory.close();
		return move;
	}
	
	@SuppressWarnings("unchecked")
	public List<Movimento> listarTodosDoPersonagem(Personagem characterOwner){
		movimentosManager.getTransaction().begin();
		Query consulta = movimentosManager
		.createQuery("SELECT movimentos FROM Movimento movimentos where movimentos.characterOwner = :characterOwner");
		consulta.setParameter("characterOwner", characterOwner);
		List<Movimento> movimentos = (List<Movimento>) consulta.getResultList();
		movimentosManager.getTransaction().commit();
		movimentosMFactory.close();
		return movimentos;
	}
}
