package it.prova.assicurazione.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import it.prova.assicurazione.exceptions.AssicuratoNotFoundException;
import it.prova.assicurazione.model.Assicurato;
import it.prova.assicurazione.repository.AssicuratoRepository;


public class AssicuratoServiceImpl implements AssicuratoService {

	@Autowired
	private AssicuratoRepository assicuratoRepository;

	@Override
	public List<Assicurato> listAll() {
		return (List<Assicurato>) assicuratoRepository.findAll();
	}

	@Override
	public Page<Assicurato> searchAndPaginate(Assicurato assicuratoExample, Integer pageNo, Integer pageSize, String sortBy) {

		Specification<Assicurato> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();

			if (!StringUtils.isEmpty(assicuratoExample.getNome()))
				predicates.add(cb.like(cb.upper(root.get("nome")), "%" + assicuratoExample.getNome().toUpperCase() + "%"));

			if (!StringUtils.isEmpty(assicuratoExample.getCognome()))
				predicates.add(
						cb.like(cb.upper(root.get("cognome")), "%" + assicuratoExample.getCognome().toUpperCase() + "%"));

			if (!StringUtils.isEmpty(assicuratoExample.getCodiceFiscale()))
				predicates.add(cb.like(cb.upper(root.get("codiceFiscale")),
						"%" + assicuratoExample.getCodiceFiscale().toUpperCase() + "%"));

			

			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};

		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		return assicuratoRepository.findAll(specificationCriteria, paging);
	}

	@Override
	public Assicurato get(Long idInput) {
		return assicuratoRepository.findById(idInput)
				.orElseThrow(() -> new AssicuratoNotFoundException("Element with id " + idInput + " not found."));
	}

	@Override
	public Assicurato save(Assicurato input) {
		return assicuratoRepository.save(input);
	}

	@Override
	public void delete(Assicurato input) {
		assicuratoRepository.delete(input);
	}

	@Override
	public Assicurato findByCodiceFiscale(String cf) {
		return assicuratoRepository.findByCodiceFiscale(cf);
	}
}
