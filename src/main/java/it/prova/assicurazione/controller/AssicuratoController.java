package it.prova.assicurazione.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.prova.assicurazione.service.ProcessService;

@RestController
@RequestMapping(value = "/api/assicurato", produces = { MediaType.APPLICATION_JSON_VALUE })
public class AssicuratoController {
	
	@Autowired
	ProcessService processService;

	@GetMapping
	public void trigger() {
		
			File file = new File("Corso/xml/assicurati.xml");
			processService.processa(file);
			
	}
}
