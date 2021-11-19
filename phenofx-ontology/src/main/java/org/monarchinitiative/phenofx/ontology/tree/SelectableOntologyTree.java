package org.monarchinitiative.phenofx.ontology.tree;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import org.monarchinitiative.phenofx.ontology.model.PhenotypeDescription;
import org.monarchinitiative.phenofx.ontology.model.SelectableOntologyTerm;
import org.monarchinitiative.phenol.ontology.algo.OntologyAlgorithm;
import org.monarchinitiative.phenol.ontology.data.Ontology;
import org.monarchinitiative.phenol.ontology.data.Term;
import org.monarchinitiative.phenol.ontology.data.TermId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class SelectableOntologyTree extends VBox {

    private static final Logger LOGGER = LoggerFactory.getLogger(SelectableOntologyTree.class);
    private final ObjectProperty<Ontology> ontology = new SimpleObjectProperty<>(this, "ontology");
    private final ObjectProperty<PhenotypeDescription> phenotypeDescriptionInFocus = new SimpleObjectProperty<>(this, "phenotypeDescriptionInFocus");

    private ChangeListener<PhenotypeDescription> termIdChangeListener = null;

    @FXML
    private TreeView<SelectableOntologyTreeTerm> ontologyTreeView;

    public SelectableOntologyTree() {
        getStylesheets().add(SelectableOntologyTree.class.getResource("SelectableOntologyTree.css").toExternalForm());

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try (InputStream is = SelectableOntologyTree.class.getResourceAsStream("SelectableOntologyTree.fxml")) {
            fxmlLoader.load(is);
        } catch (IOException exception) {
            // TODO - improve error handling
            throw new RuntimeException(exception);
        }
    }

    private static TreeItem<SelectableOntologyTreeTerm> simpleRoot(Ontology ontology) {
        Term root = ontology.getTermMap().get(ontology.getRootTermId());
        return SelectableOntologyTreeItem.of(ontology, root);
    }

    public ObjectProperty<Ontology> ontologyProperty() {
        return ontology;
    }

    public ObjectProperty<PhenotypeDescription> phenotypeDescriptionInFocusProperty() {
        return phenotypeDescriptionInFocus;
    }


    public ObservableList<SelectableOntologyTerm> getSelectedTerms() {
        // TODO: 10/27/21 implement such that the elements with non-NA status are in the list.
        return FXCollections.emptyObservableList();
    }

    @FXML
    private void initialize() {
        ontologyTreeView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        ontologyTreeView.prefWidthProperty().bind(widthProperty());
        ontologyTreeView.prefHeightProperty().bind(heightProperty());
        termIdChangeListener = getTermIdChangeListener();
        ontology.addListener((obs, old, novel) -> {
            if (novel == null) {
                clearOntologyTree();
            } else {
                initializeOntology(novel);
            }
        });
    }

    private void clearOntologyTree() {
        setDisable(true);
        ontologyTreeView.setRoot(null);
        ontologyTreeView.setCellFactory(null);
        phenotypeDescriptionInFocus.removeListener(termIdChangeListener);
    }

    private void initializeOntology(Ontology ontology) {
        setDisable(false);
        TreeItem<SelectableOntologyTreeTerm> root = simpleRoot(ontology);
        ontologyTreeView.setRoot(root);
        ontologyTreeView.setCellFactory(tv -> SelectableOntologyTreeCell.of());

        // always focus on whatever TermId is in `selectedTermId`
        phenotypeDescriptionInFocus.addListener(termIdChangeListener);
    }

    private ChangeListener<PhenotypeDescription> getTermIdChangeListener() {
        return (obs, old, novel) -> {
            if (novel != null)
                navigateToTermId(novel.getTermId());
        };
    }

    private void navigateToTermId(TermId termId) {
        Ontology ontology = this.ontology.get();
        if (ontology == null)
            // shouldn't happen but let's be 100% sure
            return;
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
            List<TreeItem<SelectableOntologyTreeTerm>> children = ontologyTreeView.getRoot().getChildren();
            stack.pop(); // get rid of 'All' node which is hidden
            TreeItem<SelectableOntologyTreeTerm> target = ontologyTreeView.getRoot();
            while (!stack.empty()) {
                TermId current = stack.pop();
                for (TreeItem<SelectableOntologyTreeTerm> child : children) {
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
