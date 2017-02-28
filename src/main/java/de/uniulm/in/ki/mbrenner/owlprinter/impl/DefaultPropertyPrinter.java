package de.uniulm.in.ki.mbrenner.owlprinter.impl;

import de.uniulm.in.ki.mbrenner.owlprinter.OWLChars;
import de.uniulm.in.ki.mbrenner.owlprinter.OWLPrinter;
import de.uniulm.in.ki.mbrenner.owlprinter.api.OWLPropertyPrinter;
import de.uniulm.in.ki.mbrenner.owlapiaddons.visitor.PropertyVisitorAdapterEx;
import org.semanticweb.owlapi.model.OWLObjectInverseOf;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLPropertyExpression;

/**
 * Default OWLPropertyPrinter implementation
 * Created by spellmaker on 17.05.2016.
 */
public class DefaultPropertyPrinter extends PropertyVisitorAdapterEx<String> implements OWLPropertyPrinter {
    @Override
    public String visit(OWLObjectProperty owlObjectProperty) {
        return OWLPrinter.getString(owlObjectProperty.getIRI());
    }

    @Override
    public String getString(OWLPropertyExpression property) {
        return property.accept(this).toString();
    }

    @Override
    public String visit(OWLObjectInverseOf property){
        return OWLPrinter.getString(property.getInverse()) + OWLChars.inverse;
    }
}
