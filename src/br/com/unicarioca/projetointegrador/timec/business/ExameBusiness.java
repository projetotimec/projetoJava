package br.com.unicarioca.projetointegrador.timec.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.Query;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;

import br.com.unicarioca.projetointegrador.timec.dao.ExameDao;
import br.com.unicarioca.projetointegrador.timec.dao.NotificacaoDao;
import br.com.unicarioca.projetointegrador.timec.model.BeneficiarioModel;
import br.com.unicarioca.projetointegrador.timec.model.ExameModel;


/*autor : jeison muniz
 * backlog: Push de Notifica��o por email de Agendamento de Exame 
 * Background:  
 * Scenario: Eu como benefici�rio da empresa 
 * desejo receber uma notifica��o do agendamento 
 * do exame para saber que o exame foi confirmado
*/




@Stateless
public class ExameBusiness {
	
	
	@EJB
	NotificacaoDao notificacaoDao;

	@EJB
	ExameDao exameDao ;
	
	@EJB
	BeneficiarioBusiness beneficiarioBusiness;
	
	@EJB
	EmailBusiness emailBusiness;
	
	
	
	
	
	
	
    public ExameModel criarExame(ExameModel e){
		
		return exameDao.criarExame(e);
		
	}
	
	
	
	
	 /*Enginner: jeison muniz
	 *backlog: 
	 *Background: buscar todos os exames
	 *cenario:   buscar todos os exames
	 

	*/
	
	public List<ExameModel> listar(){
		
		return exameDao.findAll();
		
	}
	
	  /*Enginner: jeison muniz
		 *backlog: 
		 *Background: buscar exames  "Realizado"
		 *cenario:  buscar exames  "Realizado" 
		 

		*/
	
  public List<ExameModel> listarAgendados(){

	   
   return exameDao.localizarExames();
		
	}
  
  /*Enginner: jeison muniz
	 *backlog: 
	 *Background: atualizar o campo ds_notificacao
	 *cenario:  atualizar o campo ds_notificacao 
	  Dado uma entidade  ExameModel

	*/
  public ExameModel atualizarExames(ExameModel e) {
	
	 return  exameDao.atualizar(e);
  }
	
	/*autor: jeison muniz
	 *backlog: 
	 *Background: Push de Notifica��o por email de Agendamento de Exame 
	 *cenario:  Enviar confirma��o do agendamento do exame 
	  Dado que o agendamento do exame foi feito
	  o recebe a consulta de todos os registro com 
	  status "Agendado" pega o email do beneficiario associado 
	  e realiza o envio de email
	  e faz o registro no banco de "Enviado"
	
	*/
	
   
   public List<ExameModel> noficicarExame() {

	  
	   List<ExameModel> listaExames = exameDao.localizarExamesNaoNotificados();
	   List<ExameModel> examesNotificados = new ArrayList<>();
	   
	   for (ExameModel e : listaExames) {
		   
			   Integer m = e.getIdBeneficiario();
			   BeneficiarioModel b = beneficiarioBusiness.bucarBeneficiariosPorMatricula(m);
		 
			   emailBusiness.enviarEmail(b.getEmail(), e.getDescricao(), e.getStatus());
			  
			   e.setDsNotificacao("ENVIADO");  
			   examesNotificados.add(notificacaoDao.atualizar(e));
			 
	   }
	 return examesNotificados;
  }
	  
   
   
   /*Enginner: jeison muniz
	 *backlog: 
	 *Background: buscar todos os exames notificados
	 *cenario:  buscar todos os exames j� notificados 
	 *e alterar a base de dados para n�o ocorrer reenvio
	 

	*/
	public List<ExameModel> resgistrarProcesso() {
		
		List<ExameModel> listaExames =  exameDao.localizarExamesNotificados();
		List<ExameModel> examesNotificados = new ArrayList<>();
		   
		   for (ExameModel e : listaExames) {
			   
				   e.setDsNotificacao("PROCESSADOS");  
				   examesNotificados.add(notificacaoDao.atualizar(e));
		
				 
		   }
		 return examesNotificados;
		 
	}
   
   /*Enginner: jeison muniz
 	 *backlog: 
 	 *Background: buscar todos os exames notificados
 	 *cenario:  buscar todos os exames  notificados 
 	 

 	*/
	public List<ExameModel> listarExamesNotificados() {
		return  exameDao.localizarExamesNotificados();
		 
	}
	   
	
	
	
	   /*Enginner: jeison muniz
	 	 *backlog: 
	 	 *Background: buscar todos os exames com n�o notificados
	 	 *cenario:  buscar todos os exames com n�o notificados 
	 	 

	 	*/
	   
	   public List<ExameModel> listarExamesSemNotificacao() {

		   return exameDao.localizarExamesNaoNotificados();	  
    }
	   
	   

/*enginner: jeison muniz
 * backlog: reembolso
 * Background:  ExameBusiness
 * Scenario: buscar todos os exames com por id beneficiario 

*/
	
	
	public List<ExameModel> localizarExamesPorIdBenficiario(Integer valor){
		
	
		return exameDao.localizarExamesPorIdBenficiario(valor);
	}
	
	/*enginner: jeison muniz
	 * backlog: reembolso
	 * Background:  ExameBusiness
	 * Scenario: buscar exame por id  

	*/
	
	public ExameModel localizarExamesPorId(Integer valor){
		
		
		return exameDao.find(valor);
	}
	
	

	

}
