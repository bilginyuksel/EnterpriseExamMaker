/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author Hasancan
 */
public interface IExam {
    public String getTxtExamScore();
    public String getTxtExamName();
    public String getTxtExamCategory();
    public String getLessonSelectedItem();
    public String getLevelSelectedItem();
    public String getTxtMultipleChoicePercent();
    public String getTxtClassicPercent();
    public String getTxtTrueFalsePercent();
    public JButton btnMakeExam();
    public JButton btnPrepareExam();
    public JList setListViewPreparedExamQuestions();
    public JList listViewExamClasses();
    public JButton btnDeleteListViewSelectedItem();
    public JButton btnAddRandomQuestion();
    public JComboBox setLessonComboBox();
    public JComboBox comboBoxSupervisor();
    public JComboBox comboBoxClassRoom();
    public JOptionPane showOptionPane();
    public void setLabelFile(String text);
    public String getLabelFileName();
}
