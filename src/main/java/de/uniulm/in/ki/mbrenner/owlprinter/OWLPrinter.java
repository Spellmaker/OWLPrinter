package de.uniulm.in.ki.mbrenner.owlprinter;

import de.uniulm.in.ki.mbrenner.owlprinter.api.*;
import de.uniulm.in.ki.mbrenner.owlprinter.api.PrefixManager;
import de.uniulm.in.ki.mbrenner.owlprinter.impl.*;
import org.semanticweb.owlapi.model.*;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Core printer class to turn OWL data objects into nicely readable strings.
 * By default uses a decent configuration for printing, but components
 * can be exchanged to produce different results.
 *
 * Created by spellmaker on 17.05.2016.
 */
public class OWLPrinter {
    public static OWLClassPrinter classPrinter = new DefaultClassPrinter();
    public static OWLPropertyPrinter propertyPrinter = new DefaultPropertyPrinter();
    public static OWLAxiomPrinter axiomPrinter = new DefaultAxiomPrinter();
    public static OWLEntityPrinter entityPrinter = new DefaultEntityPrinter();
    public static OWLOntologyPrinter ontologyPrinter = new DefaultOWLOntologyPrinter();
    public static IRIPrinter iriPrinter = new DefaultIRIPrinter();
    public static PrefixManager prefixManager = new DefaultPrefixManager();
    public static Consumer<String> outputAction = System.out::print;

    /**
     * Configures the OWLPrinter
     * @param cp A printer for class expressions
     * @param pp A printer for property expressions
     * @param ap A printer for axioms
     * @param iri A printer for IRIs
     * @param ep A printer for entities
     */
    public static void config(OWLClassPrinter cp, OWLPropertyPrinter pp, OWLAxiomPrinter ap, IRIPrinter iri, OWLEntityPrinter ep){
        classPrinter = cp;
        propertyPrinter = pp;
        axiomPrinter = ap;
        iriPrinter = iri;
        entityPrinter = ep;
    }

    /**
     * Determines, if the textual representation of the class expressions needs additional brackets.
     * @param oce A class expression
     * @return The textual representation of the class expression or the representation enclosed in brackets
     */
    public static String getBracketString(OWLClassExpression oce){
        String s = classPrinter.getString(oce);
        if(!(oce instanceof OWLClass || oce instanceof OWLObjectSomeValuesFrom || oce instanceof OWLObjectComplementOf || oce instanceof OWLObjectAllValuesFrom)){
            return "(" + s + ")";
        }
        return s;
    }

    /**
     * Creates a textual representation of the class expression
     * @param oce A class expression
     * @return A textual representation
     */
    public static String getString(OWLClassExpression oce){
        return classPrinter.getString(oce);
    }

    public static String getString(OWLPropertyExpression p){
        return propertyPrinter.getString(p);
    }

    public static String getString(OWLAxiom a){
        return axiomPrinter.getString(a);
    }

    public static String getString(IRI iri){
        return iriPrinter.getString(iri);
    }

    public static String getString(OWLEntity entity){
        return entityPrinter.getString(entity);
    }

    public static String getString(OWLObject o){
        if(o == null) return "null";
        if(o instanceof OWLAxiom)
            return axiomPrinter.getString((OWLAxiom) o);
        else if(o instanceof OWLPropertyExpression)
            return propertyPrinter.getString((OWLPropertyExpression) o);
        else if(o instanceof OWLClassExpression)
            return classPrinter.getString((OWLClassExpression) o);
        else if(o instanceof IRI)
            return iriPrinter.getString((IRI) o);
        else if(o instanceof OWLEntity)
            return entityPrinter.getString((OWLEntity) o);
        else if(o instanceof OWLOntology)
            return ontologyPrinter.getString((OWLOntology) o);
        else
            return "ERROR";
    }

    public static String getString(Set<? extends OWLObject> set){
        if(set == null) return "null";
        if(set.isEmpty()) return "[]";
        else{
            Iterator<? extends OWLObject> e = set.iterator();
            String s = "[" + getString(e.next());
            while(e.hasNext()) s += ", " + getString(e.next());
            s += "]";
            return s;
        }
    }

    public static String getString(Object o){
        if(o == null) return "null";
        if (o instanceof OWLObject) return getString((OWLObject) o);
        return o.toString();
    }

    public static void print(Set<? extends OWLObject> set){
        outputAction.accept(getString(set));
    }

    public static void println(Set<? extends OWLObject> set){
        outputAction.accept(getString(set) + System.lineSeparator());
    }

    public static String getString(Map<OWLObject, OWLObject> map){
        Iterator<Map.Entry<OWLObject, OWLObject>> iterator = map.entrySet().iterator();
        if(!iterator.hasNext()) return "";
        Map.Entry<OWLObject, OWLObject> e = iterator.next();
        String s = getString(e.getKey()) + " -> " + getString(e.getValue());

        while(iterator.hasNext()){
            e = iterator.next();
            s += "\n" + getString(e.getKey()) + " -> " + getString(e.getValue());
        }

        return s;
    }

    public static void print(Map<OWLObject, OWLObject> map){
        outputAction.accept(getString(map));
    }

    public static void println(Map<OWLObject, OWLObject> map){
        outputAction.accept(getString(map) + System.lineSeparator());
    }

    public static void print(OWLObject o){
        outputAction.accept(getString(o));
    }

    public static void println(OWLObject o){

        outputAction.accept(getString(o) + System.lineSeparator());
    }



}
