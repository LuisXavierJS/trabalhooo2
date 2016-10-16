package Servlets;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import Model.Movimento;
import Model.Personagem;
import Model.Usuario;
import Repository.MovimentoRepository;

/**
 * Servlet implementation class DetalhePersonagemServlet
 */
public class DetalhePersonagemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MovimentoRepository moveRep;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetalhePersonagemServlet() {
        super();
        moveRep = MovimentoRepository.repositorioMovimento();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//PEGA OS DADOS DO PERSONAGEM
		Integer index = Integer.parseInt(request.getParameter("indexPersonagemNaLista"));
		List<Personagem> personagens = (List<Personagem>) request.getSession().getAttribute("personagens");
		
		Personagem personagem = personagens.get(index);
		
		List<Movimento> movimentosDoPersonagem = moveRep.listarTodosDoPersonagem(personagem);
		
		request.getSession().setAttribute("lastSelectedCharacter", personagem);
		request.getSession().setAttribute("lastSelectedCharacterMoves", movimentosDoPersonagem);
		
		response.sendRedirect("View/detalhesPersonagemView.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Pegar os Dados
		Usuario user = (Usuario) request.getSession().getAttribute("user");	
		Personagem personagem = (Personagem) request.getSession().getAttribute("lastSelectedCharacter");
		List<Movimento> movimentos = (List<Movimento>) request.getSession().getAttribute("lastSelectedCharacterMoves");
		Image imagemPersonagem = null;
		if(request.getParameter("gerarImagem")!=null&&request.getParameter("gerarImagem").equals("gerarImagem")){
			try {
				imagemPersonagem = Image.getInstance(new URL(personagem.getUrlImagemPersonagem()));
			} catch (BadElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//GERA PDF COM OS DADOS DO PERSONAGEM
        response.setContentType("application/pdf");
        try {
            // step 1
            Document document = new Document();
            // step 2
            PdfWriter.getInstance(document, response.getOutputStream());
            // step 3
            document.open();
            // step 4
            document.add(new Paragraph("Usuário:"+user.getNome()));
            
            if(imagemPersonagem!=null){
            	document.add(imagemPersonagem);
            }
            
            document.add(new Paragraph("Nome:"+personagem.getNomePersonagem()));
            document.add(new Paragraph("Classe:"+personagem.getClassePersonagem()));
            document.add(new Paragraph("Poder de Luta:"+personagem.getPoderDoPersonagem()));
            document.add(new Paragraph("___________________________________________________________________"));
            document.add(new Paragraph(""));
            document.add(new Paragraph("HitPoints:"+personagem.getHp()));
            document.add(new Paragraph("Ataque Físico:"+personagem.getAtaqueFisico()));
            document.add(new Paragraph("Ataque Especial:"+personagem.getAtaqueEspecial()));
            document.add(new Paragraph("Defesa Física:"+personagem.getDefesaFisica()));
            document.add(new Paragraph("Defesa Especial:"+personagem.getDefesaEspecial()));
            document.add(new Paragraph("Velocidade:"+personagem.getVelocidade()));
            
            for(Movimento m : movimentos){
            	document.add(new Paragraph("-------------------------------------------------------------------------"));
            	document.add(new Paragraph("Nome:"+m.getNomeMovimento()));
            	document.add(new Paragraph("Classificação:"+m.getClassificacaoMovimento()));
            	document.add(new Paragraph("Poder:"+m.getPoderMovimento()));
            	document.add(new Paragraph("Precisão:"+m.getPrecisaoMovimento()));
            	document.add(new Paragraph(""));
            }
            // step 5
            document.close();
        } catch (DocumentException de) {
            throw new IOException(de.getMessage());
        }
	}

}
