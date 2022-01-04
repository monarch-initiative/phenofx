package org.monarchinitiative.phenofx.questionnnaire.phenoitem;

import org.monarchinitiative.phenol.ontology.data.Term;

import java.util.Optional;

public class DefaultPhenoAnswer implements PhenoAnswer {

    private final Term term;
    private final AnswerType answerType;
    private final PhenoAge phenoAge;
    private final String question;

    public DefaultPhenoAnswer(PhenoItem item) {
        this.term = item.term();
        this.answerType = item.answer();
        this.phenoAge = item.age().isPresent() ? item.age().get() : null;
        this.question = item.question();
    }


    @Override
    public Term term() {
        return this.term;
    }

    @Override
    public boolean excluded() {
        return this.answerType.equals(AnswerType.EXCLUDED);
    }

    @Override
    public boolean observed() {
        return this.answerType.equals(AnswerType.OBSERVED);
    }

    @Override
    public boolean unknown() {
        return this.answerType.equals(AnswerType.UNKNOWN);
    }

    @Override
    public String question() {
        return question;
    }

    @Override
    public Optional<PhenoAge> ageOptional() {
        return Optional.ofNullable(this.phenoAge);
    }


}
