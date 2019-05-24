/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.JButton;
import javax.swing.JList;

/**
 *
 * @author Hasancan
 */
public interface ILecturerOperations {
    
    public String getTxtName();
    public String getTxtSurname();
    public String getTxtIdentificationNumber();
    public JButton btnAddLecturer();
    public JList lecturerList();
    
}
