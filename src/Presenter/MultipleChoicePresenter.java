/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presenter;

import Model.Lesson;
import Model.Model;
import Model.MultipleAnswer;
import View.IMultipleChoice;

import javax.swing.*;
import java.util.ArrayList;

public class MultipleChoicePresenter {
    private IMultipleChoice view;

    public MultipleChoicePresenter(IMultipleChoice view){
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

    public void btnSaveMultipleChoiceQuestion(){
        if(view.getTxtAddLesson().isEmpty())
            new MultipleAnswer(view.getLevelSelectedItem(),new Lesson(view.getLessonSelectedItem()),view.getTxtQuestionScore(),view.getTxtQuestion(),view.getTxtAnswer(),view.getTxtChoiceA(),view.getTxtChoiceB(),view.getTxtChoiceC(),view.getTxtChoiceD()).addQuestion();
        else {
            new Lesson(view.getTxtAddLesson()).addLesson();
            new MultipleAnswer(view.getLevelSelectedItem(),new Lesson(view.getTxtAddLesson()),view.getTxtQuestionScore(),view.getTxtQuestion(),view.getTxtAnswer(),view.getTxtChoiceA(),view.getTxtChoiceB(),view.getTxtChoiceC(),view.getTxtChoiceD()).addQuestion();

        }
            
    }

}
