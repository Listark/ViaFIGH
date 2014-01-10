package br.com.ebt.figh.action.listener;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import br.com.ebt.figh.web.control.FornecedorController_old;

public class FornecedorActionListener implements ActionListener {

	@Override
	public void processAction(ActionEvent event) throws AbortProcessingException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FornecedorController_old controller = (FornecedorController_old) facesContext.getELContext().getELResolver().getValue(
				facesContext.getELContext(), null, "fornecedorController");
		
		controller.setarCidadeEdicao();
	}
	
}
