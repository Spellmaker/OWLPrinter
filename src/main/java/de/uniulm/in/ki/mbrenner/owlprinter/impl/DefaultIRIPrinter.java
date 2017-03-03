package de.uniulm.in.ki.mbrenner.owlprinter.impl;

import de.uniulm.in.ki.mbrenner.owlprinter.OWLPrinter;
import de.uniulm.in.ki.mbrenner.owlprinter.api.IRIPrinter;
import org.semanticweb.owlapi.model.IRI;

/**
 * Default IRIPrinter implementation.
 * Tries to remove longer URL parts before the actual name
 * Created by spellmaker on 20.05.2016.
 */
public class DefaultIRIPrinter implements IRIPrinter {
    @Override
    public String getString(IRI iri) {
        String s = iri.toString();
        if (s.contains("#")) {
            s = s.substring(s.lastIndexOf("#") + 1);
        } else {
            s = s.substring(s.lastIndexOf("/") + 1);
        }
        return OWLPrinter.prefixManager.getPrefix(iri) + s;
    }
}
