package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Usuario;

/**
 * Servlet implementation class PontosUsuarioServlet
 */
public class PontosUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PontosUsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Pega numero atual numero de pontos
		if(request.getSession().getAttribute("user")==null)response.sendRedirect("index.jsp");
		Usuario userAtual = (Usuario) request.getSession().getAttribute("user");
		response.getOutputStream().print(userAtual.getPontos());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Double numeroMovimentos = (Double) Double.parseDouble(request.getParameter("movimentos"));
		Double numeroPontosGastos = (Double) Double.parseDouble(request.getParameter("pGastos"));
		request.getSession().setAttribute("nMovimentos", numeroMovimentos);
		request.getSession().setAttribute("pontosGastos", numeroPontosGastos);
		String nmov = (String)""+ request.getSession().getAttribute("nMovimentos");
		String nmov2 = (String)""+ request.getSession().getAttribute("pontosGastos");
		response.getOutputStream().print(nmov + "/" + nmov2);
	}
	
}
