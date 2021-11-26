package it.prova.assicurazione.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.prova.assicurazione.model.Assicurato;
import it.prova.assicurazione.xml.Assicurati;

public class FileProcessingServiceImpl implements FileProcessingService {

	@Autowired
	MarshallingService marshallingService;
	
	@Autowired
	AssicuratoService assicuratoService;

	@Override
	public void processaFile() {

		File file = new File("Corso/xml/assicurati.xml");
		
		Assicurati assicurati = (Assicurati) marshallingService.unmarshall(file);
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
		
		
	}

}
