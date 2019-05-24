package Model;

public class TrueFalse extends Question {

    private final static String TABLE = "TRUEFALSE";

    public TrueFalse(String level,Lesson lesson, int score, String question,Object answer) {
        super(level,lesson, score, question,answer);
    }

    @Override
    public void addQuestion() {
        Model.Add.Add(TABLE,getLesson().getLesson(),getQuestion(),getAnswer(),getLevel(),getScore());
    }

    @Override
    public void updateQuestion(Question q,String ...params) {
        Model.Update.Update(TABLE,q.getLesson().getLesson(),q.getQuestion(),q.getAnswer(),q.getLevel(),q.getScore(),getQuestion());
    }

    @Override
    public void deleteQuestion() {
        Model.Delete.Delete(TABLE,getQuestion(),getAnswer(),getLevel());
    }

}
