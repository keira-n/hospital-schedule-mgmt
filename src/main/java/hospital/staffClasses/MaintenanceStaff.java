package hospital.staffClasses;

public class MaintenanceStaff extends Employee{
    private String dutyArea;
    private boolean onDuty;

    public MaintenanceStaff() {
        super();
    }
    // Constructor
    public MaintenanceStaff(int id, String name, String department, String dutyArea) {
        super(id, name, department, "Maintenance Staff");
        this.dutyArea = dutyArea;
        this.onDuty = false;
    }

    // --- Getters and Setters ---
    public String getDutyArea() { return dutyArea; }
    public void setDutyArea(String dutyArea) { this.dutyArea = dutyArea; }
    public boolean isOnDuty() { return onDuty; }
    public void setOnDuty(boolean onDuty) { this.onDuty = onDuty; }
    @Override
    public String getDetails() { /* (no change) */
        return "Maintenance Staff: " + getName()
                + " Department: " + getDepartment()
                + " Duty Area: " + dutyArea
                + " On Duty: " + (onDuty ? "Yes" : "No");
    }

    // --- Overridden Methods ---
    @Override
    public String getWorkingDays() {
        return "Maintenance staff work 6 days per week with Sunday off.";
    }
    public void markOnDuty(boolean duty) {
        this.onDuty = duty;
        System.out.println(getName() + " duty status: " + (duty ? "On Duty" : "Off Duty"));
    }
}