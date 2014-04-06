package Model;

import java.util.ArrayList;

public class Gazzetteer {
    ArrayList<Type> types;
    ArrayList<Subtype> subtypes;

    public Gazzetteer() {
	types = new ArrayList<>();
	subtypes = new ArrayList<>();
    }

    /**
     * @return the types
     */
    public ArrayList<Type> getTypes() {
	return types;
    }

    /**
     * @return the subtypes
     */
    public ArrayList<Subtype> getSubtypes() {
	return subtypes;
    }

    public void addType(Type type) {
	types.add(type);
    }

    public void addSubtype(Subtype subtype) {
	subtypes.add(subtype);
    }
}
