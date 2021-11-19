package org.monarchinitiative.phenofx.ontology.tree.simple;

import org.monarchinitiative.phenofx.ontology.tree.base.OntologyTreeTermBase;
import org.monarchinitiative.phenol.ontology.data.Term;

public class SimpleOntologyTreeTerm extends OntologyTreeTermBase {

    public static SimpleOntologyTreeTerm of(Term term) {
        return new SimpleOntologyTreeTerm(term);
    }

    private SimpleOntologyTreeTerm(Term term) {
        super(term);
    }

    @Override
    public String toString() {
        return "SimpleOntologyTreeTerm{} " + super.toString();
    }
}
