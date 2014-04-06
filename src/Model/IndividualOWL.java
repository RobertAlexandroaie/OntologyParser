package Model;

public class IndividualOWL {
    private String name;
    private ClassOWL classOWL;

    public IndividualOWL(String name) {
	this.name = name;
    }

    public IndividualOWL(String name, ClassOWL classOWL) {
	this.name = name;
	this.classOWL = classOWL;
    }

    /**
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @return the classOWL
     */
    public ClassOWL getClassOWL() {
	return classOWL;
    }

    /**
     * @param classOWL
     *            the classOWL to set
     */
    public void setClassOWL(ClassOWL classOWL) {
	this.classOWL = classOWL;
    }

}
