package org.monarchinitiative.phenofx.ontology.tree.simple;

import javafx.scene.control.TreeCell;

public class SimpleOntologyTreeCell extends TreeCell<SimpleOntologyTreeTerm> {

    public static SimpleOntologyTreeCell of() {
        return new SimpleOntologyTreeCell();
    }

    private SimpleOntologyTreeCell() {}

    @Override
    protected void updateItem(SimpleOntologyTreeTerm item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
        } else {
            setText(getItem().term().getName());
        }
    }
}
