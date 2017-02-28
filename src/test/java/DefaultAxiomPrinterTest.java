import de.uniulm.in.ki.mbrenner.owlprinter.api.OWLAxiomPrinter;
import de.uniulm.in.ki.mbrenner.owlprinter.OWLPrinter;
import org.junit.Assert;
import org.junit.Test;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Test for the DefaultAxiomPrinter
 * Created by spellmaker on 24.08.2016.
 */
public class DefaultAxiomPrinterTest {
    private static final OWLDataFactory data = OWLManager.getOWLDataFactory();
    private static final OWLClass A = data.getOWLClass(IRI.create("http://chen.moe/A"));
    private static final OWLClass B = data.getOWLClass(IRI.create("http://chen.moe/B"));
    private static final OWLObjectProperty P = data.getOWLObjectProperty(IRI.create("http://chen.moe/P"));
    private static final OWLObjectProperty Q = data.getOWLObjectProperty(IRI.create("http://chen.moe/Q"));
    private static final OWLObjectProperty R = data.getOWLObjectProperty(IRI.create("http://chen.moe/R"));
    private static final OWLNamedIndividual a = data.getOWLNamedIndividual(IRI.create("http://chen.moe/a"));
    private static final OWLNamedIndividual b = data.getOWLNamedIndividual(IRI.create("http://chen.moe/b"));

    private String testAxiom(OWLAxiom axiom){
        OWLAxiomPrinter dap = OWLPrinter.axiomPrinter;
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
    public void testOWLDeclarationAxiom(){
        testString(testAxiom(data.getOWLDeclarationAxiom(A)), A);
    }
    @Test
    public void testOWLSubClassOfAxiom(){
        testString(testAxiom(data.getOWLSubClassOfAxiom(A, B)), A, B);
    }
    @Test
    public void testOWLSubObjectPropertyOfAxiom(){
        testString(testAxiom(data.getOWLSubObjectPropertyOfAxiom(P, Q)), P, Q);
    }
    @Test
    public void testOWLClassAssertionAxiom(){
        testString(testAxiom(data.getOWLClassAssertionAxiom(A, a)), A, a);
    }

    @Test
    public void testOWLEquivalentClassesAxiom(){
        testString(testAxiom(data.getOWLEquivalentClassesAxiom(A, B)), A, B);
    }

    @Test
    public void testOWLTransitiveObjectPropertyAxiom(){
        testString(testAxiom(data.getOWLTransitiveObjectPropertyAxiom(P)), P);
    }

    @Test
    public void testOWLInverseObjectPropertiesAxiom(){
        testString(testAxiom(data.getOWLInverseObjectPropertiesAxiom(P, Q)), P, Q);
    }

    @Test
    public void testOWLObjectPropertyAssertionAxiom(){
        testString(testAxiom(data.getOWLObjectPropertyAssertionAxiom(P, a, b)), P, a, b);
    }

    @Test
    public void testOWLObjectPropertyDomainAxiom(){
        testString(testAxiom(data.getOWLObjectPropertyDomainAxiom(P, A)), P, A);
    }

    @Test
    public void testOWLObjectPropertyRangeAxiom(){
        testString(testAxiom(data.getOWLObjectPropertyRangeAxiom(P, A)), P, A);
    }

    @Test
    public void testOWLObjectPropertyChainOfAxiom(){
        List<OWLObjectProperty> chain = new LinkedList<>();
        chain.add(P);
        chain.add(Q);
        testString(testAxiom(data.getOWLSubPropertyChainOfAxiom(chain, R)), P, Q, R);

    }
}
