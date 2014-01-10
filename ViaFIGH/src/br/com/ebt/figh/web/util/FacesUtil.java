package br.com.ebt.figh.web.util;

import java.io.IOException;
import java.util.Map;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.context.RequestContext;


/**
 * Classe utilitária para desenvolvimento JSF
 */
public class FacesUtil {
	
	public static final String app_path = FacesUtil.getApplicationtPath();
	
	public static String getRequestParameter(String name) {
		return (String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
	}

	public static void exibirMensagemSucesso(String mensagem) {
		exibirMensagem(FacesMessage.SEVERITY_INFO, mensagem);
	}

	public static void exibirMensagemAlerta(String mensagem) {
		exibirMensagem(FacesMessage.SEVERITY_WARN, mensagem);
	}
	
	public static void exibirMensagemErro(String mensagem) {
		exibirMensagem(FacesMessage.SEVERITY_ERROR, mensagem);
	}
	
	private static void exibirMensagem(FacesMessage.Severity severity, String mensagem) {
		FacesMessage facesMessage = new FacesMessage(severity, "", mensagem);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

	public static ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}
	
	@SuppressWarnings("rawtypes")
	public static Map getSessionMap() {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	}
	
	public static ServletContext getServletContext() {
		return (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
	}
	
	public static HttpServletRequest getServletRequest() {
		return (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
	
	public static HttpServletResponse getServletResponse() {
		return (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
	}
		
	public static String getApplicationtPath(){
		return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
	}
	
	public static void redirect(String dir){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(app_path + dir);
		} catch (IOException e) {
			exibirMensagemErro("Erro ao redirecionar a página. Código:" + e.toString());
			
		}
	}
	
	public static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	
	public static Application getApplication() {
		return FacesContext.getCurrentInstance().getApplication();
	}
	
	public static NavigationHandler getNavigationHandler() {
		return FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
	}
	
	public static void executarScript(String script) {
		RequestContext rc = RequestContext.getCurrentInstance();
		rc.execute(script);
	}
	
	public static RequestContext getRequestContext() {
		return RequestContext.getCurrentInstance();
	}
	
}

