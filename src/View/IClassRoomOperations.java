/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.JButton;

/**
 *
 * @author Hasancan
 */
public interface IClassRoomOperations {
    
    public String getTxtBuildingName();
    public String getTxtHallNo();
    public int getTxtCapacity();
    public JButton btnAddClassroom();
}
