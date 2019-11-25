package br.com.unicarioca.projetointegrador.timec.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import br.com.unicarioca.projetointegrador.timec.model.BeneficiarioModel;
import br.com.unicarioca.projetointegrador.timec.model.ExameModel;
import br.com.unicarioca.projetointegrador.timec.model.ProcedimentoModel;
import br.com.unicarioca.projetointegrador.timec.model.ReembolsoModel;


@XmlRootElement
public class AnaliseReembolsoVo {

	private BeneficiarioModel beneficiarioModel ;
	private List<ExameModel> lista  ;
	private List<ProcedimentoModel> listaDeProcedimentos;

	public AnaliseReembolsoVo() {
		// TODO Auto-generated constructor stub
	}



	public List<ExameModel> getLista() {
		return lista;
	}

	public void setLista(List<ExameModel> lista) {
		this.lista = lista;
	}

	public List<ProcedimentoModel> getListaDeProcedimentos() {
		return listaDeProcedimentos;
	}

	public void setListaDeProcedimentos(List<ProcedimentoModel> listaDeProcedimentos) {
		this.listaDeProcedimentos = listaDeProcedimentos;
	}



	public BeneficiarioModel getBeneficiarioModel() {
		return beneficiarioModel;
	}



	public void setBeneficiarioModel(BeneficiarioModel beneficiarioModel) {
		this.beneficiarioModel = beneficiarioModel;
	}



	@Override
	public String toString() {
		return "AnaliseReembolsoVo [beneficiarioModel=" + beneficiarioModel + ", lista=" + lista
				+ ", listaDeProcedimentos=" + listaDeProcedimentos + ", getLista()=" + getLista()
				+ ", getListaDeProcedimentos()=" + getListaDeProcedimentos() + ", getBeneficiarioModel()="
				+ getBeneficiarioModel() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	
	





	
	
}
