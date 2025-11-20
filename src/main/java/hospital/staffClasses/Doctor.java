package hospital.staffClasses;

// Class Doctor is inherited from class Employee
public class Doctor extends Employee {
    private String specialtyArea;
    private boolean onDuty;
    private boolean emergencyCall;

    public Doctor() {
        super();
    }
    // Creating a Doctor with a specific role
    public Doctor(int id, String name, String department, String role, String specialtyArea, boolean emergencyCall) {
        super(id, name, department, role); 
        this.specialtyArea = specialtyArea;
        this.onDuty = false;
        this.emergencyCall = emergencyCall;
    }
    
    // Creating a "generic" Doctor
    public Doctor(int id, String name, String department, String specialtyArea, boolean emergencyCall) {
        super(id, name, department, "Doctor"); 
        this.specialtyArea = specialtyArea;
        this.onDuty = false;
        this.emergencyCall = emergencyCall;
    }

    // --- Getters and Setters ---
    public String getSpecialtyArea() { return specialtyArea; }
    public void setSpecialtyArea(String specialtyArea) { this.specialtyArea = specialtyArea; }
    public boolean isOnDuty() { return onDuty; }
    public void setOnDuty(boolean onDuty) { this.onDuty = onDuty; }
    public boolean isEmergencyCall() { return emergencyCall; }
    public void setEmergencyCall(boolean emergencyCall) { this.emergencyCall = emergencyCall; }

    // --- Overridden Methods ---
    @Override
    public String getDetails() { 
        return "Doctor: " + getName() +
                " Department: " + getDepartment() +
                " Specialty: " + specialtyArea +
                " On Duty: " + (onDuty ? "Yes" : "No") +
                " Emergency Call: " + (emergencyCall ? "Available" : "Not Available");
    }

    @Override
    public String getWorkingDays() { /* (no change) */
        return "Doctors typically work 5 days per week with rotating on-call duties.";
    }
    public void markOnDuty(boolean duty) { /* (no change) */
        this.onDuty = duty;
        System.out.println(getName() + (duty ? " is on duty." : " is off duty."));
    }
}

// Classs for specific types of Doctors
class GP extends Doctor {
    public GP() {
        super();
    }

    public GP(int id, String name, String department) {
        super(id, name, department, "GP", "GP", false);
    }
}

class Cardiologist extends Doctor {
    public Cardiologist() {
        super();
    }
    
    public Cardiologist(int id, String name, String department) {
        super(id, name, department, "Cardiologist", "Cardiologist", false);
    }
}

class Psychiatrist extends Doctor {
    public Psychiatrist() {
        super();
    }
    
    public Psychiatrist(int id, String name, String department) {
        super(id, name, department, "Psychiatrist", "Psychiatrist", true);
    }
}

class Radiologist extends Doctor {
    public Radiologist() {
        super();
    }
    
    public Radiologist(int id, String name, String department) {
        super(id, name, department, "Radiologist", "Radiologist", false);
    }
}

class Neurologist extends Doctor {
    public Neurologist() {
        super();
    }
    
    public Neurologist(int id, String name, String department) {
        super(id, name, department, "Neurologist", "Neurologist", true);
    }
}

class Anesthesiologist extends Doctor {
    public Anesthesiologist() {
        super();
    }
    
    public Anesthesiologist(int id, String name, String department) {
        super(id, name, department, "Anesthesiologist", "Anesthesiologist", true);
    }
}

class Surgeon extends Doctor {
    public Surgeon() {
        super();
    }
    
    public Surgeon(int id, String name, String department) {
        super(id, name, department, "Surgeon", "Surgeon", true);
    }
}