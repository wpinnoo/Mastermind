package view.panels;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import models.GameModel;
import models.PieceModel;

/**
 *
 * @author Wouter Pinnoo
 */
public class GamePanel extends JPanel {

    private GameModel gamemodel;
    private PieceModel piecemodel;

    public GamePanel(GameModel gamemodel) {
        this.gamemodel = gamemodel;
        this.piecemodel = gamemodel.getPieceModel();
        setMinimumSize(new Dimension(((gamemodel.getTrialSize() * piecemodel.getSize()) + ((gamemodel.getTrialSize() - 1) * piecemodel.getMarge())), (((gamemodel.getNumberOfTrials() + 1) * piecemodel.getSize()) + ((gamemodel.getNumberOfTrials() - 1) * piecemodel.getMarge()))));

        Listener changelistener = new Listener();
        gamemodel.addChangeListener(changelistener);
        piecemodel.addChangeListener(changelistener);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < gamemodel.getInputs().length; i++) {
            for (int j = 0; j < gamemodel.getInputs()[i].length; j++) {
                g.setColor(piecemodel.getColor(gamemodel.getInputs()[i][j].getType()));
                gamemodel.getInputs()[i][j].paint(g.create(),
                        j * (piecemodel.getSize() + piecemodel.getMarge()),
                        ((gamemodel.getNumberOfTrials() * piecemodel.getSize()) + ((gamemodel.getNumberOfTrials() - 1) * piecemodel.getMarge())) - (i * (piecemodel.getSize() + piecemodel.getMarge())));
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
