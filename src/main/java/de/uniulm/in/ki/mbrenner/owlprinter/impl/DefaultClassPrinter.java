package de.uniulm.in.ki.mbrenner.owlprinter.impl;

import de.uniulm.in.ki.mbrenner.owlprinter.OWLChars;
import de.uniulm.in.ki.mbrenner.owlprinter.OWLPrinter;
import de.uniulm.in.ki.mbrenner.owlprinter.api.OWLClassPrinter;
import de.uniulm.in.ki.mbrenner.owlapiaddons.visitor.ClassVisitorAdapterEx;
import org.semanticweb.owlapi.model.*;

import java.util.Iterator;

/**
 * Default OWLClassPrinter implementation
 * Created by spellmaker on 17.05.2016.
 */
public class DefaultClassPrinter extends ClassVisitorAdapterEx<String> implements OWLClassPrinter {
    @Override
    public String getString(OWLClassExpression oce) {
        return oce.accept(this);
    }

    @Override
    public String visit(OWLClass c){
        if(c.isBottomEntity()) return "" + OWLChars.bottom;
        else if(c.isTopEntity()) return "" + OWLChars.top;
        else return OWLPrinter.getString(c.getIRI());
    }

    @Override
    public String visit(OWLObjectIntersectionOf c){
        String result = "";
        Iterator<OWLClassExpression> iter = c.getOperands().iterator();
        result += OWLPrinter.getBracketString(iter.next());
        while(iter.hasNext()){
            result += " " + OWLChars.and + " " + OWLPrinter.getBracketString(iter.next());
        }
        return result;
    }

    @Override
    public String visit(OWLObjectSomeValuesFrom c){
        return OWLChars.exists + OWLPrinter.getString(c.getProperty()) + "." + OWLPrinter.getBracketString(c.getFiller());
    }

    @Override
    public String visit(OWLObjectAllValuesFrom c){
        return OWLChars.forall + OWLPrinter.getString(c.getProperty()) + "." + OWLPrinter.getBracketString(c.getFiller());
    }

    @Override
    public String visit(OWLObjectUnionOf c){
        String result = "";
        Iterator<OWLClassExpression> iter = c.getOperands().iterator();
        result += OWLPrinter.getBracketString(iter.next());
        while(iter.hasNext()){
            result += OWLChars.or + OWLPrinter.getBracketString(iter.next());
        }
        return result;
    }

    @Override
    public String visit(OWLObjectMinCardinality c){
        return "" + OWLChars.leq + c.getCardinality() + " " + OWLPrinter.getString(c.getProperty()) + "." + OWLPrinter.getString(c.getFiller());
    }

    @Override
    public String visit(OWLObjectMaxCardinality c){
        return "" + OWLChars.geq + c.getCardinality() + " " + OWLPrinter.getString(c.getProperty()) + "." + OWLPrinter.getString(c.getFiller());
    }

    @Override
    public String visit(OWLObjectComplementOf c){
        return OWLChars.negation + OWLPrinter.getBracketString(c.getOperand());
    }
}
