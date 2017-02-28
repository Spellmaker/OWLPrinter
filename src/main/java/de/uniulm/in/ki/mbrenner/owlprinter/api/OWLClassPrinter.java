package de.uniulm.in.ki.mbrenner.owlprinter.api;

import org.semanticweb.owlapi.model.OWLClassExpression;

/**
 * Transforms an OWLClassExpression into a printable String
 * Created by spellmaker on 17.05.2016.
 */
@FunctionalInterface
public interface OWLClassPrinter {
    String getString(OWLClassExpression oce);
}
