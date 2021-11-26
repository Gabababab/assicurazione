package it.prova.assicurazione.service;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Service;

import it.prova.assicurazione.xml.Assicurati;

@Service
public class MarshallingServiceImpl implements MarshallingService {

	@Override
	public Unmarshaller unmarshall(File file) {

		JAXBContext jaxbContext = null;
		Unmarshaller jaxbUnmarshaller = null;
		try {

			jaxbContext = JAXBContext.newInstance(Assicurati.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			jaxbUnmarshaller.unmarshal(file);

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jaxbUnmarshaller;

	}

}
