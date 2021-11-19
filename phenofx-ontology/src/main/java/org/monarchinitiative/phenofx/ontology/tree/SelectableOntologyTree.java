package org.monarchinitiative.phenofx.ontology.tree;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.util.Callback;
import org.monarchinitiative.phenofx.ontology.model.SelectableOntologyTerm;
import org.monarchinitiative.phenofx.ontology.tree.selectable.SelectableOntologyTreeCell;
import org.monarchinitiative.phenofx.ontology.tree.selectable.SelectableOntologyTreeItem;
import org.monarchinitiative.phenofx.ontology.tree.selectable.SelectableOntologyTreeTerm;
import org.monarchinitiative.phenol.ontology.data.Ontology;
import org.monarchinitiative.phenol.ontology.data.Term;

public class SelectableOntologyTree extends BaseOntologyTree<SelectableOntologyTreeTerm> {

    public SelectableOntologyTree() {
        super();
        getStyleClass().add("selectable-ontology-tree");
        getStylesheets().add(SelectableOntologyTree.class.getResource("SelectableOntologyTree.css").toExternalForm());
    }

    @Override
    TreeItem<SelectableOntologyTreeTerm> getRoot(Ontology ontology) {
        Term root = ontology.getTermMap().get(ontology.getRootTermId());
        return SelectableOntologyTreeItem.of(ontology, root);
    }

    @Override
    Callback<TreeView<SelectableOntologyTreeTerm>, TreeCell<SelectableOntologyTreeTerm>> getCellFactory() {
        return tv -> SelectableOntologyTreeCell.of();
    }


    public ObservableList<SelectableOntologyTerm> getSelectedTerms() {
        // TODO: 10/27/21 implement such that the elements with non-NA status are in the list.
        return FXCollections.emptyObservableList();
    }

}
