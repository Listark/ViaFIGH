package br.com.ebt.figh.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import br.com.ebt.figh.model.Usuario;

@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	private static final String URL_REDIRECT_CHANGE_PASSWORD = "/paginas/admin/teste.jsf";
	private static final String URL_REDIRECT_INICIO = "/paginas/inicio.jsf";
	private RedirectStrategy redirect = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		handle(request, response, authentication);
		clearAuthenticationAttributes(request);
	}
	
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		String urlTarget = determineTargetUrl(authentication);
		redirect.sendRedirect(request, response, urlTarget);
		
	}

	protected String determineTargetUrl(Authentication authentication) throws IOException {
		Usuario user = (Usuario) authentication.getPrincipal();
		
		if(user != null)  
			if(user.getSenha().equals("123456"))
				return URL_REDIRECT_CHANGE_PASSWORD;
			else
				return URL_REDIRECT_INICIO;
		else 
			throw new IOException("Nenhum usuário para autenticar");
	
		
	}
	
	public RedirectStrategy getRedirect() {
		return redirect;
	}

	public void setRedirect(RedirectStrategy redirect) {
		this.redirect = redirect;
	}
	
	
	
}
