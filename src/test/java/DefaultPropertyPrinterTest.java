import de.uniulm.in.ki.mbrenner.owlprinter.OWLPrinter;
import de.uniulm.in.ki.mbrenner.owlprinter.api.OWLPropertyPrinter;
import org.junit.Assert;
import org.junit.Test;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;

/**
 * Test for the DefaultPropertyPrinter
 * Created by spellmaker on 24.08.2016.
 */
public class DefaultPropertyPrinterTest {
    private static final OWLDataFactory data = OWLManager.getOWLDataFactory();
    private static final OWLObjectProperty P = data.getOWLObjectProperty(IRI.create("http://chen.moe/P"));

    private String testExpression(OWLPropertyExpression axiom){
        OWLPropertyPrinter dap = OWLPrinter.propertyPrinter;
        String s = dap.getString(axiom);
        Assert.assertTrue("String should not be null", s != null);
        Assert.assertTrue("String should not be empty", s.length() > 0);
        return s;
    }

    private void testString(String s, OWLNamedObject...obj){
        for(OWLNamedObject o : obj){
            Assert.assertTrue("String should contain the printed IRIs of its operands", s.contains(OWLPrinter.iriPrinter.getString(o.getIRI())));
        }
    }

    @Test
    public void testOWLObjectProperty(){
        testString(testExpression(P), P);
    }

    @Test
    public void testOWLObjectInverseOf(){
        testString(testExpression(data.getOWLObjectInverseOf(P)), P);
    }
}
