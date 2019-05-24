package Model;

public class MultipleAnswer extends Question {


    private String a,b,c,d;
    private final static String TABLE = "MULTIPLECHOICE";

    public MultipleAnswer(String level,Lesson lesson, int score, String question,Object answer,String a,String b,String c,String d) {
        super(level,lesson, score, question,answer);
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public void addQuestion() {
        Model.Add.Add(TABLE,getLesson().getLesson(),getQuestion(),this.a,this.b,this.c,this.d,getAnswer(),getLevel(),getScore());
    }

    @Override
    public void updateQuestion(Question q,String ...params) {
        Model.Update.Update(TABLE,q.getLesson().getLesson(),q.getQuestion(),params[0],params[1],params[2],params[3],q.getAnswer(),q.getLevel(),q.getScore(),getQuestion());
    }

    @Override
    public void deleteQuestion() {
       Model.Delete.Delete(TABLE,getQuestion(),getAnswer(),getLevel());

    }
    @Override
    public String toString(){
        return "<html><b>Question : " +getQuestion() +"<b>"+"<br>A )"+this.a +"<br> B ) "+this.b +"<br> C ) "+this.c+"<br> D ) "+this.d +"<b></html>";
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public String getC() {
        return c;
    }

    public String getD() {
        return d;
    }
}
