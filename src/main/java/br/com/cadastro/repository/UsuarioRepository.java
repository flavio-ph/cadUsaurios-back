package br.com.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import br.com.cadastro.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	Object findByEmail(Object login);

}
