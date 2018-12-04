/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transcripts;

/**
 *
 * @author Goitsemodimo
 */
/**
public class classModule {
    private double marks, grade_point, cgpa, weighted_gp, total_weighted_gp, cumulative_total_weighted_gp;
    private float sgpa;
    private int credits, total_credits, cumulative_total_credits;
    private String grade;
    
    public classModule(double marks, double grade_point, float sgpa, double cgpa, double weighted_gp, 
            int credits, int total_credits, String grade, int cumulative_total_credits, double cumulative_total_weighted_gp ){
        this.grade = grade;
        this.marks = marks;
        this.grade_point = grade_point;
        this.weighted_gp = weighted_gp;
        this.credits = credits;
        this.total_credits = total_credits;
        this.cumulative_total_credits = cumulative_total_credits;
        this.cumulative_total_weighted_gp = cumulative_total_credits;
        this.sgpa = sgpa;
        this.cgpa = cgpa;
    
    }
    
    public void setGrade(String grade){
        this.grade = grade;
    
    }
    public String getGrade(){
        return grade;
    
    }
    public void setMarks(double marks){
        this.marks = marks;
    }
    public double getMarks(){
        return marks;    
    }
    
    public void setGrade_Point(double grade_point){
        this.grade_point = grade_point;    
    }
    public double getGrade_Point(){
        return grade_point;
    
    }
    
    public void setCredits(int credits){
        this.credits = credits;        
    }
    public int getCredits(){
        return credits;
    
    }
    
    public double getWeighted_gp(){
        
        weighted_gp = grade_point * credits;
        
        return weighted_gp;
        }
    
    public int getTotal_Credits(){
        
        total_credits = total_credits + credits;
        
        return total_credits;
    }
    
    public int getCumulative_Total_Credits(){
        cumulative_total_credits = cumulative_total_credits + total_credits;
        
        return cumulative_total_credits;
    
    }
    
    public double getCumulative_Total_Weighted_gp(){
        cumulative_total_weighted_gp = cumulative_total_weighted_gp + total_weighted_gp;
        
        return cumulative_total_weighted_gp;
    
    }
    
    public float getSGPA(){
        sgpa = (float) (total_weighted_gp / total_credits);
                
        return sgpa;
    }
    
    public double getCGPA(){
        cgpa = cumulative_total_weighted_gp / cumulative_total_credits;
        
        return cgpa;    
    }
    
}
**/