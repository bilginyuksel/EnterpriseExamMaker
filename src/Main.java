import Model.*;
import java.util.ArrayList;


public class Main {

    public static void main(String args[]){
            
          FacadeMakeExam fd  = new FacadeMakeExam();
          ArrayList<ClassRoom> cs = new ArrayList<>();
          cs.add(new ClassRoom("Muhendislik Fakultesi","CA-21",5));
          cs.add(new ClassRoom("Muhendislik Fakultesi","C-206",3));
          fd.prepareExam(null, "deneme.xlsx", cs);
          
            
    }

  
   }
