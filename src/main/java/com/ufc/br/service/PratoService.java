package com.ufc.br.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ufc.br.model.Prato;
import com.ufc.br.repository.PratoRepository;

@Service
public class PratoService {
	
	@Autowired
	private PratoRepository pratoRepo;
	
	public void salvar(Prato prato) {
		pratoRepo.save(prato);
	}
	
	public Prato buscarPorId(Long id) {
		return pratoRepo.getOne(id);
	}
	
	public List<Prato> listarTodos(){
		return pratoRepo.findAll();
	}

	public static String getImageExtension(String imageName) {
		return imageName.substring(imageName.lastIndexOf("."));
	}
	
	public String salvarImagem(MultipartFile imageFile) throws IOException {
		String localFolder = Paths.get(".").toAbsolutePath().normalize().toString();
		String imageDirectory = localFolder + "/src/main/resources/pratos_images/";
		
		String imageExtension = getImageExtension(imageFile.getOriginalFilename());
		String currentTime = LocalDateTime.now().toString();
		String imageName = currentTime + imageExtension;
		
		String fullPath = imageDirectory + imageName;
		Path path = Paths.get(fullPath);
		
		byte[] bytes = imageFile.getBytes();
		Files.write(path, bytes);
		return fullPath.substring(fullPath.lastIndexOf("/pratos_images"));
	}

	public void excuirPrato(Long id) {
		pratoRepo.deleteById(id);
	}
}
