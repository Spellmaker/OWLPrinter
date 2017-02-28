package de.uniulm.in.ki.mbrenner.owlprinter.impl;

import de.uniulm.in.ki.mbrenner.owlprinter.OWLPrinter;
import de.uniulm.in.ki.mbrenner.owlprinter.api.OWLEntityPrinter;
import org.semanticweb.owlapi.model.*;

/**
 * Default OWLEntityPrinter implementation
 * Created by spellmaker on 17.05.2016.
 */
public class DefaultEntityPrinter implements OWLEntityVisitorEx<String>, OWLEntityPrinter {
    @Override
    public String visit(OWLClass owlClass) {
        return OWLPrinter.getString(owlClass.getIRI());
    }

    @Override
    public String visit(OWLObjectProperty owlObjectProperty) {
        return OWLPrinter.getString(owlObjectProperty.getIRI());
    }

    @Override
    public String visit(OWLDataProperty owlDataProperty) {
        return OWLPrinter.getString(owlDataProperty.getIRI());
    }

    @Override
    public String visit(OWLNamedIndividual owlNamedIndividual) {
        return OWLPrinter.getString(owlNamedIndividual.getIRI());
    }

    @Override
    public String visit(OWLDatatype owlDatatype) {
        return OWLPrinter.getString(owlDatatype.getIRI());
    }

    @Override
    public String visit(OWLAnnotationProperty owlAnnotationProperty) {
        return OWLPrinter.getString(owlAnnotationProperty.getIRI());
    }

    @Override
    public String getString(OWLEntity entity) {
        return entity.accept(this);
    }
}
