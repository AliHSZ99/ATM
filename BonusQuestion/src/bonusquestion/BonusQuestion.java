package bonusquestion;

/**
 *
 * @author Ali Zoubeidi
 */
public class BonusQuestion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Question q = new Question("Are you a question?");
        
        q.getQuestion();
        
        System.out.println(q);
    }
    
}
