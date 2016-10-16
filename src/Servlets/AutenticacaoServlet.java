package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.JLXException;
import Model.Usuario;
import Repository.UsuarioRepository;

/**
 * Servlet implementation class AutenticacaoServlet
 */
public class AutenticacaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UsuarioRepository usuariosRep;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutenticacaoServlet() {
        super();
        usuariosRep = UsuarioRepository.repositorioUsuario();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login = (String) request.getParameter("login");
		String senha = (String) request.getParameter("senha");
		Usuario novoUsuario = null;
		try{
			novoUsuario = new Usuario(login,senha);
		}catch(JLXException ex){
			response.getOutputStream().println("<script>alert('"+ex.getTitulo()+"\n"+ex.getMensagem()+"');</script>");
		}
		finally{
			if(novoUsuario!=null){
				usuariosRep.salvarUsuario(novoUsuario);
				System.out.println("cadastrado usuario com sucesso");
				response.getOutputStream().println("<script>alert('Cadastro efetuado com sucesso!');</script>");
				response.sendRedirect("index.jsp");
			}else{
				System.out.println("nao conseguiu cadastrar usuario");
				response.getOutputStream().println("<script>alert('CADASTRO NÃO FOI EFETUADO CORRETAMENTE! (tente informar outro username, ou verificar sua conexão)');</script>");
				response.sendRedirect("View/cadastrarUsuarioView.jsp");
			}						
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = (String) request.getParameter("login");
		String senha = (String) request.getParameter("senha");
		Usuario usuario = usuariosRep.obterPorNomeESenha(login!=null?login:"b", senha!=null?senha:"c");
		HttpSession sessaoAtual = request.getSession();
		if(usuario==null){
			System.out.println("Login ou senha invalidos!");
			response.sendRedirect("index.jsp");
			response.getOutputStream().println("<script>alert('LOGIN OU SENHA INVÁLIDOS!');</script>");
		}
		else{
			System.out.println("Conseguiu logar!");
			sessaoAtual.setAttribute("user", usuario);
			response.sendRedirect("View/homeView.jsp");
		}
	}
	
}
