package br.com.cadastro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.cadastro.entity.Usuario;
import br.com.cadastro.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UsuarioService {

	private UsuarioRepository repository;
	private PasswordEncoder passwordEncoder;
	
	
	public UsuarioService(UsuarioRepository repository) {
		this.setRepository(repository);
		this.passwordEncoder = new BCryptPasswordEncoder();
		
	}
	
	public List<Usuario> listarUsuario() {
		List<Usuario> lista = repository.findAll();
		return lista;
	}
	
	public Usuario criarUsuario(Usuario usuario) {
		String encoder = this.passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(encoder);
		Usuario usuarioNovo = repository.save(usuario);
		return usuarioNovo;
	}
	
	public Usuario editarUsuario(Usuario usuario) {
		String encoder = this.passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(encoder);
		Usuario usuarioNovo = repository.save(usuario);
		return usuarioNovo;
	}
	
	public Boolean excluirUsuario(Integer id) {
		repository.deleteById(id);
		return true;
	}
	
	public Boolean validarSenha(Usuario usuario) {
        String senha = repository.findById(usuario.getId())
                                 .map(Usuario::getSenha)
                                 .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        return passwordEncoder.matches(usuario.getSenha(), senha);
	}
	

	public UsuarioRepository getRepository() {
		return repository;
	}

	public void setRepository(UsuarioRepository repository) {
		this.repository = repository;
	}


}
