

package controllers.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Timer;
import models.GameModel;

/**
 *
 * @author Wouter Pinnoo
 */
public class EndOfGameAction extends AbstractAction{

    private GameModel gamemodel;
    
    public EndOfGameAction(GameModel gamemodel){
        this.gamemodel = gamemodel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        endOfGame();
    }
    
    public void endOfGame(){
        System.out.println("Spel afgelopen!");
        gamemodel.reset();
    }

}
