import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.stream.*;


public class CustomerXMLFile {
	ArrayList<Customer> customerList = null;
	String filename ="C:\\Users\\Lakshmipriya\\workspace\\CustomerXML\\src\\CustomerData.xml";
	
	public void listCustomers() {
		System.out.println("Customer Information:");
		System.out.println("*******************************************************");
		for (Customer c : readFromXMLFile()) {
			System.out.println(StringUtil.formatString(c.getFirstName()) + " " + StringUtil.formatString(c.getLastName()) + " "
					+ StringUtil.formatString(c.getEmail()));
		}
	}

	public Customer findCustomer(String email) {
		for (Customer c : readFromXMLFile()) {
			if (c.getEmail().equalsIgnoreCase(email))
				return c;
		}
		System.out.println("Cannot find customer");
		return null;
	}

	public void addCustomer(String email, String firstName, String lastName) {
		if (customerList == null)
			this.readFromXMLFile();
		customerList.add(new Customer(email, firstName, lastName));
		this.writeToXMLFile();
	}

	public void deleteCustomer(String email) {
		if (customerList == null)
			this.readFromXMLFile();
		customerList.remove(this.findCustomer(email));
		this.writeToXMLFile();
	}

	private ArrayList<Customer> readFromXMLFile() {
		if (customerList != null) {
			return customerList;
		}
		customerList = new ArrayList<Customer>();
		Customer c = null;

		XMLInputFactory inputFactory = XMLInputFactory.newFactory();
		try {
			FileReader fileReader = new FileReader(filename);
			XMLStreamReader reader = inputFactory
					.createXMLStreamReader(fileReader);

			while (reader.hasNext()) {
				int eventType = reader.getEventType();
				switch (eventType) {
				case XMLStreamConstants.START_ELEMENT:
					String elementName = reader.getLocalName();
					if (elementName.equalsIgnoreCase("Customer")) {
						c = new Customer();
						String email = reader.getAttributeValue(0);
						c.setEmail(email);
					}
					if (elementName.equalsIgnoreCase("FirstName")) {
						String firstName = reader.getElementText();
						c.setFirstName(firstName);
					}
					if (elementName.equalsIgnoreCase("LastName")) {
						String lastName = reader.getElementText();
						c.setLastName(lastName);
					}
					break;
				case XMLStreamConstants.END_ELEMENT:
					elementName = reader.getLocalName();
					if (elementName.equalsIgnoreCase("Customer")) {
						customerList.add(c);
					}
					break;
				default:
					break;
				}
				reader.next();
			}
		} catch (IOException | XMLStreamException e) {
			System.out.println(e);
			return null;
		}
		return customerList;

	}

	public void writeToXMLFile() {
		XMLOutputFactory outputFactory = XMLOutputFactory.newFactory();
		try {
			FileWriter fileWriter = new FileWriter(filename);
			XMLStreamWriter writer = outputFactory
					.createXMLStreamWriter(fileWriter);

			writer.writeStartElement("Customers");
			for (Customer c : customerList) {
				writer.writeStartElement("Customer");
				writer.writeAttribute("Email", c.getEmail());
				writer.writeStartElement("FirstName");
				writer.writeCharacters(c.getFirstName());
				writer.writeEndElement();
				writer.writeStartElement("LastName");
				writer.writeCharacters(c.getLastName());
				writer.writeEndElement();
				writer.writeEndElement();
			}
			writer.writeEndElement();
			writer.flush();
			writer.close();
		} catch (IOException | XMLStreamException e) {
			System.out.println(e);
		}
	}
}
