package de.uniulm.in.ki.mbrenner.owlprinter.api;

import org.semanticweb.owlapi.model.OWLPropertyExpression;

/**
 * Transforms an OWLPropertyExpression into a printable String
 * Created by spellmaker on 17.05.2016.
 */
@FunctionalInterface
public interface OWLPropertyPrinter {
    String getString(OWLPropertyExpression property);
}
