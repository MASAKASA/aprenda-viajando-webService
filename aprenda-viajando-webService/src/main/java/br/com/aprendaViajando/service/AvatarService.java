/**
 * 
 */
package br.com.aprendaViajando.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.com.aprendaViajando.domain.model.util.Avatar;
import br.com.aprendaViajando.domain.repository.util.AvatarRepository;

/**
 * @author MARCIO
 *
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class AvatarService {

	@Autowired
	private AvatarRepository retositoy;
	
	@Transactional(readOnly = false)
	public void saveOrUpdate(Avatar avatar) {
		retositoy.save(avatar);
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		Optional<Avatar> optional = retositoy.findById(id);
		
		Avatar aPersistente = optional.get();
		
		retositoy.delete(aPersistente);
	}
	
	public Avatar findById(Long id) {
		return retositoy.getOne(id);
	}
	
	public Avatar getAvatarByUpload(MultipartFile file) {
		Avatar avatar = new Avatar();
		
		if (file != null && file.getSize() > 0) {
			try {
				avatar.setTitulo(file.getOriginalFilename());
				avatar.setTipo(file.getContentType());
				avatar.setAvatar(file.getBytes());
			} catch (IOException e) {
				System.out.println("Ocorreu um erro em AvatarService: " + e.getMessage());	
			}
		}
		return avatar;
	}
}
