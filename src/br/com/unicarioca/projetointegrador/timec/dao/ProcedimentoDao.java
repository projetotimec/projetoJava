package br.com.unicarioca.projetointegrador.timec.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.unicarioca.projetointegrador.timec.model.ExameModel;
import br.com.unicarioca.projetointegrador.timec.model.ProcedimentoModel;

@Stateless
public class ProcedimentoDao extends AbstractFacade<ProcedimentoModel> {

	@PersistenceContext(unitName = "ProjetoIntegradorPU")
	private EntityManager em;

	
	
	public ProcedimentoDao() {
		super(ProcedimentoModel.class);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}

	
	
	public ProcedimentoModel buscarPorProcedimentoPorId(Integer IdExame){
		return em.find(ProcedimentoModel.class, IdExame);
	}

/*enginner: jeison muniz
 * backlog: solicitacao de reembolso
 * Background:  ProcedimentoModel
 * Scenario: buscar todos os procedimentos com procPagamento "1"
String valor = "1";Exame foi realizado pagamento pelo procedimento 
String valor = "0";Exame não foi realizado pagamento pelo procedimento 
*/
	
	
	public ProcedimentoModel buscarExamePorProcedimentosPagos(Integer IdExame){
		Integer pagamentoRealizado = 1;
		
		try {
			Query query = em.createQuery("SELECT p FROM ProcedimentoModel p WHERE p.codigoExame = :exame AND p.procPagamento =:v", ProcedimentoModel.class)
					.setParameter("v", pagamentoRealizado).setParameter("exame", IdExame);
			return (ProcedimentoModel) query.getSingleResult();
		} catch (Exception e) {

		}

		return null;
	}
	
	
}
