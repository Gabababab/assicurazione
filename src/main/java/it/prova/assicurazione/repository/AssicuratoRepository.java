package it.prova.assicurazione.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.assicurazione.model.Assicurato;


public interface AssicuratoRepository extends PagingAndSortingRepository<Assicurato, Long>, JpaSpecificationExecutor<Assicurato>{

	Assicurato findByCodiceFiscale(String cf);

	Assicurato findByNomeAndCognomeAndCodiceFiscaleAndDataNascita(String nome, String cognome, String cf, Date date);
}
