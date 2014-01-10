package br.com.ebt.figh.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ebt.figh.model.ModeloTesteDependente;

@FacesConverter(value="converterModeloDependente")
public class ConverterModeloDependente implements Converter {
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value != null && !value.equals("")) {
			String[] valores = value.split("-");
			
			int id = Integer.parseInt(valores[0]);
			String nome = valores[1];
			
			ModeloTesteDependente dependente = new ModeloTesteDependente();
			dependente.setId(id);
			dependente.setNome(nome);
			
			return dependente;
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value instanceof ModeloTesteDependente) {
			ModeloTesteDependente m = (ModeloTesteDependente) value;
			return m.toString();
		} else {
			return null;
		}
	}
	
}
