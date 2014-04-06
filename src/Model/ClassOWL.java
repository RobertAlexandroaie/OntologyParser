package Model;

import java.util.ArrayList;

public class ClassOWL {
    private String name;
    private ArrayList<IndividualOWL> individuals;

    public ClassOWL(String name) {
	this.name = name;
	individuals = new ArrayList<>();
    }

    /**
     * @return the name
     */
    public String getName() {
	return name;
    }

    public void addIndividual(IndividualOWL individual) {
	if (!individuals.contains(individual)) {
	    individuals.add(individual);
	}
    }
}
