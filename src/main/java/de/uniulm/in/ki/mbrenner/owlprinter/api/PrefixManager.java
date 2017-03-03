package de.uniulm.in.ki.mbrenner.owlprinter.api;

import org.semanticweb.owlapi.model.IRI;

/**
 * Manages prefixes.
 * Purpose of this tool is to keep IRIs abbreviated for printing, but
 * still inform the user about possibly different IRIs with the same
 * fragment.
 * In particular, two IRIs http://example1.com/ex#House and http:/example2.com/ex#House
 * should be separable
 * <p>
 * Created by Markus Brenner on 02.03.2017.
 */
public interface PrefixManager {
    /**
     * Obtains a prefix for the provided IRI.
     * The contract of this method is, that the same IRI pattern (up to the
     * actual fragment) should produce the same prefix
     *
     * @param iri A complete IRI, for which a prefix should be obtained
     * @return A prefix string for the IRI
     */
    String getPrefix(IRI iri);

    /**
     * Helper function which extracts the part of an IRI, which is not the name
     * of the entity
     *
     * @param iri An IRI
     * @return The non-name part of the IRI
     */
    default String extractIRIPrefix(IRI iri) {
        String s = iri.toString();
        if (s.contains("#")) {
            return s.substring(0, s.lastIndexOf("#"));
        }
        return s.substring(0, s.lastIndexOf("/"));
    }
}
