package org.monarchinitiative.phenofx.ontology.model;

import org.monarchinitiative.phenol.ontology.data.TermId;

import java.time.Period;

public interface PhenotypeDescription {
    TermId getTermId();

    String getLabel();

    Period getOnset();

    Period getResolution();

    boolean isPresent();
}
