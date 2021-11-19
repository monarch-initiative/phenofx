package org.monarchinitiative.phenofx.ontology.tree.selectable;

import javafx.scene.control.TreeItem;
import org.monarchinitiative.phenofx.ontology.tree.base.OntologyTreeItemBase;
import org.monarchinitiative.phenol.ontology.data.Ontology;
import org.monarchinitiative.phenol.ontology.data.Term;


public class SelectableOntologyTreeItem extends OntologyTreeItemBase<SelectableOntologyTreeTerm> {

    public static SelectableOntologyTreeItem of(Ontology ontology, Term term) {
        SelectableOntologyTreeTerm treeTerm = SelectableOntologyTreeTerm.of(term);
        return new SelectableOntologyTreeItem(ontology, treeTerm);
    }

    private SelectableOntologyTreeItem(Ontology ontology, SelectableOntologyTreeTerm value) {
        super(ontology, value);
    }

    @Override
    protected TreeItem<SelectableOntologyTreeTerm> treeItemForTerm(Ontology ontology, Term term) {
        return of(ontology, term);
    }

}
