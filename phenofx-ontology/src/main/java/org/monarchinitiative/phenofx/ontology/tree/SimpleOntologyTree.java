package org.monarchinitiative.phenofx.ontology.tree;

import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.util.Callback;
import org.monarchinitiative.phenofx.ontology.tree.simple.SimpleOntologyTreeCell;
import org.monarchinitiative.phenofx.ontology.tree.simple.SimpleOntologyTreeItem;
import org.monarchinitiative.phenofx.ontology.tree.simple.SimpleOntologyTreeTerm;
import org.monarchinitiative.phenol.ontology.data.Ontology;
import org.monarchinitiative.phenol.ontology.data.Term;

public class SimpleOntologyTree extends BaseOntologyTree<SimpleOntologyTreeTerm> {

    public SimpleOntologyTree() {
        super();
        getStyleClass().add("simple-ontology-tree");
        getStylesheets().add(SimpleOntologyTree.class.getResource("SimpleOntologyTree.css").toExternalForm());
    }

    @Override
    TreeItem<SimpleOntologyTreeTerm> getRoot(Ontology ontology) {
        Term root = ontology.getTermMap().get(ontology.getRootTermId());
        return SimpleOntologyTreeItem.of(ontology, root);
    }

    @Override
    Callback<TreeView<SimpleOntologyTreeTerm>, TreeCell<SimpleOntologyTreeTerm>> ontologyTreeCellFactory() {
        return tv -> SimpleOntologyTreeCell.of();
    }


}
