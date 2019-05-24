package Model;

import java.util.ArrayList;
import java.util.Random;

public class HardExam extends Exam {

    public HardExam(Lesson lesson, String examWatcher, String examName, String category, String examLocation) {
        super(lesson, examWatcher, examName, category, examLocation);
    }


    @Override
    public Exam makeExam(int classic, int multipleChoice, int truefalse) {
        ArrayList<Question> questions = new ArrayList<Question>();
        ArrayList<Question> fullQuestions = Model.Get.Get();
        ArrayList<Question> exam_questions = new ArrayList<Question>();


        //Works okey think about it doesnt matter write any code you wanted

        for(Question q:fullQuestions)
            if(q.getLesson().getLesson().equals(getLesson().getLesson()))
                questions.add(q);
        
        int score = 0; //take user input for score
        //5-3-2 min(10 questions)

        Random rand = new Random();
        int cls = classic/10;
        int mc = multipleChoice/10;
        int tf = truefalse/10;
        int random;
        //random hard easy normal for every situation if counter goes full dont load if not
        //load
        int easy = 2,normal = 3,hard = 5;
        //5 hard , 3 normal ,2 easy
        //if(questions.size()<easy+hard+normal) System.exit(0);


        int counter = 2000;
        while(counter-->0){

            if(hard==0 && easy==0 && normal==0) break;
            random = rand.nextInt(questions.size());
            System.out.println("Level : "+questions.get(random).getLevel() + "///Model : "+questions.get(random).getClass().getName());
            if(questions.get(random).getLevel().equals(Level.EASY) && easy>0){
                if(questions.get(random).getClass().getName().equals("Model.Classic") && cls>0) --cls;
                else if(questions.get(random).getClass().getName().equals("Model.TrueFalse") && tf>0) --tf;
                else if(questions.get(random).getClass().getName().equals("Model.MultipleAnswer") && mc>0) --mc;
                else continue;
                --easy;
                exam_questions.add(questions.get(random));
                questions.remove(random);
            }
            else if(questions.get(random).getLevel().equals(Level.NORMAL) && normal>0) {
                if(questions.get(random).getClass().getName().equals("Model.Classic") && cls>0) --cls;
                else if(questions.get(random).getClass().getName().equals("Model.TrueFalse") && tf>0) --tf;
                else if(questions.get(random).getClass().getName().equals("Model.MultipleAnswer") && mc>0) --mc;
                else continue;
                --normal;
                exam_questions.add(questions.get(random));
                questions.remove(random);
            }
            else if(questions.get(random).getLevel().equals(Level.HARD) && hard>0){
                if(questions.get(random).getClass().getName().equals("Model.Classic") && cls>0) --cls;
                else if(questions.get(random).getClass().getName().equals("Model.TrueFalse") && tf>0) --tf;
                else if(questions.get(random).getClass().getName().equals("Model.MultipleAnswer") && mc>0) --mc;
                else continue;
                --hard;
                exam_questions.add(questions.get(random));
                questions.remove(random);

            }
        }

        HardExam hardExam = new HardExam(getLesson(),getExamWatcher(),getExamName(),getCategory(),getExamLocation());
        hardExam.setQuestions(exam_questions);
        return hardExam;
    }
}
