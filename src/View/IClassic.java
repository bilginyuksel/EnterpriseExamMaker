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
public interface IClassic {
    
    public String lessonSelectedItem();
    public String levelSelectedItem();
    public String questionScore();
    public String lessonAdd();
    public String getQuestion();
    public Object getAnswer();
    public JButton saveClassicQuestion();
    public JComboBox fillLessons();
    
}
