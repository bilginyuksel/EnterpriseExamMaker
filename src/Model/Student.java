/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Hasancan
 */
public class Student {
    
    private String studentName;
    private String studentId;
    
    
    public Student(String studentName,String studentId){
        this.studentId = studentId;
        this.studentName = studentName;
    }
    
    public Student(){}
    
    public String getStudentName(){
        return studentName;
    }
    public String getStudentId(){
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    @Override
    public String toString(){
        return "Student ID : "+this.studentId +"\nStudent Name:"+this.studentName;
    }
}
