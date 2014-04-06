/**
 * 
 */
package Main;

import Model.Gazzetteer;
import Model.Parser;
import Model.util.XMLReader;

/**
 * @author Robert
 * 
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
	// TODO Auto-generated method stub
	Gazzetteer gazzetteer  = new Gazzetteer();
	Parser parser = new Parser(gazzetteer);
	XMLReader.loadXML(parser);
    }

}
