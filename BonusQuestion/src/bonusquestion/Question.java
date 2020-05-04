package bonusquestion;

/**
 *
 * @author Ali Zoubeidi
 */
public class Question {
    
   private String question;
    
    public Question() {
        question = "Unkown";
    }
    
    public Question(String question) {
        this.question = question;
    }
    
    public String getQuestion() {
        return question;
    }
    
    /**
     * 
     * @return 
     */
   @Override
    public String toString() {
        return "QUESTION: " + question;
    }
    
    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Question)) {
            return false;
        } else {
            Question objQuestion = (Question) o;
            if (question.equalsIgnoreCase(objQuestion.question)) {
                return true;
            } else {
                return false;
            }
        }
    }
}
