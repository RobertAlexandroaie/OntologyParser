package Model.util;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Model.ClassOWL;
import Model.IndividualOWL;
import Model.Parser;
import Model.Subtype;
import Model.Type;

public class XMLReader {

    private static void setClasses(Element rootElement, Parser parser) {
	NodeList classNodeList = rootElement.getElementsByTagName("Class");
	if (classNodeList != null && classNodeList.getLength() > 0) {
	    for (int j = 0; j < classNodeList.getLength(); j++) {
		Node classNode = classNodeList.item(j);
		if (classNode.getNodeType() == Node.ELEMENT_NODE) {
		    Element classElement = (Element) classNode;
		    ClassOWL newClass = new ClassOWL(classElement.getAttribute("IRI"));
		    parser.getClasses().add(newClass);
		}
	    }
	}
    }

    private static void setIndividuals(Element rootElement, Parser parser) {
	NodeList relativeNodeList = rootElement.getElementsByTagName("NamedIndividual");

	if (relativeNodeList != null && relativeNodeList.getLength() > 0) {
	    for (int j = 0; j < relativeNodeList.getLength(); j++) {
		Node relativeNode = relativeNodeList.item(j);
		if (relativeNode.getNodeType() == Node.ELEMENT_NODE) {
		    Element relativeElement = (Element) relativeNode;
		    IndividualOWL newIndividual = new IndividualOWL(relativeElement.getAttribute("IRI"));
		    parser.getIndividuals().add(newIndividual);
		}
	    }
	}

    }

    private static void setAssertions(Document document, Parser parser) {
	NodeList classAssertionNodeList = document.getElementsByTagName("ClassAssertion");
	if (classAssertionNodeList != null && classAssertionNodeList.getLength() > 0) {
	    for (int j = 0; j < classAssertionNodeList.getLength(); j++) {
		Node classAssertNode = classAssertionNodeList.item(j);
		if (classAssertNode.getNodeType() == Node.ELEMENT_NODE) {
		    Element classAssertElement = (Element) classAssertNode;

		    ClassOWL classOWL = null;
		    IndividualOWL individualOWL = null;

		    NodeList classNodeList = classAssertElement.getElementsByTagName("Class");
		    if (classNodeList != null && classNodeList.getLength() > 0) {
			for (int k = 0; k < classNodeList.getLength(); k++) {
			    Node classNode = classNodeList.item(k);
			    if (classNode.getNodeType() == Node.ELEMENT_NODE) {
				Element classElement = (Element) classNode;
				classOWL = parser.getClassByName(classElement.getAttribute("IRI"));
				break;
			    }
			}
		    }

		    NodeList relativeNodeList = classAssertElement.getElementsByTagName("NamedIndividual");
		    if (relativeNodeList != null && relativeNodeList.getLength() > 0) {
			for (int k = 0; k < relativeNodeList.getLength(); k++) {
			    Node relativeNode = relativeNodeList.item(k);
			    if (relativeNode.getNodeType() == Node.ELEMENT_NODE) {
				Element relativeElement = (Element) relativeNode;
				individualOWL = parser.getIndividualByName(relativeElement.getAttribute("IRI"));
				break;
			    }
			}
		    }
		    if (classOWL != null && individualOWL != null) {
			classOWL.addIndividual(individualOWL);
			individualOWL.setClassOWL(classOWL);
		    }
		}
	    }
	}
    }

    private static void setConnection(IndividualOWL individual1, IndividualOWL individual2, Parser parser) {
	if (individual1 != null && individual2 != null) {
	    Type type = new Type(individual2.getName());
	    Subtype subtype = new Subtype(individual1.getName());
	    subtype.setType(type);
	    type.addSubtype(subtype);
	    parser.addType(type);
	    parser.addSubtype(subtype);
	}
    }

    private static void setConnections(Document document, Parser parser) {
	NodeList objProprAssertNodeList = document.getElementsByTagName("ObjectPropertyAssertion");
	if (objProprAssertNodeList != null && objProprAssertNodeList.getLength() > 0) {
	    for (int j = 0; j < objProprAssertNodeList.getLength(); j++) {
		Node objProprAssertNode = objProprAssertNodeList.item(j);
		if (objProprAssertNode.getNodeType() == Node.ELEMENT_NODE) {
		    Element objProprAssertElement = (Element) objProprAssertNode;

		    IndividualOWL individual1 = null;
		    IndividualOWL individual2 = null;

		    int k = 0;
		    NodeList relativeNodeList = objProprAssertElement.getElementsByTagName("NamedIndividual");
		    if (relativeNodeList != null && relativeNodeList.getLength() > 0) {
			for (; k < relativeNodeList.getLength(); k++) {
			    Node relativeNode = relativeNodeList.item(k);
			    if (relativeNode.getNodeType() == Node.ELEMENT_NODE) {
				Element relativeElement = (Element) relativeNode;
				individual1 = parser.getIndividualByName(relativeElement.getAttribute("IRI"));
				break;
			    }
			}

			for (k++; k < relativeNodeList.getLength(); k++) {
			    Node relativeNode = relativeNodeList.item(k);
			    if (relativeNode.getNodeType() == Node.ELEMENT_NODE) {
				Element relativeElement = (Element) relativeNode;
				individual2 = parser.getIndividualByName(relativeElement.getAttribute("IRI"));
				break;
			    }
			}

			setConnection(individual1, individual2, parser);
		    }

		}
	    }
	}
    }

    public static void loadXML(Parser parser) {
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	factory.setIgnoringElementContentWhitespace(true);
	try {
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    File file = new File("NEROntolog.owl");
	    Document doc = builder.parse(file);
	    doc.getDocumentElement().normalize();

	    NodeList nList = doc.getElementsByTagName("Declaration");

	    for (int i = 0; i < nList.getLength(); i++) {
		Node node = nList.item(i);
		if (node.getNodeType() == Node.ELEMENT_NODE) {
		    Element element = (Element) node;

		    setClasses(element, parser);
		    setIndividuals(element, parser);
		}
	    }
	    setAssertions(doc, parser);
	    setConnections(doc, parser);

	} catch (ParserConfigurationException e) {
	} catch (SAXException e) {
	} catch (IOException e) {
	}
    }

}
