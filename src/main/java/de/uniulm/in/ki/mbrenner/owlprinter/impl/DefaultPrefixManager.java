package de.uniulm.in.ki.mbrenner.owlprinter.impl;

import de.uniulm.in.ki.mbrenner.owlprinter.api.PrefixManager;
import org.semanticweb.owlapi.model.IRI;

import java.util.HashMap;
import java.util.Map;

/**
 * Default Prefix Manager implementation.
 * Provides numeric prefixes, using different numbers for different IRI patterns
 * <p>
 * Created by Markus Brenner on 02.03.2017.
 */
public class DefaultPrefixManager implements PrefixManager {
    private Map<String, String> map;
    private int count;

    /**
     * Default constructor
     */
    public DefaultPrefixManager() {
        map = new HashMap<>();
        count = 0;
    }

    @Override
    public String getPrefix(IRI iri) {
        return map.computeIfAbsent(extractIRIPrefix(iri), x -> "" + count++) + ":";
    }
}
