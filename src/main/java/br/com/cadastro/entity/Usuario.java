package br.com.cadastro.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@NotBlank(message = "O nome é obrigatório!")
	@Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres!")
	@Column(name = "nome", length = 200, nullable = false)
	private String nome;
	
	@NotBlank(message = "O email é obrigatório!")
	@Column(name = "email", length = 200, nullable = false)
	private String email;
	
	@NotBlank(message = "A senha é obrigatório!")
	@Column(name = "senha", columnDefinition = "Text", nullable = false)
	private String senha;
	
	@NotBlank(message = "O telefone é obrigatório!")
	@NotBlank
	@Column(name = "tel", length = 15, nullable = false)
	private String tel;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	

}
