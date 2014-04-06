package Model;

public class Subtype {
    private String name;
    private Type type;

    public Subtype(String name) {
	this.name = name;
    }

    public Subtype(String name, Type type) {
	this.name = name;
	this.type = type;
    }

    /**
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @return the title
     */
    public Type getType() {
	return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(Type type) {
	this.type = type;
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
	result = prime * result + ((type == null) ? 0 : type.hashCode());
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
	Subtype other = (Subtype) obj;
	if (name == null) {
	    if (other.name != null) return false;
	} else if (!name.equals(other.name)) return false;
	if (type == null) {
	    if (other.type != null) return false;
	} else if (!type.equals(other.type)) return false;
	return true;
    }

}
