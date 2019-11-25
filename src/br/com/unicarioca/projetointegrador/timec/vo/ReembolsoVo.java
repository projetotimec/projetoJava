package br.com.unicarioca.projetointegrador.timec.vo;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import br.com.unicarioca.projetointegrador.timec.model.BeneficiarioModel;
import br.com.unicarioca.projetointegrador.timec.model.ExameModel;
import br.com.unicarioca.projetointegrador.timec.model.ProcedimentoModel;
import br.com.unicarioca.projetointegrador.timec.model.ReembolsoModel;



@XmlRootElement
public class ReembolsoVo implements Serializable {

	private ReembolsoModel reembolsoModel ;
	private BeneficiarioModel beneficiarioModel ;
	private ExameModel exame  ;
	private ProcedimentoModel procedimentos;

	public ReembolsoVo() {
		// TODO Auto-generated constructor stub
	}

	public ReembolsoModel getReembolsoModel() {
		return reembolsoModel;
	}

	public void setReembolsoModel(ReembolsoModel reembolsoModel) {
		this.reembolsoModel = reembolsoModel;
	}

	public BeneficiarioModel getBeneficiarioModel() {
		return beneficiarioModel;
	}

	public void setBeneficiarioModel(BeneficiarioModel beneficiarioModel) {
		this.beneficiarioModel = beneficiarioModel;
	}

	public ExameModel getExame() {
		return exame;
	}

	public void setExame(ExameModel exame) {
		this.exame = exame;
	}

	public ProcedimentoModel getProcedimentos() {
		return procedimentos;
	}

	public void setProcedimentos(ProcedimentoModel procedimentos) {
		this.procedimentos = procedimentos;
	}

	@Override
	public String toString() {
		return "ReembolsoVo [reembolsoModel=" + reembolsoModel + ", beneficiarioModel=" + beneficiarioModel + ", exame="
				+ exame + ", procedimentos=" + procedimentos + ", getReembolsoModel()=" + getReembolsoModel()
				+ ", getBeneficiarioModel()=" + getBeneficiarioModel() + ", getExame()=" + getExame()
				+ ", getProcedimentos()=" + getProcedimentos() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}



	


	
	

}
