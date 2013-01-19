

package feedback;

import java.awt.Color;
import java.awt.Graphics;
import models.FeedbackModel;

/**
 *
 * @author Wouter Pinnoo
 */
public class RedFeedback implements Feedback {
    
    private FeedbackModel feedbackmodel;
    
    public RedFeedback(FeedbackModel feedbackmodel){
        this.feedbackmodel = feedbackmodel;
    }

    @Override
    public void paint(Graphics g, int x, int y) {
        g.setColor(Color.RED);
        g.fillOval(x, y, feedbackmodel.getSize(), feedbackmodel.getSize());
    }

}
