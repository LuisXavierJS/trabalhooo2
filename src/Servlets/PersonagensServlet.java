package Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Movimento;
import Model.Personagem;
import Model.Usuario;
import Repository.MovimentoRepository;
import Repository.PersonagemRepository;

/**
 * Servlet implementation class PersonagensServlet
 */
public class PersonagensServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PersonagemRepository personagemRepo;   
    private MovimentoRepository movimentoRepo;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonagensServlet() {
        super();
        movimentoRepo = MovimentoRepository.repositorioMovimento();
        personagemRepo = PersonagemRepository.repositorioPersonagem();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Vai pegar a lista de todos os personagens
		if(request.getSession().getAttribute("user")==null)response.sendRedirect("index.jsp");
		Usuario userAtual = (Usuario) request.getSession().getAttribute("user");
		List<Personagem> personagens = personagemRepo.listarTodosDoUsuario(userAtual);
		request.getSession().setAttribute("personagens", personagens);
		response.sendRedirect("View/visualizarPersonagensView.jsp");
	}

	/**				 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)

		return '<tr><th>Poder do Movimento:</th><th><input type="number" class="atributoAtkMove" name="poder'+ nMovimentos +'" id="poder'+ nMovimentos +'" min="10" max="150" value="10" step="10"></th></tr>';
		return '<tr><th><input type="radio"  name="classeMovimento'+nMovimentos+'" value="Fisico" checked>Físico</th>';
		return '<th><input type="radio"  name="classeMovimento'+nMovimentos+'" value="Magico">Mágico</th></th></tr>';
		return '<table class="movimentoX'+nMovimentos+'"><tr><th>Nome do Movimento:</th><th><input type="text" size="20" maxlength="22" name="nomeMovimento' + nMovimentos +'" id="nomeMovimento' + nMovimentos +'" placeholder="Nome do Movimento"/></th></tr>';
		return '<tr><th>Precisão do Movimento:</th><th><input type="number" class="atributoAccMove" name="precisao'+ nMovimentos +'" id="precisao'+ nMovimentos +'" min="10" max="100" value="100" step="5">'+'</table></th></tr>';
<tr><th>Ataque Físico:</th><th><input type="number" class="atributoPers" name="atk" id="atk" min="1" max="15" value="1"></th></tr>
<tr><th>Ataque Especial:</th><th><input type="number" class="atributoPers" name="spAtk" id="spAtk" min="1" max="15" value="1"></th></tr>
<tr><th>Defesa Física:</th><th><input type="number" class="atributoPers" name="def" id="def" min="1" max="15" value="1"></th></tr>
<tr><th>Defesa Especial:</th><th><input type="number" class="atributoPers" name="spDef" id="spDef" min="1" max="15" value="1"></th></tr>
<tr><th>Velocidade:</th><th><input type="number" class="atributoPers" name="veloc" id="veloc" min="1" max="15" value="1"></th></tr>
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Vai cadastrar um novo personagem no banco
		Usuario userAtual = (Usuario) request.getSession().getAttribute("user");
		Double pontosGastos = (Double) request.getSession().getAttribute("pontosGastos");

		userAtual.setPontos(userAtual.getPontos()-pontosGastos);
		String imgPersonagem = (String) request.getParameter("imgPers");
		String nomePersonagem = (String) request.getParameter("nomePers");
		String classePersonagem = (String) request.getParameter("classePers");
		Double hpPersonagem = Double.parseDouble( request.getParameter("hp"));
		Double atkFPersonagem = Double.parseDouble( request.getParameter("atk"));
		Double atkMPersonagem = Double.parseDouble( request.getParameter("spAtk"));
		Double defFPersonagem = Double.parseDouble( request.getParameter("def"));
		Double defMPersonagem = Double.parseDouble( request.getParameter("spDef"));
		Double speedPersonagem = Double.parseDouble( request.getParameter("veloc"));
		//First: criar o Personagem e salvar no banco
		Personagem novoPersonagem = new Personagem(userAtual,nomePersonagem,classePersonagem,hpPersonagem,atkFPersonagem,atkMPersonagem,defFPersonagem,defMPersonagem,speedPersonagem,imgPersonagem,pontosGastos);
		personagemRepo.salvarPersonagem(novoPersonagem);
		//Second: criar uma lista de Movimentos, e armazenar cada movimento no banco, usando o personagem criado como set.
		Double nMov = (Double) request.getSession().getAttribute("nMovimentos");
		for(int i=1;i<=nMov;i++){
			String nomeMovimento = (String) request.getParameter("nomeMovimento"+i);
			String classeMovimento = (String) request.getParameter("classeMovimento"+i);
			Double poderMovimento = Double.parseDouble( request.getParameter("poder"+i));
			Double precisaoMovimento = Double.parseDouble( request.getParameter("precisao"+i));
			Movimento novoMovimento = new Movimento(novoPersonagem,nomeMovimento,poderMovimento,precisaoMovimento,classeMovimento);
			movimentoRepo.salvarMovimento(novoMovimento);
		}
		response.sendRedirect("View/homeView.jsp");
		request.getSession().setAttribute("pontosGastos",0);
		request.getSession().setAttribute("nMovimentos", 0);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		if(request.getSession().getAttribute("user")!=null){
			String alvo = (String) request.getParameter("alvo");
			if(alvo.equals("cadastro")){
				response.sendRedirect("View/cadastrarPersonagemView.jsp");
			}else{
				response.sendRedirect("View/visualizarPersonagensView.jsp");
			}
		}
	}
	
}

