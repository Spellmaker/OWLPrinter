package de.uniulm.in.ki.mbrenner.owlprinter.api;

import org.semanticweb.owlapi.model.IRI;

/**
 * Turns IRI objects into printable Strings
 * Created by spellmaker on 17.05.2016.
 */
@FunctionalInterface
public interface IRIPrinter {
    String getString(IRI iri);
}
