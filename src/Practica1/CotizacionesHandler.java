package Practica1;

import java.util.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class CotizacionesHandler extends DefaultHandler {

	final int NADA = 0;
	final int NOMBRE = 1;
	final int COTIZACION = 2;

	private Hashtable<String, Double> tabla = new Hashtable<String, Double>();

	String empresa = "";
	double cotizacion = 0.0;
	int situacion = NADA;

	@Override
	public void startDocument() throws SAXException {
		//System.out.println("Empieza el documento");
		tabla.clear();
	}

	@Override
	public void endDocument() throws SAXException {
		//System.out.println("Termina el documento");
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName == "nombre"){
			situacion = NOMBRE;
		}else if (qName == "cotizacion"){
			situacion = COTIZACION;
		}
		//System.out.println("Leo una etiqueta " + qName);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		//System.out.println("Cierro una etiqueta");
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		//System.out.println("Leo caracteres: " + new String(ch, start, length));
		if(situacion == NOMBRE){
			empresa = new String(ch, start, length);
		}else if (situacion == COTIZACION){
			cotizacion = Double.parseDouble(new String(ch, start, length));
			tabla.put(empresa,cotizacion);
			empresa = "";
			cotizacion = 0.0;
		}
		situacion = NADA;
	}

	public Hashtable<String, Double> getTabla() {
		return tabla;
	}
}