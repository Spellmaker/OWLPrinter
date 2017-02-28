package de.uniulm.in.ki.mbrenner.owlprinter.impl;

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
        s = s.substring(s.lastIndexOf("/") + 1);
        s = s.substring(s.lastIndexOf("#") + 1);
        return s;
    }
}
