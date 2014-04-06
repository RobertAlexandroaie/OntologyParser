package Model;

import java.util.ArrayList;

public class Parser {
    private ArrayList<ClassOWL> classes;
    private ArrayList<IndividualOWL> individuals;
    private Gazzetteer gazzetteer;

    public Parser(Gazzetteer gazzetteer) {
	this.gazzetteer = gazzetteer;
	classes = new ArrayList<>();
	individuals = new ArrayList<>();
    }

    /**
     * @return the classes
     */
    public ArrayList<ClassOWL> getClasses() {
	return classes;
    }

    /**
     * @return the individuals
     */
    public ArrayList<IndividualOWL> getIndividuals() {
	return individuals;
    }

    public ClassOWL getClassByName(String name) {
	for (ClassOWL classOWL : classes) {
	    if (classOWL.getName().contains(name)) {
		return classOWL;
	    }
	}
	return null;
    }

    public IndividualOWL getIndividualByName(String name) {
	for (IndividualOWL individual : individuals) {
	    if (individual.getName().contains(name)) {
		return individual;
	    }
	}
	return null;
    }

    public void addType(Type type) {
	gazzetteer.addType(type);
    }

    public void addSubtype(Subtype subtype) {
	gazzetteer.addSubtype(subtype);
    }
}
