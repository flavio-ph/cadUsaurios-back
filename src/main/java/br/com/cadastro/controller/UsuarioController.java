package br.com.cadastro.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadastro.entity.Usuario;
import br.com.cadastro.repository.UsuarioRepository;
import br.com.cadastro.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	

	private PasswordEncoder passwordEncoder;
	
	
	private UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

		@GetMapping
		public  ResponseEntity<List<Usuario>> listarUsuarios () {
			return ResponseEntity.status(200).body(usuarioService.listarUsuario());
		}
		
		@PostMapping
		public ResponseEntity <Usuario> criarUsuario (@Valid @RequestBody Usuario usuario) {
			return ResponseEntity.status(201).body(usuarioService.criarUsuario(usuario));
		}
		
		@PutMapping
		public ResponseEntity <Usuario> editarUsuario (@Valid @RequestBody Usuario usuario) {
			return ResponseEntity.status(200).body(usuarioService.criarUsuario(usuario));
		}
		
		@DeleteMapping("/{id}")
		public ResponseEntity<?> excluirUsuario (@PathVariable Integer id) {
			usuarioService.excluirUsuario(id);
			return ResponseEntity.status(204).build();
			
		}
		
		@PostMapping("/login")
		public ResponseEntity<Usuario> validarSenha(@Valid @RequestBody Usuario usuario) {
			Boolean valid = usuarioService.validarSenha(usuario);
			if (!valid) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			return ResponseEntity.status(204).build();
		}
		
		@ResponseStatus(HttpStatus.BAD_REQUEST)
		@ExceptionHandler(MethodArgumentNotValidException.class)
		public Map<String, String> handleValidationExceptio(MethodArgumentNotValidException ex) {
			Map<String, String> errors = new HashMap<>();
			
			ex.getBindingResult().getAllErrors().forEach((error) -> { 
				String fieldName = ((FieldError) error).getField();
				String errorMessage = error.getDefaultMessage();
				errors.put(fieldName, errorMessage);
			});
			
			return errors;
			
		}
		
		
}
