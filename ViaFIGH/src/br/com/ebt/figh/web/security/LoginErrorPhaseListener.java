package br.com.ebt.figh.web.security;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.WebAttributes;

import br.com.ebt.figh.web.util.FacesUtil;

/**
 * PhaseListener utilizado para capturar excecoes de autenticacao
 *
 */
@SuppressWarnings({"serial"})
public class LoginErrorPhaseListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent arg0) {
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public void beforePhase(PhaseEvent arg0) {		
		
		Exception dadosIncorretosException = (Exception) FacesUtil.getSessionMap().get(WebAttributes.AUTHENTICATION_EXCEPTION);
		
		if(dadosIncorretosException instanceof BadCredentialsException) {
			FacesUtil.getSessionMap().put(WebAttributes.AUTHENTICATION_EXCEPTION, null);
			FacesUtil.exibirMensagemErro("Dados incorretos!");
			return;
		}
		
	
/*		SecurityContext context = SecurityContextHolder.getContext();
		if(context instanceof SecurityContext) {
			Authentication authentication = context.getAuthentication();
			
			if(!(authentication.getName().equals("anonymousUser"))) {
				String senha = ((Usuario) authentication.getPrincipal()).getPassword().toString();
				
				if(senha.equals("123456"))					
					FacesUtil.redirect("/paginas/admin/teste.jsf");
					
			}
		
		}*/		
	
	
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

}
