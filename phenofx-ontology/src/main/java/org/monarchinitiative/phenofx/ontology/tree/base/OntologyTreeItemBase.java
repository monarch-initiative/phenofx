package org.monarchinitiative.phenofx.ontology.tree.base;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import org.monarchinitiative.phenol.ontology.algo.OntologyAlgorithm;
import org.monarchinitiative.phenol.ontology.data.Ontology;
import org.monarchinitiative.phenol.ontology.data.Term;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class OntologyTreeItemBase<T extends OntologyTreeTermBase> extends TreeItem<T> {

    private final Ontology ontology;

    private ObservableList<TreeItem<T>> children;

    protected OntologyTreeItemBase(Ontology ontology, T value) {
        super(value);
        this.ontology = ontology;
    }

    protected abstract TreeItem<T> treeItemForTerm(Ontology ontology, Term term);


    @Override
    public boolean isLeaf() {
        return OntologyAlgorithm.getChildTerms(ontology, getValue().term().getId(), false).isEmpty();
    }

    @Override
    public ObservableList<TreeItem<T>> getChildren() {
        if (children == null) {
            children = FXCollections.observableArrayList();
            Set<Term> childrenTerms = OntologyAlgorithm.getChildTerms(ontology, getValue().term().getId(), false).stream()
                    .map(ontology.getTermMap()::get)
                    .collect(Collectors.toUnmodifiableSet());

            children = childrenTerms.stream()
                    .sorted(Comparator.comparing(Term::getName))
                    .map(term -> treeItemForTerm(ontology, term))
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
            super.getChildren().setAll(children);
        }

        return super.getChildren();
    }
}
