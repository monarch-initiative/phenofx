package org.monarchinitiative.phenofx.ontology.tree;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.monarchinitiative.phenofx.ontology.model.OntologyTerm;
import org.monarchinitiative.phenofx.ontology.tree.base.OntologyTreeTermBase;
import org.monarchinitiative.phenol.ontology.algo.OntologyAlgorithm;
import org.monarchinitiative.phenol.ontology.data.Ontology;
import org.monarchinitiative.phenol.ontology.data.TermId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;
import java.util.Stack;

abstract class BaseOntologyTree<T extends OntologyTreeTermBase> extends VBox {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseOntologyTree.class);

    private final ObjectProperty<Ontology> ontology = new SimpleObjectProperty<>(this, "ontology");
    private final ObjectProperty<OntologyTerm> ontologyTermInFocus = new SimpleObjectProperty<>(this, "ontologyTermInFocus");
    private final ChangeListener<OntologyTerm> ontologyTermChangeListener = getOntologyTermChangeListener();

    private final TreeView<T> ontologyTreeView = new TreeView<>();

    protected BaseOntologyTree() {
        super();
        getChildren().add(ontologyTreeView);
        getStylesheets().add(BaseOntologyTree.class.getResource("OntologyTree.css").toExternalForm());

        ontologyTreeView.setShowRoot(false);
        ontologyTreeView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        ontologyTreeView.prefWidthProperty().bind(widthProperty());
        ontologyTreeView.prefHeightProperty().bind(heightProperty());
        ontologyTreeView.minWidthProperty().bind(minWidthProperty());
        ontologyTreeView.minHeightProperty().bind(minHeightProperty());

        ontology.addListener(getOntologyChangeListener());
    }

    abstract TreeItem<T> getRoot(Ontology ontology);

    abstract Callback<TreeView<T>, TreeCell<T>> getCellFactory();

    public ObjectProperty<Ontology> ontologyProperty() {
        return ontology;
    }

    public ObjectProperty<OntologyTerm> ontologyTermInFocusProperty() {
        return ontologyTermInFocus;
    }

    private ChangeListener<Ontology> getOntologyChangeListener() {
        return (obs, old, novel) -> {
            if (novel != null) initializeOntology(novel);
            else clearOntologyTree();
        };
    }

    private void clearOntologyTree() {
        setDisable(true);
        ontologyTreeView.setRoot(null);
        ontologyTreeView.setCellFactory(null);
        ontologyTermInFocus.removeListener(ontologyTermChangeListener);
    }

    private void initializeOntology(Ontology ontology) {
        setDisable(false);
        ontologyTreeView.setRoot(getRoot(ontology));
        ontologyTreeView.setCellFactory(getCellFactory());
        ontologyTermInFocus.addListener(ontologyTermChangeListener);
    }


    private ChangeListener<OntologyTerm> getOntologyTermChangeListener() {
        return (obs, old, novel) -> {
            if (novel != null)
                navigateToTermId(novel.term().getId());
        };
    }


    private void navigateToTermId(TermId termId) {
        Ontology ontology = this.ontology.get();
        if (ontology == null) {
            // shouldn't happen but let's be 100% sure
            LOGGER.warn("Ontology was null");
            return;
        }
        if (OntologyAlgorithm.existsPath(ontology, termId, ontology.getRootTermId())) {
            // find root -> term path through the tree
            Stack<TermId> stack = new Stack<>();
            stack.add(termId);
            Set<TermId> parents = ontology.getParentTermIds(termId); //getTermParents(term);
            while (parents.size() != 0) {
                TermId parent = parents.iterator().next();
                stack.add(ontology.getTermMap().get(parent).getId());
                parents = ontology.getParentTermIds(parent);
            }

            // expand tree nodes in top -> down direction
            List<TreeItem<T>> children = ontologyTreeView.getRoot().getChildren();
            stack.pop(); // get rid of 'All' node which is hidden
            TreeItem<T> target = ontologyTreeView.getRoot();
            while (!stack.empty()) {
                TermId current = stack.pop();
                for (TreeItem<T> child : children) {
                    if (child.getValue().term().getId().equals(current)) {
                        child.setExpanded(true);
                        target = child;
                        children = child.getChildren();
                        break;
                    }
                }
            }
            ontologyTreeView.requestFocus();
            ontologyTreeView.getSelectionModel().select(target);
            ontologyTreeView.scrollTo(ontologyTreeView.getSelectionModel().getSelectedIndex() - 5);
        } else {
            LOGGER.warn("Unable to find the path from {} to {}", ontology.getRootTermId(), termId);
        }
    }
}
