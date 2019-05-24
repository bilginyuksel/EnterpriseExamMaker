/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

/**
 *
 * @author Hasancan
 */
public class ReadStudentInfo {
    
    private String path;    

    
    public ReadStudentInfo(String path){
        
        this.path=path;
    }
    
    public ArrayList<Student> readExcellFile(){
        ArrayList<Student> students = new ArrayList<Student>();
        
         try
        {
            FileInputStream file = new FileInputStream(new File(this.path));
 
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);
 
            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
 
            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                Student s = new Student();
                 
                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    //Object numeric = cell.getCellType().NUMERIC;
            
                    if( cell.getColumnIndex() == 0){
                        s.setStudentId(cell.getStringCellValue());
                        
                    }   
                    else{
                        s.setStudentName(cell.getStringCellValue());
                    }

                }
                    students.add(s);
            }
            file.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return students;
    }
    
    /*
    read student information from excel
    fill the list of students
    when exam created ask for this ......
    */
    
}
