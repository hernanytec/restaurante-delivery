package com.ufc.br.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Prato;
import com.ufc.br.service.PratoService;

@Controller
@RequestMapping("/pratos")
public class PratosController {

	@Autowired
	PratoService pratoService;
	
	@RequestMapping("/listagem")
	public ModelAndView listarPratos() {
		ModelAndView mv = new ModelAndView("lista-de-pratos");
		List<Prato> pratos = pratoService.listarTodos();
		
		mv.addObject("listaPratos", pratos);
		return mv;
	}
	
	
	@RequestMapping("/cadastro")
	public ModelAndView caregarFormCadastro() {
		ModelAndView mv = new ModelAndView("cadastro-prato");
		mv.addObject(new Prato());
		return mv;
	}
	
	
	@PostMapping("/salvar")
	public ModelAndView salvarPrato(@RequestParam("imageFile") MultipartFile imageFile, Prato prato) throws IOException {
		String imagePath = pratoService.salvarImagem(imageFile);
		prato.setCaminhoImagem(imagePath);
		
		pratoService.salvar(prato);
		ModelAndView mv = new ModelAndView("redirect:/");
		return mv;
	}
	
	@RequestMapping("/pratos-cadastrados")
	public ModelAndView oratosCafastrados() throws IOException {
		ModelAndView mv = new ModelAndView("pratos-para-deletar");
		List<Prato> pratos = pratoService.listarTodos();
		
		mv.addObject("listaPratos", pratos);
		return mv;
	}
	
	
	@RequestMapping("/apagar/{id}")
	public ModelAndView apagarPrato(@PathVariable Long id) throws IOException {
		pratoService.excuirPrato(id);
		ModelAndView mv = new ModelAndView("redirect:/pratos/pratos-cadastrados");
		return mv;
	}
	
}
