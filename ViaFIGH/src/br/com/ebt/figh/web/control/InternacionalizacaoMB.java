package br.com.ebt.figh.web.control;

import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "InternacionalizacaoMB")
@SessionScoped
public class InternacionalizacaoMB {

	private Locale currentLocale;
	
	public void englishLocale(){
		currentLocale = Locale.US;
		FacesContext.getCurrentInstance().getViewRoot().setLocale(currentLocale);
	}
	public void portugueseLocale(){
		currentLocale = new Locale("pt","BR");
		FacesContext.getCurrentInstance().getViewRoot().setLocale(currentLocale);
	}
	
	public Locale getCurrentLocale() {
		if(currentLocale == null){
			currentLocale = new Locale("pt","BR");
		}
		return currentLocale;
	}
	
}
