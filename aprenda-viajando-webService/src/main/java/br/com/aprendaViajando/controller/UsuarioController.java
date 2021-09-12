/**
 * 
 */
package br.com.aprendaViajando.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.aprendaViajando.domain.model.usuario.Usuario;
import br.com.aprendaViajando.service.UsuarioService;

/**
 * @author MARCIO-PC
 *		
 */
@RestController /* Arquitetura REST */
@RequestMapping(value = "/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Usuario> init(@PathVariable (value = "id") Long id) {
		
		Usuario usuario = usuarioService.findById(id);
		
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
//	@GetMapping(value = "/", produces = "application/json")
//	public ResponseEntity<List<Usuario>> usuario (){
//		
//		List<Usuario> list = (List<Usuario>) usuarioService.findAll();
//		
//		return new ResponseEntity<List<Usuario>>(list, HttpStatus.OK);
//	}

}
