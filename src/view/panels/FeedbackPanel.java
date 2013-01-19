package view.panels;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import models.GameModel;

/**
 *
 * @author Wouter Pinnoo
 */
public class FeedbackPanel extends JPanel {

    private GameModel gamemodel;

    public FeedbackPanel(GameModel gamemodel) {
        this.gamemodel = gamemodel;
        Listener changelistener = new Listener();
        gamemodel.addChangeListener(changelistener);
        setMaximumSize(new Dimension(gamemodel.getPieceModel().getSize() + gamemodel.getPieceModel().getMarge(), 1000));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < gamemodel.getFeedback().length; i++) {
            for (int j = 0; j < gamemodel.getFeedback()[i].length; j++) {
                if (j < 2) {
                    gamemodel.getFeedback()[i][j].paint(g.create(), (j%2)*gamemodel.getPieceModel().getSize()/2, 
                            ((gamemodel.getNumberOfTrials() * gamemodel.getPieceModel().getSize()) + ((gamemodel.getNumberOfTrials() - 1) * gamemodel.getPieceModel().getMarge())) - ((i * (gamemodel.getPieceModel().getSize() + gamemodel.getPieceModel().getMarge()))));
                } else {
                    gamemodel.getFeedback()[i][j].paint(g.create(), (j%2)*gamemodel.getPieceModel().getSize()/2, 
                            ((gamemodel.getNumberOfTrials() * gamemodel.getPieceModel().getSize()) + ((gamemodel.getNumberOfTrials() - 1) * gamemodel.getPieceModel().getMarge())) - ((i * (gamemodel.getPieceModel().getSize() + gamemodel.getPieceModel().getMarge()))-(gamemodel.getPieceModel().getSize()/2)));
               
                }
            }
        }
    }

    private class Listener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            repaint();
        }
    }
}
