package org.monarchinitiative.phenofx.ontology.tree.simple;

import javafx.scene.control.TreeItem;
import org.monarchinitiative.phenofx.ontology.tree.base.OntologyTreeItemBase;
import org.monarchinitiative.phenol.ontology.data.Ontology;
import org.monarchinitiative.phenol.ontology.data.Term;

public class SimpleOntologyTreeItem extends OntologyTreeItemBase<SimpleOntologyTreeTerm> {

    public static SimpleOntologyTreeItem of(Ontology ontology, Term term) {
        SimpleOntologyTreeTerm treeTerm = SimpleOntologyTreeTerm.of(term);
        return new SimpleOntologyTreeItem(ontology, treeTerm);
    }

    private SimpleOntologyTreeItem(Ontology ontology, SimpleOntologyTreeTerm term) {
        super(ontology, term);
    }

    @Override
    protected TreeItem<SimpleOntologyTreeTerm> treeItemForTerm(Ontology ontology, Term term) {
        return of(ontology, term);
    }

}
