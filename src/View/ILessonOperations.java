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
public interface ILessonOperations {
    
    public void setTxtLesson(String lesson);
    public String getTxtLesson();
    public JButton btnAddLesson();
    public JButton btnDeleteLesson();
    public JButton btnUpdateLesson();    
    public JComboBox fillLessons();
    public JComboBox comboBoxLecturer();
    
}
