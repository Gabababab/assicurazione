package it.prova.assicurazione.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.assicurazione.model.Assicurato;


public interface AssicuratoService {

	Page<Assicurato> searchAndPaginate(Assicurato assicuratoExample, Integer pageNo, Integer pageSize, String sortBy);

	List<Assicurato> listAll();

	Assicurato get(Long idInput);
	
	Assicurato save(Assicurato input);
	
	void delete(Assicurato input);
	
	Assicurato findByCodiceFiscale(String cf);
	
	Assicurato findByNomeAndCognomeAndCodiceFiscaleAndDataNascita(String nome, String cognome, String cf, Date date);
	
	boolean aggiungiAlDb(Assicurato input);
}
