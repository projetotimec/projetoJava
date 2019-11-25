package br.com.unicarioca.projetointegrador.timec.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import br.com.unicarioca.projetointegrador.timec.model.ExameModel;
import br.com.unicarioca.projetointegrador.timec.model.ReembolsoModel;


/*autor: jeison muniz 1.0
 * backlog: 
 * Background: acesso a banco de dados  
 * Scenario: 
* Given:
  Then:
   And:
*/
@Stateless
public class ExameDao extends AbstractFacade<ExameModel> {

	@PersistenceContext(unitName = "ProjetoIntegradorPU")
	private EntityManager em;

	
	
	public ExameDao() {
		super(ExameModel.class);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}



/*enginner: jeison muniz
 * backlog:
 * Background:  ExameBusiness
 * Scenario: criar exame

*/
	
	
	public ExameModel criarExame(ExameModel r){
		em.persist(r);
		em.flush();
		return em.find(ExameModel.class, r.getExameId());
	}
	
	

	

/*enginner: jeison muniz
 * backlog:
 * Background:  ExameBusiness
 * Scenario: atualizar exame

*/
	
	public ExameModel atualizar(ExameModel r){
		em.merge(r);
		em.flush();
		return em.find(ExameModel.class, r.getExameId());
	}
	
	
	

/*enginner: jeison muniz
 * backlog: reembolso
 * Background:  ExameBusiness
 * Scenario: buscar todos os exames com por id beneficiario 

*/
	
	
	public List<ExameModel> localizarExamesPorIdBenficiario(Integer valor){
		
		try {
			Query query = em.createQuery("SELECT e FROM ExameModel e WHERE e.idBeneficiario = :id", ExameModel.class)
					.setParameter("id", valor);
			return (List<ExameModel>) query.getResultList();
		} catch (Exception e) {

		}

		return null;
	}
	
	


/*enginner: jeison muniz
 * backlog: Push de Notificação por email de Agendamento de Exame
 * Background:  ExameBusiness
 * Scenario: buscar todos os exames com sn_status "Agendado"

*/
	
	
	public List<ExameModel> localizarExames(){
		String valor = "Agendado";
		
		try {
			Query query = em.createQuery("SELECT e FROM ExameModel e WHERE e.status = :tipo", ExameModel.class)
					.setParameter("tipo", valor);
			return (List<ExameModel>) query.getResultList();
		} catch (Exception e) {

		}

		return null;
	}
	
	

/*enginner: jeison muniz
 * backlog: Push de Notificação por email de Agendamento de Exame
 * Background:  ExameBusiness
 * Scenario: buscar todos os exames com ds_notificacao "Enviado"

*/
	public List<ExameModel> localizarExamesNotificados(){
		String valor = "Enviado";
		String statusExame = "Agendado";
		
		try {
			Query query = em.createQuery("SELECT e FROM ExameModel e WHERE e.status = :st AND e.dsNotificacao = :tipo", ExameModel.class)
					.setParameter("st", statusExame)
					.setParameter("tipo", valor);
			return (List<ExameModel>) query.getResultList();
		} catch (Exception e) {

		}

		return null;
	}
	
	

/*enginner: jeison muniz
 * backlog: Push de Notificação por email de Agendamento de Exame
 * Background:  ExameBusiness
 * Scenario: buscar todos os exames com 
 * ds_notificacao "PROCESSANDO" e sn_status "Agendado"


select * from exame where sn_status = "Agendado" and ds_notificacao = "" or ds_notificacao is null  ;

*/
	public List<ExameModel> localizarExamesNaoNotificados(){
		String notificacaoExame = "";
		String statusExame = "Agendado";
		
		try {
			Query query = em.createQuery("SELECT e FROM ExameModel e WHERE e.status = :st AND  e.dsNotificacao = :nt OR e.dsNotificacao IS NULL", ExameModel.class)
					 .setParameter("nt", notificacaoExame)		        
					.setParameter("st", statusExame);
			return (List<ExameModel>) query.getResultList();
		} catch (Exception e) {

		}

		return null;
	}

}
