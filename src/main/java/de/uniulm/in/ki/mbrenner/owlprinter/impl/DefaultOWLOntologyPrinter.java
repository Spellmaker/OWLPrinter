package de.uniulm.in.ki.mbrenner.owlprinter.impl;

import de.uniulm.in.ki.mbrenner.owlprinter.OWLPrinter;
import de.uniulm.in.ki.mbrenner.owlprinter.api.OWLOntologyPrinter;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLOntology;

import java.util.stream.Collectors;

/**
 * Created by Markus Brenner on 11.01.2017.
 */
public class DefaultOWLOntologyPrinter implements OWLOntologyPrinter{
    public DefaultOWLOntologyPrinter(){
        super();
    }


    @Override
    public String getString(OWLOntology o) {
        return o.getAxioms().stream().map(x -> OWLPrinter.axiomPrinter.getString(x)).collect(Collectors.joining("\n"));
    }
}
