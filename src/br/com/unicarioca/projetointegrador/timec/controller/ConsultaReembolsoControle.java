package br.com.unicarioca.projetointegrador.timec.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.unicarioca.projetointegrador.timec.business.ReembolsoBusiness;
import br.com.unicarioca.projetointegrador.timec.vo.AnaliseReembolsoVo;


@WebServlet("/ConsultaReembolsoControle")
public class ConsultaReembolsoControle  extends HttpServlet {
		private static final long serialVersionUID = 1L;
	       
		
		@EJB
		ReembolsoBusiness reembolsoBusiness ;
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public ConsultaReembolsoControle() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			response.getWriter().append("Served at: ").append(request.getContextPath());
		//	String nome = request.getParameter("name").toString();
		   
		
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
			   HttpSession session = request.getSession();
			   String matricula = request.getParameter("matricula").toString();
			 String consulta =   reembolsoBusiness.buscarSolicitacaoDeReembolso(Integer.parseInt(matricula));
			   
			 AnaliseReembolsoVo vo = reembolsoBusiness.analiseDeReembolso(Integer.parseInt(matricula));
			System.out.println(" RESULTADO ANALISE DE REEMBOLSO " + vo);
			 response.sendRedirect("consulta.jsp");
			   session.setAttribute("CONSULTA",consulta);
					

		
				
		}

	}
