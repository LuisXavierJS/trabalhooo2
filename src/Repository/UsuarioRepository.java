package Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Model.Usuario;

public class UsuarioRepository {
	private static UsuarioRepository repositorioSingleton;
	EntityManagerFactory usuariosMFactory;
	EntityManager usuariosManager;
	
	public static UsuarioRepository repositorioUsuario(){
		if(repositorioSingleton==null){
			repositorioSingleton = new UsuarioRepository();
		}
		return repositorioSingleton;
	}
	
	protected UsuarioRepository(){
		super();
		usuariosMFactory = Persistence.createEntityManagerFactory("trabalhoOO2");
		usuariosManager = usuariosMFactory.createEntityManager();
	}
	
	public void salvarUsuario(Usuario user){
		usuariosManager.getTransaction().begin();
		usuariosManager.persist(user);// or em.merge(user);
		usuariosManager.getTransaction().commit();
		usuariosMFactory.close();
	}
	
	public Usuario obterPorID(Integer id){
		usuariosManager.getTransaction().begin();
		Usuario p = usuariosManager.find(Usuario.class, id);
		usuariosManager.getTransaction().commit();
		usuariosMFactory.close();
		return p;
	}
	
	@SuppressWarnings("unchecked")
	public Usuario obterPorNomeESenha(String nome, String senha){
		usuariosManager.getTransaction().begin();
		Query consulta = usuariosManager.createQuery("SELECT usuarios FROM Usuario usuarios where usuarios.nome=:nome and usuarios.senha=:senha");
		consulta.setParameter("nome", nome);
		consulta.setParameter("senha", senha);
		List<Usuario> usuarios = (List<Usuario>) consulta.getResultList();
		usuariosManager.getTransaction().commit();
		usuariosMFactory.close();
		for(Usuario u : usuarios){
			System.out.println(u.getNome()+"\n");
		}
		 if(usuarios!=null&&!usuarios.isEmpty()) return usuarios.get(0); else return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> listarTodos(){
		usuariosManager.getTransaction().begin();
		Query consulta = usuariosManager.createQuery("SELECT usuarios FROM Usuario usuarios");
		List<Usuario> usuarios = (List<Usuario>) consulta.getResultList();
		usuariosManager.getTransaction().commit();
		usuariosMFactory.close();
		return usuarios;
	}
}
