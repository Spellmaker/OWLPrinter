import de.uniulm.in.ki.mbrenner.owlprinter.api.OWLClassPrinter;
import de.uniulm.in.ki.mbrenner.owlprinter.OWLPrinter;
import org.junit.Assert;
import org.junit.Test;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;

/**
 * Test for the DefaultClassPrinter
 * Created by spellmaker on 24.08.2016.
 */
public class DefaultClassPrinterTest {
    private static final OWLDataFactory data = OWLManager.getOWLDataFactory();
    private static final OWLClass A = data.getOWLClass(IRI.create("http://chen.moe/A"));
    private static final OWLClass B = data.getOWLClass(IRI.create("http://chen.moe/B"));
    private static final OWLObjectProperty P = data.getOWLObjectProperty(IRI.create("http://chen.moe/P"));

    private String testExpression(OWLClassExpression axiom){
        OWLClassPrinter dap = OWLPrinter.classPrinter;
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
    public void testOWLClass(){
        testString(testExpression(A), A);
    }

    @Test
    public void testOWLObjectIntersectionOf(){
        testString(testExpression(data.getOWLObjectIntersectionOf(A, B)), A, B);
    }

    @Test
    public void testOWLObjectSomeValuesFrom(){
        testString(testExpression(data.getOWLObjectSomeValuesFrom(P, A)), P, A);
    }

    @Test
    public void testOWLObjectUnionOf(){
        testString(testExpression(data.getOWLObjectUnionOf(A, B)), A, B);
    }

    @Test
    public void testOWLObjectComplementOf(){
        testString(testExpression(data.getOWLObjectComplementOf(A)), A);
    }
}
