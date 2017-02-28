package de.uniulm.in.ki.mbrenner.owlprinter.impl;

import de.uniulm.in.ki.mbrenner.owlprinter.OWLChars;
import de.uniulm.in.ki.mbrenner.owlprinter.OWLPrinter;
import de.uniulm.in.ki.mbrenner.owlprinter.api.OWLAxiomPrinter;
import de.uniulm.in.ki.mbrenner.owlapiaddons.visitor.AxiomVisitorAdapterEx;
import org.semanticweb.owlapi.model.*;
import java.util.Iterator;

/**
 * Default OWLAxiomPrinter implementation.
 *
 * Created by spellmaker on 17.05.2016.
 */
public class DefaultAxiomPrinter extends AxiomVisitorAdapterEx<String> implements OWLAxiomPrinter {
    @Override
    public String getString(OWLAxiom axiom) {
        return axiom.accept(this);
    }

    @Override
    public String visit(OWLDeclarationAxiom owlDeclarationAxiom) {
        return "Decl(" + OWLPrinter.getString(owlDeclarationAxiom.getEntity()) + ")";
    }

    @Override
    public String visit(OWLSubClassOfAxiom owlSubClassOfAxiom) {
        return OWLPrinter.getString(owlSubClassOfAxiom.getSubClass()) +
                OWLChars.subset +
                OWLPrinter.getString(owlSubClassOfAxiom.getSuperClass());
    }

    @Override
    public String visit(OWLSubObjectPropertyOfAxiom owlSubObjectPropertyOfAxiom) {
        return OWLPrinter.getString(owlSubObjectPropertyOfAxiom.getSubProperty()) +
                OWLChars.subset +
                OWLPrinter.getString(owlSubObjectPropertyOfAxiom.getSuperProperty());
    }

    @Override
    public String visit(OWLClassAssertionAxiom owlClassAssertionAxiom) {
        return OWLPrinter.getString(owlClassAssertionAxiom.getClassExpression()) + "(" +
                OWLPrinter.getString(owlClassAssertionAxiom.getIndividual()) + ")";
    }

    @Override
    public String visit(OWLEquivalentClassesAxiom owlEquivalentClassesAxiom) {
        return OWLPrinter.getString(owlEquivalentClassesAxiom.getClassExpressionsAsList().get(0)) +
                OWLChars.equiv +
                OWLPrinter.getString(owlEquivalentClassesAxiom.getClassExpressionsAsList().get(1));
    }

    @Override
    public String visit(OWLTransitiveObjectPropertyAxiom owlTransitiveObjectPropertyAxiom) {
        return "Trans(" + OWLPrinter.getString(owlTransitiveObjectPropertyAxiom.getProperty()) + ")";
    }

    @Override
    public String visit(OWLInverseObjectPropertiesAxiom axiom){
        return OWLPrinter.getString(axiom.getFirstProperty()) + OWLChars.equiv + OWLPrinter.getString(axiom.getSecondProperty()) + OWLChars.inverse;
    }

    @Override
    public String visit(OWLObjectPropertyAssertionAxiom axiom){
        return OWLPrinter.getString(axiom.getProperty()) + "(" + OWLPrinter.getString(axiom.getSubject()) + ", " + OWLPrinter.getString(axiom.getObject()) + ")";
    }

    @Override
    public String visit(OWLObjectPropertyDomainAxiom axiom){
        return "Domain(" + OWLPrinter.getString(axiom.getProperty()) + ", " + OWLPrinter.getString(axiom.getDomain()) + ")";
    }


    @Override
    public String visit(OWLObjectPropertyRangeAxiom axiom){
        return "Range(" + OWLPrinter.getString(axiom.getProperty()) + ", " + OWLPrinter.getString(axiom.getRange()) + ")";
    }

    @Override
    public String visit(OWLSubPropertyChainOfAxiom axiom){
        Iterator<OWLObjectPropertyExpression> chain = axiom.getPropertyChain().iterator();
        String r = "" + OWLPrinter.getString(chain.next());
        while(chain.hasNext()) r += " o " + OWLPrinter.getString(chain.next());
        r += " " + OWLChars.subset + " " + OWLPrinter.getString(axiom.getSuperProperty());
        return r;
    }
}
