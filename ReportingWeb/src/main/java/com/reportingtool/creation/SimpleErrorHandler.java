package com.reportingtool.creation;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Class from http://www.edankert.com/validate.html Used in
 * GeneratorXML.validateXMLWellFormed()
 * 
 * @author alberto.olivan
 *
 */
public class SimpleErrorHandler implements ErrorHandler {

	public void warning(SAXParseException e) throws SAXException {
		System.out.println(e.getMessage());
	}

	public void error(SAXParseException e) throws SAXException {
		System.out.println(e.getMessage());
	}

	public void fatalError(SAXParseException e) throws SAXException {
		System.out.println(e.getMessage());
	}

}
