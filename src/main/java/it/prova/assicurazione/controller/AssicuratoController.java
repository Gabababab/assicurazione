package it.prova.assicurazione.controller;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;

import it.prova.assicurazione.model.Assicurato;
import it.prova.assicurazione.service.AssicuratoService;

public class AssicuratoController {
	
	@Autowired
	AssicuratoService assicuratoService;

	public void trigger() {
		
		  try {  
			   
		        File file = new File("assicurati.xml");  
		        JAXBContext jaxbContext = JAXBContext.newInstance(Assicurato.class);  
		   
		        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
		        Assicurato assic= (Assicurato) jaxbUnmarshaller.unmarshal(file);  
		          
		        System.out.println(assic.getNome()+" "+assic.getCognome()+ " "+ assic.getCodiceFiscale());  
		        Assicurato assicurato=assicuratoService.findByCodiceFiscale(assic.getCodiceFiscale());
		        
		        if(assicurato!=null) {
		        	int x=assicurato.getNumeroSinistri();
		        	x++;
		        	assicurato.setNumeroSinistri(x++);
		        }
		        else {
		        	assicuratoService.save(assicurato);
		        }
		   
		      } catch (JAXBException e) {  
		        e.printStackTrace();  
		      }  
		   
		    }
}
