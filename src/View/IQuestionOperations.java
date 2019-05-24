/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JRadioButton;

/**
 *
 * @author Hasancan
 */
public interface IQuestionOperations {
    public void setTxtQuestion(String question);
    public void setTxtAnswer(Object answer);
    public void setTxtScore(int score);
    public void setTxtA(String a);
    public void setTxtB(String b);
    public void setTxtC(String c);
    public void setTxtD(String d);
    
    
    
    public String getTxtQuestion();
    public Object getTxtAnswer();
    public String getTxtScore();
    public String getTxtA();
    public String getTxtB();
    public String getTxtC();
    public String getTxtD();    
    public JComboBox setLessonComboBox();    
    public JComboBox setLevelComboBox();
    public JList jListQuestions();
    public boolean getRadioButtonIsFalse();
    public boolean getRadioButtonIsTrue();
    
    public JRadioButton radioButtonTrue();
    public JRadioButton radioButtonFalse();
}
