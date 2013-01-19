

package models;

/**
 *
 * @author Wouter Pinnoo
 */
public class FeedbackModel extends Model{

    private int size;
    
    public FeedbackModel(){
        size = 5;
    }
    
    public int getSize(){
        return size;
    }
}
