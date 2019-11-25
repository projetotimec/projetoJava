package br.com.unicarioca.projetointegrador.timec.business;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.ejb.AccessTimeout;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.ScheduleExpression;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import br.com.unicarioca.projetointegrador.timec.model.ExameModel;

/**
 *
* @author Willy 1.0
 * @author jeison  1.1
 *
 * backlog: Push de Notifica��o por email de Agendamento de Exame 
 * Background:  
 * Scenario: Eu como benefici�rio da empresa 
 * desejo receber uma notifica��o do agendamento 
 * do exame para saber que o exame foi confirmado
*/

@Stateless
public class AgendadorBean  {

	
	@EJB
	ExameBusiness exameBusiness;
	
	
	/**
	 *
	* @author Willy 1.0
	 * @author jeison  1.1
	 *
	 * backlog: Push de Notifica��o por email de Agendamento de Exame 
	 * Background:  
	 * Scenario: no PROCESSO 1 realizar  a chamada do m�doto notificarExame() periodicamente
	 * dado : desejo receber uma notifica��o do agendamento 
	 * do exame para saber que o exame foi confirmado
	 * 
	 * e realiza o registro dos exames j� enviados  no PROCESSO 2 resgistrarProcesso()
	*/
	 
	
     @Timeout 
	 @AccessTimeout(value = 20, unit = TimeUnit.MINUTES) 
	 @Schedule(second = "*/1000", hour = "*", minute = "*")
     public void notificarExamesRealizados() {
    	 
         System.out.println("-----------");
	        System.out.println("-----PROCESSO 1------");
	        System.out.println("-----------");
	        
		  System.out.println("----- INICIALIZAR VARREDURA DE NOTIFICA��ES DE BENEFICI�RIOS -----");
		  List<ExameModel> examesNotificados =  exameBusiness.noficicarExame();
	        System.out.println( "--------- N�MERO DE BENEFICI�RIOS NOTIFICADOS POR EMAIL : " + examesNotificados.size());
	        System.out.println("----- ENCERRAR VARREDURA DE NOTIFICA��ES -----");
	        
	        System.out.println("-----------");
	        System.out.println("-----PROCESSO 2------");
	        System.out.println("-----------");
	        
	        System.out.println("----- INICIALIZAR VARREDURA DE REGISTRO -----");
			  List<ExameModel> en =  exameBusiness.resgistrarProcesso();
		        System.out.println( "--------- N�MERO DE REGISTRO PROCESSADOS : " + en.size());
		        System.out.println("----- ENCERRAR VARREDURA DE PROCESSAMENTO DE NOTIFICA��ES -----");
				  
			  
     }
     
     
    
	 
	}


