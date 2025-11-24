package hospital.staffClasses;

public class Nurse extends Employee{
    private String dutyArea;

    public Nurse() {
        super();
    }
    
    public Nurse(int id, String name, String department, String dutyArea) {
        super(id, name, department, "Nurse"); 
        this.dutyArea = dutyArea;
    }

    public String getDutyArea() { return dutyArea; }
    public void setDutyArea(String dutyArea) { this.dutyArea = dutyArea; }
    @Override
    public String getDetails() { 
        return "Nurse: " + getName()
                + ", Department: " + getDepartment();
    }
    @Override
    public String getWorkingDays() {
        return "Nurses work 6 days per week with Sunday off.";
    }
}