package Model;

public class ExamCreator {

    private MakeExam makeExam;

    public ExamCreator(MakeExam makeExam){
        this.makeExam = makeExam;
    }

    public Exam makeExam(int classic,int multipleChoice,int truefalse ){
        return makeExam.makeExam(classic, multipleChoice, truefalse);}
}
