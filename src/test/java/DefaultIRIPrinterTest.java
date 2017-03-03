import de.uniulm.in.ki.mbrenner.owlprinter.OWLPrinter;
import org.junit.Assert;
import org.junit.Test;
import org.semanticweb.owlapi.model.IRI;

/**
 * Test for the DefaultIRIPrinter
 * Created by spellmaker on 24.08.2016.
 */
public class DefaultIRIPrinterTest {
    @Test
    public void testIRI(){
        String name = "AVeryLongIRI";
        String iri = "http://chen.moe/" + name;
        String s = OWLPrinter.iriPrinter.getString(IRI.create(iri));
        Assert.assertTrue("String should not be null", s != null);
        Assert.assertTrue("String should not be empty", s.length() > 0);
        Assert.assertTrue("String should at least contain the entity name, got '" + s + "'", s.contains(name));
    }
}
