package Presenter;

import Model.Lesson;
import Model.Model;
import Model.TrueFalse;
import View.ITrueFalse;

import javax.swing.*;
import java.util.ArrayList;

public class TrueFalsePresenter {
    private ITrueFalse view;

    public TrueFalsePresenter(ITrueFalse view){
        this.view = view;
        setComboBoxLessons();
    }

    private void setComboBoxLessons(){
        ArrayList<Lesson> lesson = Model.Get.Get(true);
        ArrayList<String> les = new ArrayList<>();
        for(Lesson s:lesson)
            les.add(s.getLesson());
        view.setLessonComboBox().setModel(new DefaultComboBoxModel(les.toArray()));
    }

    public void btnSaveTrueFalseQuestion(){
        
        if(view.getTxtAddLesson().isEmpty()) new TrueFalse(view.getLevelSelectedItem(),
                new Lesson(view.getLessonSelectedItem()),Integer.valueOf(view.getTxtQuestionScore()),
                view.getTxtQuestion(),answer()).addQuestion();
        else {
            new Lesson(view.getTxtAddLesson()).addLesson();
            new TrueFalse(view.getLevelSelectedItem(),new Lesson(view.getTxtAddLesson()),
                Integer.valueOf(view.getTxtQuestionScore()),view.getTxtQuestion(),answer()).addQuestion();
        }
            }

    private boolean answer(){
        if(view.getRadioButtonIsTrue()) return true;
        else return false;
    }
}