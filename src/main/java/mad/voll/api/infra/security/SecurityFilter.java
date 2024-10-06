package mad.voll.api.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mad.voll.api.modules.basicRegister.usuario.UsuarioRepository;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String tokenJWT = this.verificarToken(request);
		if(tokenJWT != null) {
			String username = tokenService.getTokenSubject(tokenJWT);
			UserDetails usuario = usuarioRepository.findByLogin(username);
			
			SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities()));
		}

		filterChain.doFilter(request, response);
	}
	
	private String verificarToken(HttpServletRequest request) {
		String requestToken = request.getHeader("Authorization");
		
		if(requestToken != null) {
			return requestToken.replace("Bearer ", "");
		}
		
		return null;
	}

}
