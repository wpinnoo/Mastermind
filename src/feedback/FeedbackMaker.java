package feedback;

import java.util.ArrayList;
import java.util.List;
import models.FeedbackModel;
import pieces.Piece;

/**
 *
 * @author Wouter Pinnoo
 */
public class FeedbackMaker {

    private Piece[] solution;
    private FeedbackModel feedbackmodel;

    public FeedbackMaker(FeedbackModel feedbackmodel, Piece[] solution) {
        this.solution = solution;
        this.feedbackmodel = feedbackmodel;
    }

    public Feedback[] giveFeedback(Piece[] input) {
        Object[] resultRed = giveRedFeedback(input);
        List<RedFeedback> redfeedback = (List<RedFeedback>) resultRed[0];
        List<Integer> tokenpieces = (List<Integer>) resultRed[1];
        List<WhiteFeedback> whitefeedback = giveWhiteFeedback(input, tokenpieces);
        Feedback[] result = new Feedback[input.length];
        for (int i = 0; i < redfeedback.size(); i++) {
            result[i] = redfeedback.get(i);
        }
        for (int i = 0; i < whitefeedback.size(); i++) {
            result[i + redfeedback.size()] = whitefeedback.get(i);
        }
        if(whitefeedback.size() + redfeedback.size() < input.length){
            for(int i=(whitefeedback.size() + redfeedback.size()); i < input.length; i++){
                result[i] = new NullFeedback();
            }
        }
        return result;
    }

    private Object[] giveRedFeedback(Piece[] input) {
        if (input.length != solution.length) {
            return null;
        }
        List<RedFeedback> feedbacklist = new ArrayList<>();
        List<Integer> tokenpieces = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            if (input[i].equals(solution[i])) {
                feedbacklist.add(new RedFeedback(feedbackmodel));
                tokenpieces.add(i);
            }
        }
        return new Object[]{feedbacklist, tokenpieces};
    }

    private List<WhiteFeedback> giveWhiteFeedback(Piece[] input, List<Integer> tokenpieces) {
        if (input.length != solution.length) {
            return null;
        }
        List<WhiteFeedback> feedbacklist = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < solution.length; j++) {
                if (i != j && input[i].equals(solution[j]) && !tokenpieces.contains(j)) {
                    feedbacklist.add(new WhiteFeedback(feedbackmodel));
                    tokenpieces.add(j);
                }
            }
        }
        return feedbacklist;
    }
}
