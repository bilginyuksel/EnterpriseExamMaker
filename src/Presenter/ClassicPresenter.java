/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presenter;


import Model.Classic;
import Model.Lesson;
import Model.Model;
import View.IClassic;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Hasancan
 */
public class ClassicPresenter{
    public IClassic view;
    
    private void fillComboBoxLessons(){
        ArrayList<Lesson> lesson = Model.Get.Get(true);
        ArrayList<String> les = new ArrayList<String>();
        for(Lesson s:lesson)
            les.add(s.getLesson());
        view.fillLessons().setModel(new DefaultComboBoxModel(les.toArray()));
    }    
    public ClassicPresenter(IClassic view){
        this.view = view;
        fillComboBoxLessons();        
    }   
    public void saveClasssicQuestion(){
        if(view.lessonAdd().isEmpty()) new Classic(view.levelSelectedItem(),new Lesson(view.lessonSelectedItem()),Integer.valueOf(view.questionScore()),view.getQuestion(),view.getAnswer()).addQuestion();
        else {
            new Lesson(view.lessonAdd()).addLesson();
            new Classic(view.levelSelectedItem(),new Lesson(view.lessonAdd()),Integer.valueOf(view.questionScore()),view.getQuestion(),view.getAnswer()).addQuestion();
        }
    }
    
    
}
