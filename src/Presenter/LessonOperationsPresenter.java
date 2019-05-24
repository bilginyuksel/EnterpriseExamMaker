/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presenter;

import Model.Lecturer;
import Model.Lesson;
import View.ILessonOperations;
import View.LessonOperationsFrame;
import java.util.ArrayList;
import Model.Model;
import Model.Lecturer;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Hasancan
 */
public class LessonOperationsPresenter {
    
    private ILessonOperations view;
    private ArrayList<Lecturer> lecturers;
    
    public LessonOperationsPresenter(ILessonOperations view){
        this.view =view;
        lecturers = new ArrayList<>();
        fillLessons();
        fillLecturers();
    }
    private void fillLessons(){
        ArrayList<Lesson> lesson = Model.Get.Get(true);
        ArrayList<String> les = new ArrayList<String>();
        for(Lesson s:lesson) les.add(s.getLesson());
        view.fillLessons().setModel(new DefaultComboBoxModel(les.toArray()));
        
    }
    private void fillLecturers(){
        lecturers = Model.Get.Get(0);
        ArrayList<String> les = new ArrayList<String>();
        for(Lecturer r:lecturers) les.add(r.getLecturerFullName());
        view.comboBoxLecturer().setModel(new DefaultComboBoxModel(les.toArray()));
    }
    
    public void comboBoxSelectedItem(){
        view.setTxtLesson(view.fillLessons().getSelectedItem().toString());
    }

    public void btnAddLesson(){
        new Lesson(view.getTxtLesson()).addLesson();
        fillLessons();
    }
    public void btnDeleteLesson(){
        new Lesson(view.fillLessons().getSelectedItem().toString()).deleteLesson();
        fillLessons();
    }
    public void btnUpdateLesson(){
        if(view.getTxtLesson().toString().equals(""))
            new Lesson(view.fillLessons().getSelectedItem().toString()).updateLesson(view.fillLessons().getSelectedItem().toString(),Model.Get.findLecturer(lecturers.get(view.comboBoxLecturer().getSelectedIndex()).getIdentificationNumber()));
        else
            new Lesson(view.fillLessons().getSelectedItem().toString()).updateLesson(view.getTxtLesson(),Model.Get.findLecturer(lecturers.get(view.comboBoxLecturer().getSelectedIndex()).getIdentificationNumber()));

        fillLessons();
    }
}
