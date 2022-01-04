package org.monarchinitiative.phenofx.questionnnaire;

import org.monarchinitiative.phenofx.questionnnaire.io.QuestionParser;
import org.monarchinitiative.phenofx.questionnnaire.phenoitem.PhenoItem;
import org.monarchinitiative.phenofx.questionnnaire.qtable.PhenoqTable;
import org.monarchinitiative.phenol.base.PhenolRuntimeException;
import org.monarchinitiative.phenol.ontology.data.Ontology;

import java.io.File;
import java.net.URL;
import java.util.List;

public class Phenoquestionnaire {

    private final List<PhenoItem> questions;

    private Phenoquestionnaire(List<PhenoItem> phenoQuestions) {
        this.questions = phenoQuestions;
    }

    public List<PhenoItem> getQuestions() {
        return questions;
    }

    public static Phenoquestionnaire development(Ontology ontology) {
        String questionFilePath = "/questions/development.txt";
        URL url = Phenoquestionnaire.class.getResource(questionFilePath);
        if (url == null) {
            throw new PhenolRuntimeException("Could not find development questionnaire file" + questionFilePath);
        }
        File f = new File(url.getFile());
        if (! f.isFile()) {
            throw new PhenolRuntimeException("Could not find file " + f.getAbsolutePath());
        }
        QuestionParser parser = new QuestionParser(f, ontology);
        List<PhenoItem> ques =  parser.getPhenoItemList();
        return new Phenoquestionnaire(ques);
    }


}
