package de.uniulm.in.ki.mbrenner.owlprinter.api;

import org.semanticweb.owlapi.model.OWLEntity;

/**
 * Transforms an OWLEntity into a printable String
 * Created by spellmaker on 17.05.2016.
 */
@FunctionalInterface
public interface OWLEntityPrinter {
    String getString(OWLEntity entity);
}
