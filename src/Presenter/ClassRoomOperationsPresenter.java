/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presenter;

import Model.ClassRoom;
import View.IClassRoomOperations;

/**
 *
 * @author Hasancan
 */
public class ClassRoomOperationsPresenter {
    
    private IClassRoomOperations view;
    
    
    public ClassRoomOperationsPresenter(IClassRoomOperations view){
        this.view =view;
    }
    
     public void btnAddClassroom(){         
         new ClassRoom(view.getTxtBuildingName(),view.getTxtHallNo(),view.getTxtCapacity()).addClassRoom();
    }
    
}
