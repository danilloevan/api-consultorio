package mad.voll.api.domain.usuario;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService{
	
	final UsuarioRepository usuarioRepository;
	
	public AutenticacaoService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		
		
		return usuarioRepository.findByLogin(login);
	}

	
}