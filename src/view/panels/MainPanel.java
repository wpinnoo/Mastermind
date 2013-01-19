

package view.panels;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import models.GameModel;
import models.PieceModel;

/**
 *
 * @author Wouter Pinnoo
 */
public class MainPanel extends JPanel{

    public MainPanel(){
        PieceModel piecemodel = new PieceModel();
        GameModel gamemodel = new GameModel(piecemodel);
        InputPanel inputpanel = new InputPanel(gamemodel);
        GamePanel gamepanel = new GamePanel(gamemodel);
        FeedbackPanel feedbackpanel = new FeedbackPanel(gamemodel);
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                    .addComponent(gamepanel)
                    .addComponent(inputpanel))
                .addGroup(layout.createParallelGroup()
                    .addComponent(feedbackpanel)
                    .addComponent(inputpanel)));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                    .addComponent(gamepanel)
                    .addComponent(feedbackpanel))
                .addComponent(inputpanel));
    }
    
}
