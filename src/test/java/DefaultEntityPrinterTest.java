import de.uniulm.in.ki.mbrenner.owlprinter.api.OWLEntityPrinter;
import de.uniulm.in.ki.mbrenner.owlprinter.OWLPrinter;
import org.junit.Assert;
import org.junit.Test;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;

/**
 * Test for the DefaultEntityPrinter
 * Created by spellmaker on 24.08.2016.
 */
public class DefaultEntityPrinterTest {
    private static final OWLDataFactory data = OWLManager.getOWLDataFactory();
    private static final OWLClass A = data.getOWLClass(IRI.create("http://chen.moe/A"));
    private static final OWLObjectProperty P = data.getOWLObjectProperty(IRI.create("http://chen.moe/P"));
    private static final OWLDataProperty D = data.getOWLDataProperty(IRI.create("http://chen.moe/D"));
    private static final OWLNamedIndividual a = data.getOWLNamedIndividual(IRI.create("http://chen.moe/a"));
    private static final OWLDatatype t = data.getOWLDatatype(IRI.create("http://chen.moe/t"));
    private static final OWLAnnotationProperty  ap = data.getOWLAnnotationProperty(IRI.create("http://chen.moe/ap"));

    private String testEntity(OWLEntity axiom){
        OWLEntityPrinter dap = OWLPrinter.entityPrinter;
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
        testString(testEntity(A), A);
    }

    @Test
    public void testOWLObjectProperty(){
        testString(testEntity(P), P);
    }

    @Test
    public void testOWLDataProperty(){
        testString(testEntity(D), D);
    }

    @Test
    public void testOWLNamedIndividual(){
        testString(testEntity(a), a);
    }

    @Test
    public void testOWLDatatype(){
        testString(testEntity(t), t);
    }

    @Test
    public void testOWLAnnotationProperty(){
        testString(testEntity(ap), ap);
    }
}
