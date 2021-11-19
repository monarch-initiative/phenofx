package org.monarchinitiative.phenofx.ontology.model;

import org.monarchinitiative.phenol.ontology.data.Term;

public interface OntologyTerm {

    static OntologyTerm of(Term term) {
        return new OntologyTermDefault(term);
    }

    Term term();

}
