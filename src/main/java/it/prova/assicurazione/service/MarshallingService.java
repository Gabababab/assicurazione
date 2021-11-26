package it.prova.assicurazione.service;

import java.io.File;

import javax.xml.bind.Unmarshaller;

public interface MarshallingService {

	Unmarshaller unmarshall(File file);
}
