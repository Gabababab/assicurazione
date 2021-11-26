package it.prova.assicurazione.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessServiceImpl implements ProcessService {

	@Autowired
	FileProcessingService fileService;

	@Override
	public void processa(File file) {

		fileService.processaFile(file);

	}

}
