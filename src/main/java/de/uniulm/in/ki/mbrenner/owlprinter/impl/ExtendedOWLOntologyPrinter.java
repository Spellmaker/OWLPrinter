package de.uniulm.in.ki.mbrenner.owlprinter.impl;

import de.uniulm.in.ki.mbrenner.owlprinter.OWLPrinter;
import de.uniulm.in.ki.mbrenner.owlprinter.api.OWLOntologyPrinter;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.parameters.Imports;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Markus Brenner on 12.01.2017.
 */
public class ExtendedOWLOntologyPrinter implements OWLOntologyPrinter{
    public enum Options{
        HIDE_DECLARATION_AXIOMS, SEPARATE_BOXES;
    }

    private boolean printDecls = true;
    private boolean separateBoxes = false;
    private String delimiter = "\n";

    public ExtendedOWLOntologyPrinter(Options... options){
        super();

        for(Options o : options){
            switch (o){
                case HIDE_DECLARATION_AXIOMS: printDecls = false; break;
                case SEPARATE_BOXES: separateBoxes = true; break;
            }
        }
    }

    public ExtendedOWLOntologyPrinter(String delimiter, Options... options){
        super();

        this.delimiter = delimiter;
        for(Options o : options){
            switch (o){
                case HIDE_DECLARATION_AXIOMS: printDecls = false; break;
                case SEPARATE_BOXES: separateBoxes = true; break;
            }
        }
    }

    @Override
    public String getString(OWLOntology o) {
        Set<OWLAxiom> printableAxioms = o.getAxioms().stream().filter(x -> printDecls || !(x instanceof OWLDeclarationAxiom)).collect(Collectors.toSet());

        if(separateBoxes){
            String res = "TBOX:" + delimiter;
            Set<OWLAxiom> abox = o.getABoxAxioms(Imports.INCLUDED);
            Set<OWLAxiom> rbox = o.getRBoxAxioms(Imports.INCLUDED);
            res += printableAxioms.stream().filter(x -> !abox.contains(x) && !rbox.contains(x)).map(x -> OWLPrinter.getString(x)).collect(Collectors.joining(delimiter));
            res += delimiter + "RBOX:" + delimiter + rbox.stream().map(x -> OWLPrinter.getString(x)).collect(Collectors.joining(delimiter));
            res += delimiter + "ABOX:" + delimiter + abox.stream().map(x -> OWLPrinter.getString(x)).collect(Collectors.joining(delimiter));
            return res;
        }
        else{
            return printableAxioms.stream().map(x -> OWLPrinter.axiomPrinter.getString(x)).collect(Collectors.joining(delimiter));
        }
    }
}
