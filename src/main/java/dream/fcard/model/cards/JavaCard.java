package dream.fcard.model.cards;

import java.util.ArrayList;

import dream.fcard.model.TestCase;
import dream.fcard.model.exceptions.IndexNotFoundException;

/**
 * Card that evaluates input as javascript code whose output has to match back of card.
 */
public class JavaCard extends FlashCard {

    private String question;
    private ArrayList<TestCase> testCases;

    public JavaCard(String question, ArrayList<TestCase> testCases) {
        this.question = question;
        this.testCases = testCases;
    }

    @Override
    public Boolean evaluate(String in) throws IndexNotFoundException {
        //TODO: Evaluating Java code can be done inside here
        return null;
    }

    @Override
    public String getFront() {
        return question;
    }

    @Override
    public String getBack() {
        //irrelevant
        return null;
    }

    @Override
    public void editFront(String newText) {
        //irrelevant
    }

    @Override
    public void editBack(String newText) {
        //irrelevant
    }

    @Override
    public boolean hasChoices() {
        return false;
    }
}
