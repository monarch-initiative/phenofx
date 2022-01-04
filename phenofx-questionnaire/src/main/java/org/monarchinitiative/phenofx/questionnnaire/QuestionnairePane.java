package org.monarchinitiative.phenofx.questionnnaire;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.monarchinitiative.phenofx.questionnnaire.phenoitem.AgeThresholdPhenoItem;
import org.monarchinitiative.phenofx.questionnnaire.phenoitem.PhenoAnswer;
import org.monarchinitiative.phenofx.questionnnaire.phenoitem.PhenoItem;
import org.monarchinitiative.phenofx.questionnnaire.qtable.PhenoqTable;
import org.monarchinitiative.phenofx.questionnnaire.qtable.Qphenorow;
import org.monarchinitiative.phenol.ontology.data.Ontology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionnairePane extends BorderPane {
Logger LOGGER = LoggerFactory.getLogger(QuestionnairePane.class);
    private PhenoqTable phenoqTable;
    private VBox root = new VBox();
    Button cancelButton = new Button("Cancel");
    Button acceptButton = new Button("Done");

    public QuestionnairePane() {
        super();
        URL cssResource = QuestionnairePane.class.getResource("/QuestionnairePane.css");
        if (cssResource != null) {
            getStylesheets().add(cssResource.toExternalForm());
            getStyleClass().add("pane");
        } else {
            LOGGER.error("Could not load CSS for QuestionnairePane");
        }
//        List<AgeThresholdPhenoItem> agePhenoItems = developmentQuestionnare.getAgeThresholdPhenoItemList();
//        phenoRows = agePhenoItems.stream().map(Qphenorow::new).collect(Collectors.toList());
        phenoqTable = new PhenoqTable(List.of()); // initialize here with empty list
        root.getChildren().add(phenoqTable);
        HBox buttonBox = new HBox();
        buttonBox.setMinWidth(1000);
        buttonBox.setMaxHeight(20);


        buttonBox.getChildren().add(cancelButton);
        buttonBox.getChildren().add(acceptButton);
        root.getChildren().add(buttonBox);
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        Label titleLabel = new Label("HPO-based Questionnaire");
        setTop(titleLabel);

//        Scene scene = new Scene(root);

//        stage.show();
    }

    public void setQuestionnaire(Ontology ontology, List<PhenoItem> phenoQuestions) {
        List<Qphenorow> phenoRows = phenoQuestions.stream().map(Qphenorow::new).collect(Collectors.toList());
        this.phenoqTable = new PhenoqTable(phenoRows);
        root.getChildren().add(this.phenoqTable);
        HBox buttonBox = new HBox();
        buttonBox.setMinWidth(1000);
        buttonBox.setMaxHeight(20);
        Button cancelButton = new Button("Cancel");
        Button acceptButton = new Button("Done");
        cancelButton.setOnAction((e) -> {
            phenoRows.forEach(Qphenorow::reset);
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
        acceptButton.setOnAction((e) -> {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
        buttonBox.getChildren().add(cancelButton);
        buttonBox.getChildren().add(acceptButton);
        root.getChildren().add(buttonBox);
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        setCenter(root);

//        Stage stage = new Stage();
//        stage.setWidth(1000);
//        stage.setHeight(700);
//        stage.setScene(scene);
//        stage.setTitle("HPO-Based Phenotype Questionnaire");
//        stage.showAndWait();
//        ListView<String> listView = new ListView<>();
//        for (var pitem : phenoRows) {
//            listView.getItems().add(pitem.toPhenoItem().toString());
//        }
//        listView.setMinWidth(990);
//        listView.setMinHeight(650);
//        HBox hbox = new HBox(listView);
////        Scene scene2 = new Scene(hbox, 1000, 700);
//        stage.setScene(scene2);
//        stage.setTitle("Answers");
//        stage.show();


    }



}
