package models;

import controllers.actions.EndOfGameAction;
import feedback.Feedback;
import feedback.FeedbackMaker;
import feedback.NullFeedback;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Timer;
import pieces.Piece;

/**
 *
 * @author Wouter Pinnoo
 */
public class GameModel extends Model {

    private Piece[][] inputs;
    private Piece[] currentInput;
    private Piece[] solution;
    private Feedback[][] feedback;
    private final int TRIALS = 10;
    private final int TRIAL_SIZE = 4;
    private PieceModel piecemodel;
    private int attempt;
    private FeedbackMaker feedbackmaker;
    private FeedbackModel feedbackmodel;

    public GameModel(PieceModel piecemodel) {
        this.piecemodel = piecemodel;
        reset();
    }

    public void changePieceType(Piece piece) {
        if (piece != null) {
            piece.changeType();
        }
        fireStateChanged();
    }

    public void activateCurrentInput() {
        for (int i = 0; i < currentInput.length; i++) {
            inputs[attempt][i] = currentInput[i].clone();
            currentInput[i].resetType();
        }
        if (checkForCorrectsolution(inputs[attempt])) {
            System.out.println("GEWONNEN!");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
            }
            new EndOfGameAction(this).endOfGame();
        } else {
            feedback[attempt] = feedbackmaker.giveFeedback(inputs[attempt]);
            attempt++;
        }
        if (attempt == TRIALS) {
            new EndOfGameAction(this).endOfGame();
        }
        fireStateChanged();
    }

    public Piece[][] getInputs() {
        return inputs;
    }

    public Piece[] getCurrentInput() {
        return currentInput;
    }

    public Feedback[][] getFeedback() {
        return feedback;
    }

    public PieceModel getPieceModel() {
        return piecemodel;
    }

    public int getNumberOfTrials() {
        return TRIALS;
    }

    public int getTrialSize() {
        return TRIAL_SIZE;
    }

    protected boolean solutionContainsType(int type) {
        boolean containsNot = true;
        for (int i = 0; i < solution.length; i++) {
            boolean temp = true;
            try {
                temp = (solution[i].getType() != type);
            } catch (NullPointerException e) {
            } finally {
                containsNot &= temp;
            }
        }
        return !containsNot;
    }

    private void makeSolution() {
        solution = new Piece[TRIAL_SIZE];
        System.out.print("Solution: ");
        for (int i = 0; i < solution.length; i++) {
            int randomnr = new Random().nextInt(piecemodel.getPossibleTypes());
            while (randomnr == 0 || solutionContainsType(randomnr)) {
                randomnr = new Random().nextInt(piecemodel.getPossibleTypes());
            }
            solution[i] = new Piece(piecemodel, randomnr);
            System.out.print(solution[i].getType() + " ");
        }
        System.out.println();
    }

    private boolean checkForCorrectsolution(Piece[] pieces) {
        boolean success = true;
        for (int i = 0; i < solution.length; i++) {
            success &= (solution[i].equals(pieces[i]));
        }
        return success;
    }

    public void reset() {
        attempt = 0;
        feedback = new Feedback[TRIALS][TRIAL_SIZE];
        feedbackmodel = new FeedbackModel();
        for (int i = 0; i < feedback.length; i++) {
            for (int j = 0; j < feedback[i].length; j++) {
                feedback[i][j] = new NullFeedback();
            }
        }
        inputs = new Piece[TRIALS][TRIAL_SIZE];
        for (int i = 0; i < inputs.length; i++) {
            for (int j = 0; j < inputs[i].length; j++) {
                inputs[i][j] = new Piece(piecemodel);
            }
        }
        currentInput = new Piece[TRIAL_SIZE];
        for (int i = 0; i < currentInput.length; i++) {
            currentInput[i] = new Piece(piecemodel, 1);
        }
        makeSolution();
        feedbackmaker = new FeedbackMaker(feedbackmodel, solution);
        fireStateChanged();
    }
}
