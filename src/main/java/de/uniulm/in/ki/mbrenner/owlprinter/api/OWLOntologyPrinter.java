package de.uniulm.in.ki.mbrenner.owlprinter.api;

import org.semanticweb.owlapi.model.OWLOntology;

/**
 * Created by Markus Brenner on 11.01.2017.
 */
public interface OWLOntologyPrinter {
    String getString(OWLOntology o);
}
