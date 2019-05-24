package Model;

import java.sql.*;
import java.util.ArrayList;

public class Model {
    
    private static Connection connection = null;

    static{
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:qmaker.db");

            createTable();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private static void createTable(){
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS LECTURER(lecturer_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,surname TEXT,identificationnumber TEXT)");
            statement.execute("CREATE TABLE IF NOT EXISTS LESSONS(lesson_id INTEGER PRIMARY KEY AUTOINCREMENT,lecturer_id INT,lesson TEXT,"+
                    "FOREIGN KEY (lecturer_id) REFERENCES LECTURER(lecturer_id) ON DELETE CASCADE ON UPDATE NO ACTION)");
            statement.execute("CREATE TABLE IF NOT EXISTS MULTIPLECHOICE(mc_id INTEGER PRIMARY KEY AUTOINCREMENT,lesson_id INT,question TEXT," +
                    "A TEXT,B TEXT,C TEXT,D TEXT,answer TEXT,level TEXT,score INT,FOREIGN KEY (lesson_id) REFERENCES LESSONS(lesson_id) ON DELETE CASCADE ON UPDATE NO ACTION)");
            statement.execute("CREATE TABLE IF NOT EXISTS CLASSIC(c_id INTEGER PRIMARY KEY AUTOINCREMENT,lesson_id INT,question TEXT,answer TEXT,level TEXT" +
                    ",score INT,FOREIGN KEY (lesson_id) REFERENCES LESSONS(lesson_id) ON DELETE CASCADE ON UPDATE NO ACTION)");
            statement.execute("CREATE TABLE IF NOT EXISTS TRUEFALSE(tf_id INTEGER PRIMARY KEY AUTOINCREMENT,lesson_id INT,question TEXT,answer TEXT,level TEXT" +
                    ",score INT,FOREIGN KEY (lesson_id) REFERENCES LESSONS(lesson_id) ON DELETE CASCADE ON UPDATE NO ACTION)");
            statement.execute("CREATE TABLE IF NOT EXISTS SUPERVISOR(supervisor_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,surname TEXT,identificationnumber TEXT)");
            statement.execute("CREATE TABLE IF NOT EXISTS CLASSROOM(classroom_id INTEGER PRIMARY KEY AUTOINCREMENT,building_name TEXT,hall_no TEXT,hall_code TEXT,capacity INT)");
            

            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static class Add{

        private static PreparedStatement preparedStatement = null;
        
        
        
        //------------------------------------------------------
        
        public static void Add(String building,String hallNo,String hallCode,int capacity){
            
            try{
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:qmaker.db");
                preparedStatement = connection.prepareStatement("INSERT INTO CLASSROOM(building_name,hall_no,hall_code,capacity) VALUES (?,?,?,?)");
                preparedStatement.setString(1, building);
                preparedStatement.setString(2,hallNo);
                preparedStatement.setString(3,hallCode);
                preparedStatement.setInt(4,capacity);
                
                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        //----------------------
        
        

        public static void Add(String lesson,int lec_id){
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:qmaker.db");
                preparedStatement = connection.prepareStatement("INSERT INTO LESSONS(lesson,lecturer_id) VALUES(?,?)");
                preparedStatement.setString(1,lesson);
                preparedStatement.setInt(2,lec_id);
                preparedStatement.executeUpdate();
                //control if you want

                preparedStatement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        //--------------------------------------------
        
        public static void Add(String table,String name,String surname,String identificationnumber){
                 try{
                    Class.forName("org.sqlite.JDBC");
                    connection = DriverManager.getConnection("jdbc:sqlite:qmaker.db");
                
                    preparedStatement = connection.prepareStatement("INSERT INTO " + table+"(name,surname,identificationnumber) VALUES (?,?,?)");
                    preparedStatement.setString(1,name);
                    preparedStatement.setString(2,surname);
                    preparedStatement.setString(3,identificationnumber);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                    connection.close();
                 }
                    
                 catch (Exception e){
                     e.printStackTrace();
                 }   
        }
        

        public static void Add(String table,Object/*lesson,grade,question,choices,answer*/ ...params){
            int lesson_id=0;
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:qmaker.db");
                preparedStatement = connection.prepareStatement("SELECT lesson_id FROM LESSONS WHERE lesson=?");
                preparedStatement.setString(1,params[0].toString());

                ResultSet resultSet = preparedStatement.executeQuery();
                lesson_id = resultSet.getInt("lesson_id");

                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if(table == "MULTIPLECHOICE") {
                    preparedStatement = connection.prepareStatement("INSERT INTO MULTIPLECHOICE(lesson_id,question,a,b,c,d,answer,level,score) VALUES(?,?,?,?,?,?,?,?,?)");
                    preparedStatement.setInt(1,lesson_id);
                    preparedStatement.setString(2,params[1].toString());
                    preparedStatement.setString(3,params[2].toString());
                    preparedStatement.setString(4,params[3].toString());
                    preparedStatement.setString(5,params[4].toString());
                    preparedStatement.setString(6,params[5].toString());
                    preparedStatement.setObject(7,params[6]);
                    preparedStatement.setString(8,params[7].toString());
                    preparedStatement.setInt(9,Integer.valueOf(params[8].toString()));
                }else if(table == "CLASSIC"){
                    preparedStatement = connection.prepareStatement("INSERT INTO CLASSIC(lesson_id,question,answer,level,score) VALUES (?,?,?,?,?)");
                    //duzenle
                    preparedStatement.setInt(1,lesson_id);
                    preparedStatement.setString(2,params[1].toString());
                    preparedStatement.setObject(3,params[2]);
                    preparedStatement.setString(4,params[3].toString());
                    preparedStatement.setInt(5,Integer.valueOf(params[4].toString()));

                }else if(table == "TRUEFALSE"){
                    preparedStatement = connection.prepareStatement("INSERT INTO TRUEFALSE(lesson_id,question,answer,level,score) VALUES (?,?,?,?,?)");
                    preparedStatement.setInt(1,lesson_id);
                    preparedStatement.setString(2,params[1].toString());
                    preparedStatement.setObject(3,params[2]);
                    preparedStatement.setString(4,params[3].toString());
                    preparedStatement.setInt(5,Integer.valueOf(params[4].toString()));
                }

                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //--------------------------------------------


    }
    public static class Update{
        private static PreparedStatement preparedStatement=null;

        //-------------------------------------------------------------
        public static void Update(String lesson,String new_lesson,int lec_id){
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:qmaker.db");
                preparedStatement = connection.prepareStatement("UPDATE LESSONS SET lesson = ?,lecturer_id = ? WHERE lesson = ?");
                preparedStatement.setString(1,new_lesson);
                preparedStatement.setInt(2,lec_id);
                preparedStatement.setString(3,lesson);
                preparedStatement.executeUpdate();
                //control if you want

                preparedStatement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //---------------------------------------------------------------

        public static void Update(String table,Object/*lesson,grade,question,choices,answer*/ ...params){
            int lesson_id=0;
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:qmaker.db");
                preparedStatement = connection.prepareStatement("SELECT lesson_id FROM LESSONS WHERE lesson=?");
                preparedStatement.setString(1,params[0].toString());

                ResultSet resultSet = preparedStatement.executeQuery();
                lesson_id = resultSet.getInt("lesson_id");

                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if(table == "MULTIPLECHOICE") {
                    preparedStatement = connection.prepareStatement("UPDATE MULTIPLECHOICE SET lesson_id=?,question=?,a=?,b=?,c=?,d=?,answer=?,level=?,score=? WHERE question = ?");
                    preparedStatement.setInt(1,lesson_id);
                    preparedStatement.setString(2,params[1].toString());
                    preparedStatement.setString(3,params[2].toString());
                    preparedStatement.setString(4,params[3].toString());
                    preparedStatement.setString(5,params[4].toString());
                    preparedStatement.setString(6,params[5].toString());
                    preparedStatement.setObject(7,params[6]);
                    preparedStatement.setString(8,params[7].toString());
                    preparedStatement.setInt(9,Integer.valueOf(params[8].toString()));
                    preparedStatement.setString(10,params[9].toString());
                }else if(table == "CLASSIC"){
                    preparedStatement = connection.prepareStatement("UPDATE CLASSIC SET lesson_id=?,question=?,answer=?,level=?,score=? WHERE question=?");
                    preparedStatement.setInt(1,lesson_id);
                    preparedStatement.setString(2,params[1].toString());
                    preparedStatement.setObject(3,params[2]);
                    preparedStatement.setString(4,params[3].toString());
                    preparedStatement.setInt(5,Integer.valueOf(params[4].toString()));
                    preparedStatement.setString(6,params[5].toString());

                }else if(table == "TRUEFALSE"){
                    preparedStatement = connection.prepareStatement("UPDATE TRUEFALSE SET lesson_id=?,question=?,answer=?,level=?,score=? WHERE question=?");
                    preparedStatement.setInt(1,lesson_id);
                    preparedStatement.setString(2,params[1].toString());
                    preparedStatement.setObject(3,params[2]);
                    preparedStatement.setString(4,params[3].toString());
                    preparedStatement.setInt(5,Integer.valueOf(params[4].toString()));
                    preparedStatement.setString(6,params[5].toString());

                }

                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static class Delete{
        private static PreparedStatement preparedStatement = null;

        //-------------------------------------------------------------
        public static void Delete(String lesson){
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:qmaker.db");
                //if you got problem check AND!
                preparedStatement = connection.prepareStatement("DELETE FROM LESSONS WHERE lesson=?");
                preparedStatement.setString(1,lesson);
                preparedStatement.executeUpdate();


                preparedStatement.close();
                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //-------------------------------------------------------------

        public static void Delete(String table,String question,Object answer,String level){
            try {
                //if you have problem check AND's
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:qmaker.db");
                preparedStatement = connection.prepareStatement("DELETE FROM "+table+" WHERE question=? AND answer=? AND level=?");
                preparedStatement.setString(1,question);
                preparedStatement.setObject(2,answer);
                preparedStatement.setString(3,level);
                preparedStatement.executeUpdate();


                preparedStatement.close();
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        //---------------------------------------------------------------
    }
    
    public static class Get{
        private static PreparedStatement preparedStatement = null;

        public static ArrayList<Question> Get(){

            ArrayList<Question> questions = new ArrayList<Question>();
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:qmaker.db");
                preparedStatement = connection.prepareStatement("SELECT *FROM CLASSIC");
                ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){

                    /*fill questions here.. according to table name*/
                    questions.add(new Classic(resultSet.getString("level"),
                            findLesson(resultSet.getInt("lesson_id")),
                            /*update score column*/resultSet.getInt("score"),
                            resultSet.getString("question"),
                            resultSet.getObject("answer")));
                }

                //new result set after that but first try something on sqlite

                resultSet.close();
                preparedStatement.close();
                preparedStatement = connection.prepareStatement("SELECT *FROM TRUEFALSE");
                resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){
                    questions.add(new TrueFalse(resultSet.getString("level"),
                            findLesson(resultSet.getInt("lesson_id")),
                            /*update score column*/resultSet.getInt("score"),
                            resultSet.getString("question"),
                            resultSet.getObject("answer")));
                }

                resultSet.close();
                preparedStatement.close();
                preparedStatement = connection.prepareStatement("SELECT *FROM MULTIPLECHOICE");
                resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){
                    questions.add(new MultipleAnswer(resultSet.getString("level"),
                            findLesson(resultSet.getInt("lesson_id")),
                            /*update score column*/resultSet.getInt("score"),
                            resultSet.getString("question"),
                            resultSet.getObject("answer"),
                            resultSet.getString("A"),
                            resultSet.getString("B"),
                            resultSet.getString("C"),
                            resultSet.getString("D")));
                }

                resultSet.close();
                preparedStatement.close();
                connection.close();


            } catch (Exception e) {
                e.printStackTrace();
            }
            return questions;
        }

        public static Lesson findLesson(int lesson_id){
            String lesson = null;
            int grade=0;
            try {
               
                preparedStatement = connection.prepareStatement("SELECT *FROM LESSONS WHERE lesson_id=?");
                preparedStatement.setInt(1,lesson_id);

                ResultSet resultSet = preparedStatement.executeQuery();
                lesson = resultSet.getString("lesson");


            } catch (Exception e) {
                e.printStackTrace();
            }
            return new Lesson(lesson);
        }
        
         public static int findLecturer(String identificationnumber){
            String lecturer = null;
            int lec_id = 0;
            try {
                 Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:qmaker.db");
                
                preparedStatement = connection.prepareStatement("SELECT lecturer_id FROM LECTURER WHERE identificationnumber=?");
                preparedStatement.setString(1,identificationnumber);

                ResultSet resultSet = preparedStatement.executeQuery();
                lec_id= resultSet.getInt("lecturer_id");

                
                preparedStatement.close();
                connection.close();


            } catch (Exception e) {
                e.printStackTrace();
            }
            return lec_id;
        }

        public static ArrayList<Lesson> Get(boolean checked){
            ArrayList<Lesson> lessons = new ArrayList<Lesson>();
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:qmaker.db");
                preparedStatement = connection.prepareStatement("SELECT *FROM LESSONS");
                ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next())
                    lessons.add(new Lesson(resultSet.getString("lesson")));


                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
         return lessons;
        }
        
        public static ArrayList Get(int checked){
            
            switch(checked){
                case 0:
                    ArrayList<Lecturer> lecs = new ArrayList<Lecturer>();
                    try {
                    Class.forName("org.sqlite.JDBC");
                    connection = DriverManager.getConnection("jdbc:sqlite:qmaker.db");
                    preparedStatement = connection.prepareStatement("SELECT *FROM LECTURER");
                    ResultSet resultSet = preparedStatement.executeQuery();

                    while(resultSet.next())
                        lecs.add(new Lecturer(resultSet.getString("name"),resultSet.getString("surname"),
                    resultSet.getString("identificationnumber")));

                    resultSet.close();
                    preparedStatement.close();
                    connection.close();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    return lecs;
                case 1:
                    ArrayList<Supervisor> supervisors = new ArrayList<Supervisor>();
                    try {
                    Class.forName("org.sqlite.JDBC");
                    connection = DriverManager.getConnection("jdbc:sqlite:qmaker.db");
                    preparedStatement = connection.prepareStatement("SELECT *FROM SUPERVISOR");
                    ResultSet resultSet = preparedStatement.executeQuery();

                    while(resultSet.next())
                        supervisors.add(new Supervisor(resultSet.getString("name"),resultSet.getString("surname"),
                    resultSet.getString("identificationnumber")));

                    resultSet.close();
                    preparedStatement.close();
                    connection.close();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    return supervisors;
                case 2:
                    ArrayList<ClassRoom> classes = new ArrayList<ClassRoom>();
                    try {
                    Class.forName("org.sqlite.JDBC");
                    connection = DriverManager.getConnection("jdbc:sqlite:qmaker.db");
                    preparedStatement = connection.prepareStatement("SELECT *FROM CLASSROOM");
                    ResultSet resultSet = preparedStatement.executeQuery();

                    while(resultSet.next())
                        classes.add(new ClassRoom(resultSet.getString("building_name"),resultSet.getString("hall_no"),
                    resultSet.getString("hall_code"),resultSet.getInt("capacity")));

                    resultSet.close();
                    preparedStatement.close();
                    connection.close();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    return classes;
                    
            }
            return null;
        }
    }


}
