package br.com.ebt.figh.web.control;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import br.com.ebt.figh.web.util.FacesUtil;

@ManagedBean(name = "menuMB")
public class MenuMB {

	private static final String cadFornecedorURL   = "/paginas/admin/fornecedor/fornecedor.jsf";
	private static final String crudUsuarioURL 	   = "/paginas/admin/usuario/usuarios.jsf";
	private static final String crudProjetosURL    = "/paginas/admin/cadProjeto/crudProjeto.jsf";
	private static final String crudCenariosURL    = "/paginas/admin/cenario/lista.jsf";
	private static final String crudIncidentesURL  = "";
	private static final String testeURL		   = "/paginas/admin/teste.jsf";
	
	public void crudFornecedorListener(ActionEvent actionEvent) {
		FacesUtil.redirect(cadFornecedorURL);
	}

	public void crudUsuarioListener(ActionEvent actionEvent){
		FacesUtil.redirect(crudUsuarioURL);
	}

	public void crudProjetosListener(ActionEvent actionEvent){
		FacesUtil.redirect(crudProjetosURL);
	}
	
	public void crudCenariosListener(ActionEvent actionEvent){
		FacesUtil.redirect(crudCenariosURL);
	}
	
	public void crudIncidentesListener(ActionEvent actionEvent){
		FacesUtil.redirect(crudIncidentesURL);
	}
	
	public void cenarioListaListener(ActionEvent event) {
		FacesUtil.redirect(crudCenariosURL);
	}
	
	public void testeListener(ActionEvent actionEvent) {
		FacesUtil.redirect(testeURL);
	}
	
	
}
