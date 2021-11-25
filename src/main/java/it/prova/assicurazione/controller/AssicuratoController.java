package it.prova.assicurazione.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.prova.assicurazione.model.Assicurato;
import it.prova.assicurazione.service.AssicuratoService;
import it.prova.assicurazione.xml.Assicurati;

@RestController
@RequestMapping(value = "/api/assicurato", produces = { MediaType.APPLICATION_JSON_VALUE })
public class AssicuratoController {

	@Autowired
	AssicuratoService assicuratoService;

	@GetMapping
	public void trigger() {

		try {

			File file = new File("xml/assicurati.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Assicurati.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Assicurati assicurati = (Assicurati) jaxbUnmarshaller.unmarshal(file);

			List<Assicurati.Assicurato> listaIterazione = assicurati.getAssicurato();

			List<Assicurato> listaAssicurati = new ArrayList<Assicurato>();
			for (Assicurati.Assicurato item : listaIterazione) {
				Assicurato tmp = new Assicurato(item.getNome(), item.getCognome(), item.getDataNascita(),
						item.getCodiceFiscale(), item.getNumeroSinistri());

				listaAssicurati.add(tmp);
			}

			for (Assicurato item : listaAssicurati) {
				if (item.getNumeroSinistri() < 0) {
					file.renameTo(new File("xml/scarti/assicurati.xml"));
				}
			}

			for (Assicurato item : listaAssicurati) {

				assicuratoService.aggiungiAlDb(item);
			}

			file.renameTo(new File("xml/processed/assicurati.xml"));

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}
}
