

package view.panels;

import controllers.listeners.MouseListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import models.GameModel;
import models.PieceModel;
import pieces.Piece;

/**
 *
 * @author Wouter Pinnoo
 */
public class InputPanel extends JPanel{

    private GameModel gamemodel;
    private PieceModel piecemodel;
    private final int UPPER_MARGE = 20;
    
    public InputPanel(GameModel gamemodel){
        this.gamemodel = gamemodel;
        this.piecemodel = gamemodel.getPieceModel();
        setMinimumSize(new Dimension(gamemodel.getTrialSize() * (piecemodel.getSize() + piecemodel.getMarge()), piecemodel.getSize()));
        addMouseListener(new MouseListener(this, gamemodel));
        Listener changelistener = new Listener();
        gamemodel.addChangeListener(changelistener); 
        piecemodel.addChangeListener(changelistener);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i=0; i < gamemodel.getCurrentInput().length; i++){
            g.setColor(piecemodel.getColor(gamemodel.getCurrentInput()[i].getType()));
            gamemodel.getCurrentInput()[i].paint(g.create(), i*piecemodel.getSize() + i*piecemodel.getMarge(), UPPER_MARGE);
        }
        g.setColor(Color.RED);
        g.fillRect(getMinimumSize().width, UPPER_MARGE, piecemodel.getSize(), piecemodel.getSize());
        g.setColor(Color.WHITE);
        g.drawString("OK", getMinimumSize().width + piecemodel.getSize() / 4, UPPER_MARGE + piecemodel.getSize() / 2);
    }
    
    public boolean isActivator(int x, int y){
        int beginActivator = gamemodel.getTrialSize() * (piecemodel.getSize() + piecemodel.getMarge());
        if(x > beginActivator && x < beginActivator + piecemodel.getSize()){
            return true;
        } else {
            return false;
        }
    }
    
    public Piece getPiece(int x, int y){
        Piece foundpiece = null;
        if(y < piecemodel.getSize() + piecemodel.getMarge() + UPPER_MARGE && x < gamemodel.getTrialSize() * (piecemodel.getSize() + piecemodel.getMarge())){
            if(x < piecemodel.getSize()){
                foundpiece = gamemodel.getCurrentInput()[0];
            } else {
                foundpiece = gamemodel.getCurrentInput()[x / (piecemodel.getSize() + piecemodel.getMarge())];
            }
        }
        return foundpiece;
    }
    
    private class Listener implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent e) {
            repaint();
        }
        
    }
    
}
