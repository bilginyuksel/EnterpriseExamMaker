/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.JButton;
import javax.swing.JComboBox;
import static javax.swing.UIManager.get;

/**
 *
 * @author Hasancan
 */
public interface IMultipleChoice {    
    public String getLessonSelectedItem();
    public String getLevelSelectedItem();
    public int getTxtQuestionScore();
    public String getTxtAddLesson();
    public String getTxtChoiceA();
    public String getTxtChoiceB();
    public String getTxtChoiceC();
    public String getTxtChoiceD();
    public Object getTxtAnswer();
    public String getTxtQuestion();
    public JButton btnSaveMultipleChoiceQuestion();
    public JComboBox setLessonComboBox();
    
}
