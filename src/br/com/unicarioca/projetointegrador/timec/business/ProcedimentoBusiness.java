package br.com.unicarioca.projetointegrador.timec.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.unicarioca.projetointegrador.timec.dao.ProcedimentoDao;
import br.com.unicarioca.projetointegrador.timec.model.ProcedimentoModel;

@Stateless
public class ProcedimentoBusiness {

	
	
	
	@EJB
	private ProcedimentoDao procedimento;
	
	
	/*
	 * enginner: jeison muniz 
	 * backlog: solicitacao de reembolso 
	 * Background:
	 * ProcedimentoModel 
	 * Scenario: buscar todos os procedimentos com procPagamento = "1" 
	 * String valor = "1";Exame foi realizado pagamento pelo procedimento 
	 * String * valor = "0";Exame não foi realizado pagamento pelo procedimento
	 */
	
	public ProcedimentoModel buscarExamePorProcedimentosPagos(Integer IdExame){
		

		return procedimento.buscarExamePorProcedimentosPagos(IdExame);
	}
	
}
