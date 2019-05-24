/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presenter;

import Model.Lecturer;
import Model.Model;
import Model.Lesson;
import View.ILecturerOperations;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author Hasancan
 */
public class LecturerOperationsPresenter {
    
    private ILecturerOperations view;
    
    ArrayList<Lecturer> lecs ;
    
    public LecturerOperationsPresenter(ILecturerOperations view){
        this.view =view;
        fillLecturer();
    }
    
    public void fillLecturer(){        
        lecs = Model.Get.Get(0);
        DefaultListModel dfl = new DefaultListModel();
        for(int i=0;i<lecs.size();i++) dfl.add(i,lecs.get(i));
        view.lecturerList().setModel(dfl);
    }
    
     public void btnAddLecturer(){         
         new Lecturer(view.getTxtIdentificationNumber(),view.getTxtSurname(),view.getTxtName()).addLecturer();         
              
    }
     
    
}
