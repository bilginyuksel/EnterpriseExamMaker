/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presenter;

import Model.*;
import View.IExam;
import java.io.File;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class ExamPresenter {
    private IExam view;

    private int selectedIndex =0;
    private ArrayList<Question> questions;
    private ArrayList<Question> questions_control;
    private Exam exam =null;
    private ArrayList<ClassRoom> classRooms;
    private ArrayList<String> comboBoxInfoExam;
    
    
    public ExamPresenter(IExam view){
        this.view = view;
        comboBoxInfoExam = new ArrayList<String>();
        classRooms = new ArrayList<>();
        setComboBoxLessons();
        setComboBoxSupervisor();
        setComboBoxClasses();
    }

    private void setComboBoxLessons(){
        ArrayList<Lesson> lesson = Model.Get.Get(true);
        ArrayList<String> les = new ArrayList<>();
        for(Lesson s:lesson)
            les.add(s.getLesson());
        view.setLessonComboBox().setModel(new DefaultComboBoxModel(les.toArray()));
    }
    private void setComboBoxSupervisor(){
         ArrayList<Supervisor> supervisor = Model.Get.Get(1);
        view.comboBoxSupervisor().setModel(new DefaultComboBoxModel(supervisor.toArray()));
    }
    private void setComboBoxClasses(){
         ArrayList<ClassRoom> classRooms = Model.Get.Get(2);
        view.comboBoxClassRoom().setModel(new DefaultComboBoxModel(classRooms.toArray()));
    }


    public void btnMakeExam(){
        FacadeMakeExam f =new FacadeMakeExam();
        f.prepareExam(exam, view.getLabelFileName(),classRooms);
        view.showOptionPane().showMessageDialog(null,"Exam created succesfully...");
        
    }
    public void btnPrepareExam(){
        
        int control = Integer.valueOf(view.getTxtMultipleChoicePercent().toString())+Integer.valueOf(view.getTxtTrueFalsePercent().toString()) + Integer.valueOf(view.getTxtClassicPercent().toString());
        questions_control = Model.Get.Get();
        int q_count =0;
        for(Question q:questions_control) if(q.getLesson().getLesson().equals(view.getLessonSelectedItem().toString())) q_count++;
        if(control==100 && q_count>10){
             //no new lessons here. Exam Score important think about it....
        if(view.getLevelSelectedItem()== Level.EASY)
           exam = new ExamCreator(new EasyExam(new Lesson(view.getLessonSelectedItem()),
                    /*view.getTxtSupervisorName()*/null,view.getTxtExamName(),view.getTxtExamCategory(),
                    /*view.getTxtExamLocation()*/null)).makeExam(Integer.valueOf(view.getTxtClassicPercent()),
                            Integer.valueOf(view.getTxtMultipleChoicePercent()),Integer.valueOf(view.getTxtTrueFalsePercent()));
        else if(view.getLevelSelectedItem()==Level.NORMAL)
           exam = new ExamCreator(new NormalExam(new Lesson(view.getLessonSelectedItem()),
                    /*view.getTxtSupervisorName()*/null,view.getTxtExamName(),view.getTxtExamCategory(),
                    /*view.getTxtExamLocation()*/ null)).makeExam(Integer.valueOf(view.getTxtClassicPercent()),
                            Integer.valueOf(view.getTxtMultipleChoicePercent()),Integer.valueOf(view.getTxtTrueFalsePercent()));
        else
           exam = new ExamCreator(new HardExam(new Lesson(view.getLessonSelectedItem()),/*view.getTxtSupervisorName()*/null,
                    view.getTxtExamName(),view.getTxtExamCategory(),/*view.getTxtExamLocation()*/null)).makeExam(Integer.valueOf(view.getTxtClassicPercent()),
                    Integer.valueOf(view.getTxtMultipleChoicePercent()),Integer.valueOf(view.getTxtTrueFalsePercent()));
        
        questions = exam.getQuestions();
        DefaultListModel model = new DefaultListModel();
        int totalScore = 0;
        for(int i=0;i<questions.size();i++){
            totalScore += questions.get(i).getScore();
            model.add(i, questions.get(i));
        }
        
        view.setListViewPreparedExamQuestions().setModel(model);
        if(Integer.valueOf(view.getTxtExamScore().toString())<totalScore)
            view.showOptionPane().showMessageDialog(null,"Total score of exam is "+totalScore + ".It is bigger than your score you may delete questions from exam!");
        else if(Integer.valueOf(view.getTxtExamScore().toString())>totalScore)
            view.showOptionPane().showMessageDialog(null,"Total score of exam is "+totalScore + ".It is smaller than your score you may add questions!");
        
        }else view.showOptionPane().showMessageDialog(null,"SUM OF QUESTION PERCENTS HAVE TO 100 OR NOT ENOUGH QUESTION FOR CHOSEN LESSON !");
       
    }
    public void btnDeleteListViewSelectedItem(){
        selectedIndex = view.setListViewPreparedExamQuestions().getSelectedIndex();
        questions.remove(selectedIndex);
        
        DefaultListModel model = new DefaultListModel();
        int totalScore = 0;
        for(int i=0;i<questions.size();i++){
            totalScore += questions.get(i).getScore();
            model.add(i,questions.get(i));
        }
        exam.setQuestions(questions);
        view.setListViewPreparedExamQuestions().setModel(model);
        if(Integer.valueOf(view.getTxtExamScore().toString())<totalScore)
            view.showOptionPane().showMessageDialog(null,"Total score of exam is "+totalScore + ".It is bigger than your score you may delete questions from exam!");
        else if(Integer.valueOf(view.getTxtExamScore().toString())>totalScore)
            view.showOptionPane().showMessageDialog(null,"Total score of exam is "+totalScore + ".It is smaller than your score you may add questions!");
        
    }
    public void btnAddRandomQuestion(){
        
        ArrayList<Question> new_ques = Model.Get.Get();
        
        int counter = 100;
        while(counter-->0){
            int rand = new Random().nextInt(new_ques.size());
            if(!questions.contains(new_ques.get(rand))){
            questions.add(new_ques.get(rand));
            break;
        }
        }
        DefaultListModel model = new DefaultListModel();
        int totalScore = 0;
        for(int i=0;i<questions.size();i++){
            totalScore += questions.get(i).getScore();
            model.add(i,questions.get(i));
        }
        exam.setQuestions(questions);
        view.setListViewPreparedExamQuestions().setModel(model);
        if(Integer.valueOf(view.getTxtExamScore().toString())<totalScore)
            view.showOptionPane().showMessageDialog(null,"Total score of exam is "+totalScore + ".It is bigger than your score you may delete questions from exam!");
        else if(Integer.valueOf(view.getTxtExamScore().toString())>totalScore)
            view.showOptionPane().showMessageDialog(null,"Total score of exam is "+totalScore + ".It is smaller than your score you may add questions!");
        
        }
    
    public void btnChooseFile(){
        JFileChooser fileopen = new JFileChooser(); 
        
        int answer=fileopen.showDialog(null, "Open File");
        File file = fileopen.getSelectedFile();
        
        if (answer ==JFileChooser.APPROVE_OPTION)
        {
            file= fileopen.getSelectedFile();
            view.setLabelFile(file.getName());
        }
    }
    
    public void addExamClassInformation(){
        /*do exam class information settings here*/
        if(view.comboBoxClassRoom().getSelectedItem()!=null && view.comboBoxSupervisor().getSelectedItem()!=null){
           //view.listViewExamClasses().setModel(new DefaultListModel());
           comboBoxInfoExam.add(view.comboBoxClassRoom().getSelectedItem()+"<br><b>"+view.comboBoxSupervisor().getSelectedItem()+"</b></html>");
           DefaultListModel df = new DefaultListModel();
           for(int i=0;i<comboBoxInfoExam.size();i++)
               df.add(i, comboBoxInfoExam.get(i));
           
           //control for same items or remove from comboBox
           view.listViewExamClasses().setModel(df);
           ClassRoom cr = (ClassRoom)view.comboBoxClassRoom().getSelectedItem();
           cr.setSupervisor((Supervisor)view.comboBoxSupervisor().getSelectedItem());
           classRooms.add(cr);
           view.comboBoxClassRoom().removeItem(view.comboBoxClassRoom().getSelectedItem());
           view.comboBoxSupervisor().removeItem(view.comboBoxSupervisor().getSelectedItem());
           
        }
    }

}
