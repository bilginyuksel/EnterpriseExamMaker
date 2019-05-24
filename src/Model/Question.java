package Model;

public abstract class Question {
    private Lesson lesson;
    private int score;
    private String question,level;
    private Object answer;


    public Question(String level,Lesson lesson,int score,String question,Object answer){
        this.lesson = lesson;
        this.score = score;
        this.question = question;
        this.answer = answer;
        this.level = level;
    }


    public abstract void addQuestion();
    public abstract void updateQuestion(Question q,String ...params);
    public abstract void deleteQuestion();

    public String getLevel() {
        return level;
    }

    public Object getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public int getScore() {
        return score;
    }
    
    public String toString(){
        return "<html><b>Question : " +this.question +"<b></html>";
    }
}
