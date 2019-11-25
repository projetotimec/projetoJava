package br.com.unicarioca.projetointegrador.timec.ws;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import br.com.unicarioca.projetointegrador.timec.business.BeneficiarioBusiness;
import br.com.unicarioca.projetointegrador.timec.business.CoreBusiness;
import br.com.unicarioca.projetointegrador.timec.business.EmailBusiness;
import br.com.unicarioca.projetointegrador.timec.business.ExameBusiness;
import br.com.unicarioca.projetointegrador.timec.business.ReembolsoBusiness;
import br.com.unicarioca.projetointegrador.timec.dao.ProcedimentoDao;
import br.com.unicarioca.projetointegrador.timec.model.BeneficiarioModel;
import br.com.unicarioca.projetointegrador.timec.model.ExameModel;
import br.com.unicarioca.projetointegrador.timec.model.ProcedimentoModel;
import br.com.unicarioca.projetointegrador.timec.model.ReembolsoModel;
import br.com.unicarioca.projetointegrador.timec.vo.AnaliseReembolsoVo;
import br.com.unicarioca.projetointegrador.timec.vo.ReembolsoVo;

/*
 * UNICARIOCA
 * autor jeison muniz
 * TIME C - PROJETO INTEGRADOR - BDD
 * 
 * Background:
 * Scenario:
 * Given:
 * And:
 * But:
 * When:
 * then: */
 
/*Background:DEPLOY JAVA APPLICATION SSH NO SERVIDOR GOOGLE
 * Scenario:DEPLOY 
 * Given: PROTOTIPO.WAR 
 * And: VM UBUNTU 
 * then:  SERVIDOR GOOGLE */

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public class WebServico {

	@EJB
	EmailBusiness emailBusiness;
	
	@EJB
	CoreBusiness coreBusiness;
	
	@EJB
	BeneficiarioBusiness beneficiarioBusiness ;

	@EJB
	ExameBusiness notificacaoExame ;
	

	@EJB
	ExameBusiness exameBusiness;
	
	@EJB
	ReembolsoBusiness reembolsoBusiness;
	@EJB
	ProcedimentoDao procedimentoDao ;
	/* * Background:
 * Scenario:
 * Given:
 * And:
 * But:
 * When:
 * then: */
	
	@WebMethod
	public String teste() {
		// TODO Auto-generated method stub
		return "TIME C -TESTE DO SERVICE - SOLICIR REEMBOLSO";
	}
	
	
	@WebMethod
	public ReembolsoModel criarReembolso(ReembolsoModel r) {
		// TODO Auto-generated method stub
		return reembolsoBusiness.criarReembolso(r);
	}
	

	
	@WebMethod
	public String solicitarReembolso(String email,String telefone,String endereco,String motivo , String matricula) {
		// TODO Auto-generated method stub
		return reembolsoBusiness.solicitacaoDeReembolso(email, telefone, endereco, motivo, matricula);
	}
	
	
	/* * Background:
 * Scenario:
 * Given:
 * And:
 * But:
 * When:
 * then: */
	
	@WebMethod
	public List<BeneficiarioModel> buscarTodosBeneficiarios() {
		// TODO Auto-generated method stub
		return beneficiarioBusiness.listarBeneficiarios();
	}
	

	
	/* * Background:
 * Scenario:
 * Given:
 * And:
 * But:
 * When:
 * then: */
	
	@WebMethod
	public List<ExameModel> buscarTodosExames() {
		// TODO Auto-generated method stub
		return notificacaoExame.listar();
	}
	
	@WebMethod
	public ExameModel atualizarExame(ExameModel e) {
		// TODO Auto-generated method stub
		return exameBusiness.atualizarExames(e);
	}
	
	
	@WebMethod
	public ExameModel criarExame(ExameModel e) {
		// TODO Auto-generated method stub
		return exameBusiness.criarExame(e);
	}
	
	@WebMethod
	public BeneficiarioModel criarBeneficiario(BeneficiarioModel e) {
		// TODO Auto-generated method stub
		return  beneficiarioBusiness.criar(e);
	}

	

	@WebMethod
	public List<ExameModel> buscarTodosAgendamentos() {
		// TODO Auto-generated method stub
		return notificacaoExame.listarAgendados();
	}
	
	@WebMethod
	public List<ExameModel> buscarExamesNotificados() {
		// TODO Auto-generated method stub
		return exameBusiness.listarExamesNotificados();
	}
	
	@WebMethod
	public String notificarExame() {
		 System.out.println("INICIALIZAR VARREDURA DE NOTIFICAÇÃO");
		  List<ExameModel> examesNotificados =  exameBusiness.noficicarExame();
		  String resultado = " EXAMES NOTIFICADOS : " + examesNotificados.size();
	      
		return resultado;
	}
	 
	
	@WebMethod
	public void enviarEmail(String emailDestino,String descricao, String assunto) {
	//	emailBusiness = new EmailBusiness();
		emailBusiness.enviarEmail(emailDestino, descricao, assunto);
		
	}
	
	
	
	@WebMethod
	public AnaliseReembolsoVo analiseDeReembolso(Integer r) {
		// TODO Auto-generated method stub
		return reembolsoBusiness.analiseDeReembolso(r);
	}
	
	@WebMethod
	public ReembolsoVo solicitarDeReembolsoNovo(Integer r) {
		// TODO Auto-generated method stub
		return reembolsoBusiness.solicitaReembolsoPorExame(r);
	}
	
	@WebMethod
	public ProcedimentoModel buscarProcedimentoPorExame(Integer id) {
		// TODO Auto-generated method stub
		return procedimentoDao.buscarExamePorProcedimentosPagos(id);
	}
	
	
	@WebMethod
	public List<ProcedimentoModel> buscarTodosProcedimentos() {
		// TODO Auto-generated method stub
		return procedimentoDao.findAll();
	}
	@WebMethod
	public ProcedimentoModel buscarProcedimentosPorId(Integer id) {
		// TODO Auto-generated method stub
		return procedimentoDao.buscarPorProcedimentoPorId(id);
	}
	
}
	