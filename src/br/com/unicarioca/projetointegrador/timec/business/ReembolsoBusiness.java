package br.com.unicarioca.projetointegrador.timec.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.unicarioca.projetointegrador.timec.dao.ReembolsoDao;
import br.com.unicarioca.projetointegrador.timec.model.BeneficiarioModel;
import br.com.unicarioca.projetointegrador.timec.model.ExameModel;
import br.com.unicarioca.projetointegrador.timec.model.ProcedimentoModel;
import br.com.unicarioca.projetointegrador.timec.model.ReembolsoModel;
import br.com.unicarioca.projetointegrador.timec.vo.AnaliseReembolsoVo;
import br.com.unicarioca.projetointegrador.timec.vo.ReembolsoVo;


/*Enginner: jeison muniz
 * backlog: GERENCIAR SERVICO DE SOLICITACAI DE REEMBOLSO
 * Background:  
 *cenario: beneficiário da empresa 
 *desejo receber um reembolso do exame para 
 *receber meu dinheiro de volta. 
 
*/



@Stateless
public class ReembolsoBusiness {

	
   @EJB
   ReembolsoDao reembolsoDao;
   @EJB
   ProcedimentoBusiness procedimentoBusiness;
   @EJB
   ExameBusiness exameBusiness ;
   @EJB
   BeneficiarioBusiness beneficiarioBusiness;
   
   
   
   
/*Enginner: jeison muniz
 * backlog: GERENCIAR SERVICO DE SOLICITACAO DE REEMBOLSO
 * Background:  
 *cenario: Pedido de reembolso aceito
*  Dado que o funcionário peça o reembolso
   Quando o pedido for avaliado e aceito
    Então o funcionário deve receber o reembolso 
*/


/*Enginner: jeison muniz
 * backlog: GERENCIAR SERVICO DE SOLICITACAO DE REEMBOLSO
 * Background:  
 * cenario: Pedido de reembolso negado 
*  Dado que o funcionário peça o reembolso  
*   Quando o pedido for avaliado e negado   
*   Então o funcionário deve ter o reembolso negado 
*/
	/*String nome = request.getParameter("nome").toString();
		String email = request.getParameter("email").toString();
		String telefone = request.getParameter("telefone").toString();
		String endereco = request.getParameter("endereco").toString();
		String motivo = request.getParameter("motivo").toString();
		String matricula = request.getParameter("matricula").toString();*/
   
	public ReembolsoModel criarReembolso (ReembolsoModel r) {
		return reembolsoDao.criarSolicitacaoReembolso(r);
	}
	
	public String solicitacaoDeReembolso (String email,String telefone,String endereco,String motivo , String matricula) {
		Date dataAtutal = new Date();
		ReembolsoModel reembolsoModel = new ReembolsoModel();
		reembolsoModel.setNomeBeneficiario(Integer.valueOf(matricula));
		 reembolsoModel.setLogradouro(endereco); reembolsoModel.setEmail(email);
		 reembolsoModel.setMotivo(motivo); reembolsoModel.setTelefone(telefone);
		 reembolsoModel.setDataReembolso(dataAtutal);
		 reembolsoModel.setStatus("Em Andamento");
		 ReembolsoModel c = reembolsoDao.criarSolicitacaoReembolso(reembolsoModel);
		 return c.toString();
	}
	
	
	//validar lista de exames
			//registrar a solicitacao
			//retornar andamento da solicitacao
	public ReembolsoVo solicitaReembolsoPorExame (Integer idExame ) {
		
		ReembolsoVo solicitacaoReembolsoVo = new ReembolsoVo();
		Date dataAtutal = new Date();
		ReembolsoModel reembolsoModel = new ReembolsoModel();
		ExameModel exameModel = exameBusiness.localizarExamesPorId(idExame);
	
		try {
			
			
			BeneficiarioModel beneficiario = beneficiarioBusiness.bucarBeneficiariosPorMatricula(exameModel.getIdBeneficiario());
			ProcedimentoModel procedimento = procedimentoBusiness.buscarExamePorProcedimentosPagos(idExame);
			
			
				reembolsoModel.setNomeBeneficiario(beneficiario.getNumeroMatricula());
				 reembolsoModel.setLogradouro(beneficiario.getDescricaoEndereco());
				 reembolsoModel.setEmail(beneficiario.getEmail());
				 
				 reembolsoModel.setMotivo("VALOR A SER REEMBOLSADO R$: "+procedimento.getValorProcedimento()); 
				 reembolsoModel.setTelefone(beneficiario.getNumetoTelefone());
				 reembolsoModel.setDataReembolso(dataAtutal);
				 reembolsoModel.setStatus("OK");
				 ReembolsoModel c = reembolsoDao.criarSolicitacaoReembolso(reembolsoModel);
				
				 solicitacaoReembolsoVo.setBeneficiarioModel(beneficiario);
				 solicitacaoReembolsoVo.setReembolsoModel(c);
				 solicitacaoReembolsoVo.setProcedimentos(procedimento);
				 solicitacaoReembolsoVo.setExame(exameModel);
				 
				 return solicitacaoReembolsoVo;
		}catch(Exception e){
			
		}
			
		
		
		 return null ;
	}
	
	
	public String  buscarSolicitacaoDeReembolso(int id) {
		ReembolsoModel c = reembolsoDao.find(id);
		return c.toString();
	}
	
	
	
	/*Enginner: jeison muniz
	 * backlog: GERENCIAR SERVICO DE SOLICITACAO DE REEMBOLSO
	 * Background:  Reembolso
	 * cenario: Pedido de reembolso por id beneficiario 
	*  buscar todos exames do idBeneficiario
	*   verificar quais exames posssuem o procedimento pago
	*   retornar os exames com procedimentos pagos
	*/
	
	public AnaliseReembolsoVo analiseDeReembolso (Integer idBeneficiario ) {
	
		AnaliseReembolsoVo analiseReembolsoVo = new AnaliseReembolsoVo();
		BeneficiarioModel beneficiario = beneficiarioBusiness.bucarBeneficiariosPorMatricula(idBeneficiario);
		//buscar todos exames do idBeneficiario
		List<ExameModel> listaDeExames = exameBusiness.localizarExamesPorIdBenficiario(idBeneficiario);
		
		//verificar quais exames posssuem o procedimento pago
	
		List<ProcedimentoModel> lista = new ArrayList<>();
		for(ExameModel exame : listaDeExames){
			
			ProcedimentoModel procedimento = procedimentoBusiness.buscarExamePorProcedimentosPagos(exame.getExameId());
			lista.add(procedimento);
			
		}
		analiseReembolsoVo.setLista(listaDeExames);
		analiseReembolsoVo.setListaDeProcedimentos(lista);
		analiseReembolsoVo.setBeneficiarioModel(beneficiario);
	
	
		//retornar os exames com procedimentos pagos
		return analiseReembolsoVo;
	}

}
