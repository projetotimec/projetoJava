package br.com.unicarioca.projetointegrador.timec.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "procedimento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcedimentoModel.findAll", query = "SELECT e FROM ProcedimentoModel e"),
@NamedQuery(name = "ProcedimentoModel.pagos", query = "SELECT e FROM ProcedimentoModel e")})

public class ProcedimentoModel {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cd_procedimento")
	private Integer codigoProcedimento ;
	@Size(max = 45)
	@Column(name = "ds_procedimento")
	private String descricaoProcedimento ;
	@Column(name = "vl_procedimento")
	private Float valorProcedimento;
	@Column(name = "proc_pago")
    private Integer procPagamento ;
	@Column(name = "id_exame")
	private Integer codigoExame;
	
	
	public ProcedimentoModel() {
		
	}
	
	public Integer getCodigoProcedimento() {
		return codigoProcedimento;
	}
	public void setCodigoProcedimento(Integer codigoProcedimento) {
		this.codigoProcedimento = codigoProcedimento;
	}
	public String getDescricaoProcedimento() {
		return descricaoProcedimento;
	}
	public void setDescricaoProcedimento(String descricaoProcedimento) {
		this.descricaoProcedimento = descricaoProcedimento;
	}
	public Float getValorProcedimento() {
		return valorProcedimento;
	}
	public void setValorProcedimento(Float valorProcedimento) {
		this.valorProcedimento = valorProcedimento;
	}
	public Integer getProcPagamento() {
		return procPagamento;
	}
	public void setProcPagamento(Integer procPagamento) {
		this.procPagamento = procPagamento;
	}
	public Integer getCodigoExame() {
		return codigoExame;
	}
	public void setCodigoExame(Integer codigoExame) {
		this.codigoExame = codigoExame;
	}
	@Override
	public String toString() {
		return "ProcedimentoModel [codigoProcedimento=" + codigoProcedimento + ", descricaoProcedimento="
				+ descricaoProcedimento + ", valorProcedimento=" + valorProcedimento + ", procPagamento="
				+ procPagamento + ", codigoExame=" + codigoExame + ", getCodigoProcedimento()="
				+ getCodigoProcedimento() + ", getDescricaoProcedimento()=" + getDescricaoProcedimento()
				+ ", getValorProcedimento()=" + getValorProcedimento() + ", getProcPagamento()=" + getProcPagamento()
				+ ", getCodigoExame()=" + getCodigoExame() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
	
	

}
