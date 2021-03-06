package org.monarchinitiative.phenofx.ontology.tree.base;

import org.monarchinitiative.phenofx.ontology.model.OntologyTerm;
import org.monarchinitiative.phenol.ontology.data.Term;

import java.util.Objects;

public abstract class OntologyTreeTermBase implements OntologyTerm {

    protected final Term term;

    public OntologyTreeTermBase(Term term) {
        this.term = term;
    }

    public Term term() {
        return term;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OntologyTreeTermBase that = (OntologyTreeTermBase) o;
        return Objects.equals(term, that.term);
    }

    @Override
    public int hashCode() {
        return Objects.hash(term);
    }

    @Override
    public String toString() {
        return "OntologyTreeTermBase{" +
                "term=" + term +
                '}';
    }
}
