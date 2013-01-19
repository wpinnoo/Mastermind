package models;

import java.awt.Color;

/**
 *
 * @author Wouter Pinnoo
 */
public class PieceModel extends Model {

    private final Color[] COLORS = new Color[]{Color.WHITE, Color.BLACK, Color.ORANGE, new Color(155, 0, 155), Color.RED, Color.GREEN, Color.BLUE};
        
    private final int MARGE = 10;
    private final int PIECE_SIZE = 30;
    
    public PieceModel() {
    }
    
    public Color getColor(int type){
        return COLORS[type];
    }
    
    public int getPossibleTypes(){
        return COLORS.length;
    }
    
    public int getSize(){
        return PIECE_SIZE;
    }
    
    public int getMarge(){
        return MARGE;
    }
}
