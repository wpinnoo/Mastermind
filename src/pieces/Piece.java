

package pieces;

import java.awt.Graphics;
import models.PieceModel;

/**
 *
 * @author Wouter Pinnoo
 */
public class Piece {

    private int type;
    private PieceModel piecemodel;
    
    public Piece(PieceModel piecemodel, int type){
        this.piecemodel = piecemodel;
        this.type = type;
    }
    
    public Piece(PieceModel piecemodel){
        this(piecemodel, 0);
    }
    
    public void paint(Graphics g, int x, int y){
        g.setColor(piecemodel.getColor(type));
        g.fillOval(x, y, piecemodel.getSize(), piecemodel.getSize());
    }
    
    public boolean isNullPiece(){
        return type == 0;
    }
    
    public PieceModel getModel(){
        return piecemodel;
    }
    
    public void changeType(){
        type = ++type % piecemodel.getPossibleTypes();
        if(isNullPiece()){
            type++;
        }
    }
    
    public void resetType(){
        type = 1;
    }
    
    public int getType(){
        return type;
    }
    
    @Override
    public Piece clone(){
        return new Piece(piecemodel, type);
    }
    
    @Override
    public String toString(){
        return "[Piece] type "+type;
    }
    
    @Override
    public boolean equals(Object o1){
        Piece o2;
        try {
            o2 = (Piece) o1;
        } catch(ClassCastException e){
            return false;
        }
        return getType() == o2.getType();
    }
}
