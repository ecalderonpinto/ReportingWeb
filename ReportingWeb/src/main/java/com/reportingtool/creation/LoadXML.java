package com.reportingtool.creation;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.hibernate.internal.jaxb.cfg.ObjectFactory;
import org.springframework.context.ApplicationContext;

import com.reportingtool.xml.AIFMReportingInfo;

public class LoadXML {

	private ApplicationContext applicationContext;

	public LoadXML(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	// http://java.dzone.com/articles/using-jaxb-generate-java
	
	public void loadXMLFile(File file) throws JAXBException {

		// 1. We need to create JAXContext instance
		JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);

		// 2. Use JAXBContext instance to create the Unmarshaller.
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		// 3. Use the Unmarshaller to unmarshal the XML document to get an
		// instance of JAXBElement.
		JAXBElement<AIFMReportingInfo> unmarshalledObject = (JAXBElement<AIFMReportingInfo>) unmarshaller
				.unmarshal(ClassLoader
						.getSystemResourceAsStream("problem/expense.xml"));

		// 4. Get the instance of the required JAXB Root Class from the
		// JAXBElement.
		// ExpenseT expenseObj = unmarshalledObject.getValue();
		// UserT user = expenseObj.getUser();
		// ItemListT items = expenseObj.getItems();
		//
		// //Obtaining all the required data from the JAXB Root class instance.
		// System.out.println("Printing the Expense for: "+user.getUserName());
		// for (ItemT item : items.getItem()){
		// System.out.println("Name: "+item.getItemName());
		// System.out.println("Value: "+item.getAmount());
		// System.out.println("Date of Purchase: "+item.getPurchasedOn());
		// }
	}
}
