package br.com.unicarioca.projetointegrador.timec.controller;

import javax.ejb.EJB;

import br.com.unicarioca.projetointegrador.timec.business.ReembolsoBusiness;
import br.com.unicarioca.projetointegrador.timec.vo.AnaliseReembolsoVo;

public class AnaliseReembolsoControle {

	
	
	@EJB
	ReembolsoBusiness reembolsoBusiness ;
	
	
	
	public AnaliseReembolsoControle() {
		// TODO Auto-generated constructor stub
	}
	
	
	public AnaliseReembolsoVo analisarSolicitacaoReembolso  (Integer idBeneficiario) {
		 AnaliseReembolsoVo vo = reembolsoBusiness.analiseDeReembolso(idBeneficiario);
		return vo;
	}
	

}
