/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Random;
import javax.naming.Context;

/**
 *
 * @author yuksel
 */
public class FacadeMakeExam {
    
    private Exam exam;
    String path;
    private ArrayList<Student> students;
    private ArrayList<ClassRoom> classRooms;
    private FacadeMakeExam  f;
    private ArrayList<Student> tempst;
    public FacadeMakeExam(){
    }
    public void prepareExam(Exam exam,String path,ArrayList<ClassRoom> classes){
        this.exam = exam;
        this.path = path;
        this.classRooms = classes;
        this.students = new ReadStudentInfo(this.path).readExcellFile();

        this.students.removeIf(c->c.getStudentId() == null);
        examPdf();
        examLocations();
        examInspectionList();
    }
    
    private void examPdf(){
       
        Font normalFont = FontFactory.getFont(FontFactory.HELVETICA,12,Font.NORMAL,new CMYKColor(255,255,255,255));
        String exParagraph = "\nExam Name : "+exam.getExamName();
        String openingParagraph="Exam Category : " +exam.getCategory()+"\n" ;
        String fullExam = "";
        int qCounter =1;
        for(Question q:this.exam.getQuestions()){
            if(q.getClass().getName().equals("Model.MultipleAnswer")){
                MultipleAnswer ma = (MultipleAnswer)q;
                fullExam += "  "+qCounter++ +")"+ma.getQuestion() +"("+ma.getScore()+" points)"+"\n   A)"+ma.getA()+"\n   B)"+ma.getB()+"\n   C)"+ma.getC()+"\n   D)"+ma.getD()+"\n\n";
            }else if(q.getClass().getName().equals("Model.TrueFalse"))fullExam += "  "+qCounter++ +")"+q.getQuestion()+"("+q.getScore()+" points)(True/False)\n\n";
            else fullExam+="  "+qCounter++ +")"+q.getQuestion()+"("+q.getScore()+" points)\n\n";
        }
      Document document = new Document();
      try
      {
         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(exam.getExamName()+".pdf"));
         document.open();
         Image image1 = Image.getInstance("social_image.png");
         image1.setAbsolutePosition(370f, 530f);
         image1.scaleAbsolute(220, 220);
         document.add(image1);
         document.add(new Paragraph(exParagraph,normalFont));
         document.add(new Paragraph(openingParagraph,normalFont));
         document.add(new Paragraph(fullExam,normalFont));
         document.close();
         writer.close();
      } catch (Exception e)
      {
         e.printStackTrace();
      } 
    }  
    
    private void examLocations(){
        ArrayList<Student> sts = new ArrayList<>();
        sts = this.students;
        int l=0;
       for(int i=0;i<this.classRooms.size();i++){
               for(int k=0;k<this.classRooms.get(i).getCapacity();k++){
                   if(this.students.size()==l) break;
                   this.classRooms.get(i).setStudent(sts.get(l++));}
       }
       
       
        Font normalFont = FontFactory.getFont(FontFactory.HELVETICA,12,Font.NORMAL,new CMYKColor(255,255,255,255));
        Document document = new Document();
        try{
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("examLocations.pdf"));
            document.open();
            
            document.add(new Paragraph("Exam Locations for Student\n\n",normalFont));
            for(int i=0;i<this.classRooms.size();i++){
                ArrayList<Student> st = this.classRooms.get(i).getStudents();
                for(int j=0;j<st.size();j++){
                    if(st!=null)
                        document.add(new Paragraph(st.get(j).getStudentId()+"   "+st.get(j).getStudentName()+"   "+this.classRooms.get(i).getHallNo(),normalFont));
            }
            }
            
            
            document.close();
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
       
       
    }
    
    private void examInspectionList(){
         
       
        
        //this.classRooms has classes and students with in classes....
        
        Font redFont = FontFactory.getFont(FontFactory.COURIER, 18, Font.BOLD, new CMYKColor(0, 255, 0, 0));
        Font blueFont = FontFactory.getFont(FontFactory.HELVETICA, 15, Font.NORMAL, new CMYKColor(255, 0, 0, 0));
        Font normalFont = FontFactory.getFont(FontFactory.HELVETICA,12,Font.NORMAL,new CMYKColor(255,255,255,255));
  
        for(int i=0;i<this.classRooms.size();i++){
            Document document = new Document();

            try
            {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(this.classRooms.get(i).getHallCode()+".pdf"));
            document.open();
            //Image image1 = Image.getInstance("social_image.png");
            //image1.setAbsolutePosition(370f, 530f);
            //image1.scaleAbsolute(220, 220);
            //document.add(image1);
            ArrayList<Student> st = this.classRooms.get(i).getStudents();
            document.add(new Paragraph("Exam Inspection List for "+this.classRooms.get(i).getHallNo()+
                    "\n\n\n",normalFont));
            document.add(new Paragraph("Student Id                 Student Name                             Signature",normalFont));
            for(int j=0;j<st.size();j++){
                if(st!=null)
                    document.add(new Paragraph(st.get(j).getStudentId()+"         "+st.get(j).getStudentName()+"      ",normalFont));
            }
            
            document.close();
            writer.close();
        }   
            catch(Exception e)
            {
                e.printStackTrace();
            } 
        }
    }
    
    
}
