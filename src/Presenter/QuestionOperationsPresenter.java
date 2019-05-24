/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presenter;

import Model.Classic;
import Model.Lesson;
import Model.Question;
import Model.Model;
import Model.MultipleAnswer;
import Model.TrueFalse;
import View.IQuestionOperations;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

/**
 *
 * @author Hasancan
 */
public class QuestionOperationsPresenter {
    private IQuestionOperations view;
    private ArrayList<Question> questions;
    int selectedIndex = 0;
    
    public QuestionOperationsPresenter(IQuestionOperations view){
        this.view = view;
        questions = new ArrayList<>();
        fillLesson();
        fillQuestions();
        
    }
    
    private void fillLesson(){
        ArrayList<Lesson> lesson = Model.Get.Get(true);
        ArrayList<String> les = new ArrayList<String>();
        for(Lesson s:lesson) les.add(s.getLesson());
        view.setLessonComboBox().setModel(new DefaultComboBoxModel(les.toArray()));
    }
    private void fillQuestions(){
        questions = Model.Get.Get();
     
        DefaultListModel dfl = new DefaultListModel();
        for(int i=0;i<questions.size();i++) dfl.add(i,questions.get(i));
        view.jListQuestions().setModel(dfl);
    }
    public void listViewClicked(){
        selectedIndex = view.jListQuestions().getSelectedIndex();
        if(questions.get(selectedIndex).getClass().getName().equals("Model.MultipleAnswer")){
            MultipleAnswer mpa_question = (MultipleAnswer) questions.get(selectedIndex);
            view.radioButtonFalse().setSelected(false);
            view.radioButtonTrue().setSelected(false);
            view.setTxtQuestion(mpa_question.getQuestion());
            view.setTxtAnswer(mpa_question.getAnswer());
            view.setTxtScore(mpa_question.getScore());
            view.setTxtA(mpa_question.getA());
            view.setTxtB(mpa_question.getB());
            view.setTxtC(mpa_question.getC());
            view.setTxtD(mpa_question.getD());
            view.setLessonComboBox().setSelectedItem(mpa_question.getLesson());
            view.setLevelComboBox().setSelectedItem(mpa_question.getLevel());
        }else if(questions.get(selectedIndex).getClass().getName().equals("Model.TrueFalse")){
            
            if(Integer.valueOf(questions.get(selectedIndex).getAnswer().toString())==1){
                view.radioButtonFalse().setSelected(false);
                view.radioButtonTrue().setSelected(true);
            }
            else if(Integer.valueOf(questions.get(selectedIndex).getAnswer().toString())==0){
                view.radioButtonTrue().setSelected(false);
                view.radioButtonFalse().setSelected(true);
            }            
            view.setTxtAnswer("");
            view.setTxtQuestion(questions.get(selectedIndex).getQuestion());
            view.setTxtScore(questions.get(selectedIndex).getScore());
            view.setTxtA(null);
            view.setTxtB(null);
            view.setTxtC(null);
            view.setTxtD(null);
            view.setLevelComboBox().setSelectedItem(questions.get(selectedIndex).getLevel());
            view.setLessonComboBox().setSelectedItem(questions.get(selectedIndex).getLesson());

        }
        else{
            view.radioButtonFalse().setSelected(false);
            view.radioButtonTrue().setSelected(false);
            view.setTxtQuestion(questions.get(selectedIndex).getQuestion());
            view.setTxtScore(questions.get(selectedIndex).getScore());
            view.setTxtAnswer(questions.get(selectedIndex).getAnswer());
            view.setTxtA(null);
            view.setTxtB(null);
            view.setTxtC(null);
            view.setTxtD(null);
            view.setLevelComboBox().setSelectedItem(questions.get(selectedIndex).getLevel());
            view.setLessonComboBox().setSelectedItem(questions.get(selectedIndex).getLesson());
        }
    
    }
    
    public void deleteQuestion(){
        questions.get(selectedIndex).deleteQuestion();
        fillQuestions();
    }
    public void updateQuestion(){
        //create new question according to getClass getName than send it to updateQuestion section
        if(questions.get(selectedIndex).getClass().getName().equals("Model.MultipleAnswer")){
            MultipleAnswer mpa_question = new MultipleAnswer(view.setLevelComboBox().getSelectedItem().toString(),
            new Lesson(view.setLessonComboBox().getSelectedItem().toString()), 
            Integer.valueOf(view.getTxtScore()), view.getTxtQuestion(), view.getTxtAnswer(),
            view.getTxtA(), view.getTxtB(), view.getTxtC(), view.getTxtD());
            questions.get(selectedIndex).updateQuestion(mpa_question, mpa_question.getA(),mpa_question.getB(),mpa_question.getC(),mpa_question.getD());
        }else if(questions.get(selectedIndex).getClass().getName().equals("Model.TrueFalse")){
            TrueFalse tf_question = new TrueFalse(view.setLevelComboBox().getSelectedItem().toString(),
            new Lesson(view.setLessonComboBox().getSelectedItem().toString()), 
            Integer.valueOf(view.getTxtScore()), view.getTxtQuestion(),trueFalseAnswer());
            questions.get(selectedIndex).updateQuestion(tf_question); 
        }else {
            Classic cls = new Classic(view.setLevelComboBox().getSelectedItem().toString(),
            new Lesson(view.setLessonComboBox().getSelectedItem().toString()), 
            Integer.valueOf(view.getTxtScore()), view.getTxtQuestion(), view.getTxtAnswer());
            questions.get(selectedIndex).updateQuestion(cls);
        }
        fillQuestions();
    }
    private boolean trueFalseAnswer(){
        if(view.getRadioButtonIsTrue())
            return true;
        return false;
    }
}
