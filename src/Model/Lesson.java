package Model;

public class Lesson {
    private String lesson;
    private int lec_id;

    public Lesson(String lesson,int lec_id){
        this.lesson = lesson;
        this.lec_id = lec_id;
    }

    public Lesson(String lesson){
        this.lesson = lesson;
    }

    public void addLesson(){
        Model.Add.Add(this.lesson,this.lec_id);
    }
    public void updateLesson(String new_les,int lec_id){ Model.Update.Update(this.lesson,new_les,lec_id); }
    public void deleteLesson(){
        Model.Delete.Delete(this.lesson);
    }

    public int getLec_id() {
        return lec_id;
    }
    public String getLesson() {
        return lesson;
    }
}
