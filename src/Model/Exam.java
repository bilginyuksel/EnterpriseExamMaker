package Model;

import java.util.ArrayList;

public abstract class Exam implements MakeExam{

    private Lesson lesson;
    private ArrayList<Question> questions;
    private String examName,category,examLocation,examWatcher;

    public Exam(Lesson lesson,String examWatcher,String examName,String category,String examLocation){
        this.lesson = lesson;
        this.examWatcher = examWatcher;
        this.examName = examName;
        this.category = category;
        this.examLocation = examLocation;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public String getExamName() {
        return examName;
    }

    public String getExamLocation() {
        return examLocation;
    }

    public String getCategory() {
        return category;
    }

    public String getExamWatcher() {
        return examWatcher;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
}
