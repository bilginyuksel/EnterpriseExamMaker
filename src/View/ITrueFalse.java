/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import javax.swing.JButton;
import javax.swing.JComboBox;

/**
 *
 * @author Hasancan
 */
public interface ITrueFalse {
    public String getLessonSelectedItem();
    public String getLevelSelectedItem();
    public String getTxtQuestionScore();
    public String getTxtAddLesson();
    public String getTxtQuestion();
    public boolean getRadioButtonIsTrue();
    public JButton btnSaveTrueFalseQuestion();
    public JComboBox setLessonComboBox();
}
