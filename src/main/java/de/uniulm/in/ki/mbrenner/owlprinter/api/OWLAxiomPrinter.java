package de.uniulm.in.ki.mbrenner.owlprinter.api;

import org.semanticweb.owlapi.model.OWLAxiom;

/**
 * Transforms an OWLAxiom into a printable String
 * Created by spellmaker on 17.05.2016.
 */
@FunctionalInterface
public interface OWLAxiomPrinter {
    String getString(OWLAxiom axiom);
}
