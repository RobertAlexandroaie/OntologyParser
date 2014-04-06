package Model;

import java.util.ArrayList;

public class Type {
    private String name;
    private ArrayList<Subtype> subtypes;

    public Type() {
	subtypes = new ArrayList<>();
    }

    public Type(String name) {
	this();
	this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @return the subtypes
     */
    public ArrayList<Subtype> getSubtypes() {
	return subtypes;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + ((subtypes == null) ? 0 : subtypes.hashCode());
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj) return true;
	if (obj == null) return false;
	if (getClass() != obj.getClass()) return false;
	Type other = (Type) obj;
	if (name == null) {
	    if (other.name != null) return false;
	} else if (!name.equals(other.name)) return false;
	if (subtypes == null) {
	    if (other.subtypes != null) return false;
	} else if (!subtypes.equals(other.subtypes)) return false;
	return true;
    }

    public void addSubtype(Subtype subtype) {
	if (!subtypes.contains(subtype)) {
	    subtypes.add(subtype);
	}
    }
}
