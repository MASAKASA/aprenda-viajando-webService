/**
 * 
 */
package br.com.aprendaViajando.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aprendaViajando.domain.model.usuario.Usuario;
import br.com.aprendaViajando.service.servicesInterfaces.usuario.UsuarioServiceInterface;

/**
 * @author MARCIO-PC
 *		
 */
@RestController /* Arquitetura REST */
@RequestMapping(value = "/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioServiceInterface usuarioService;
	
	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {
		
		usuario = (Usuario) usuarioService.saveOrUpdate(usuario);
		
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	@PutMapping(value = "/", produces = "application/json")
	public ResponseEntity<Usuario> Update(@RequestBody Usuario usuario) {
		
		usuario = (Usuario) usuarioService.saveOrUpdate(usuario);
		
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	@PutMapping(value = "/nomeemail/", produces = "application/json")
	public ResponseEntity<Usuario> UpdateNameEmail(@RequestBody Usuario usuario) {
		
		Usuario usuarioP = (Usuario) usuarioService.findById(usuario.getId());
		
		usuarioService.atualizarNome(usuarioP.getNome(), usuario.getNome());
		
		usuarioService.atualizarEmail(usuarioP.getEmail(), usuario.getEmail());
		
		usuarioP = (Usuario) usuarioService.findById(usuario.getId());
		
		return new ResponseEntity<Usuario>(usuarioP, HttpStatus.OK);
	}
	
	@DeleteMapping(value= "/{id}", produces = "application/text")
	public String delete(@PathVariable("id") Long id) {
		
		usuarioService.delete(id);
		
		return "ok";
	
	}
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Usuario> findById(@PathVariable (value = "id") Long id) {
		
		Usuario usuario = (Usuario) usuarioService.findById(id);
		
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	@GetMapping(value = "/nome/{nome}", produces = "application/json")
	public ResponseEntity<List<Usuario>> findByNome(@PathVariable (value = "nome") String nome) {
		
		List<Usuario> usuarios =  usuarioService.findByNome(nome);
		
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}
	
	@GetMapping(value = "/pagination", produces = "application/json")
	public ResponseEntity<?> findByPagination(Pageable pageable) {
		
		return new ResponseEntity<>(usuarioService.findByPagination(pageable), HttpStatus.OK);
	}

}
