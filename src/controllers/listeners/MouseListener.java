package controllers.listeners;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;
import models.GameModel;
import view.panels.InputPanel;

/**
 *
 * @author Wouter Pinnoo
 */
public class MouseListener extends MouseInputAdapter {

    private InputPanel inputpanel;
    private GameModel gamemodel;

    public MouseListener(InputPanel inputpanel, GameModel gamemodel) {
        this.inputpanel = inputpanel;
        this.gamemodel = gamemodel;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (inputpanel.isActivator(e.getX(), e.getY())) {
            gamemodel.activateCurrentInput();
        } else {
            gamemodel.changePieceType(inputpanel.getPiece(e.getX(), e.getY()));
        }
    }
}
