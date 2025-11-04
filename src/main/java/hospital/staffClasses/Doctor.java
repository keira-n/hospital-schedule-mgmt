package hospital.staffClasses;

public class Doctor extends Employee{
    String specialtyArea;
    boolean onDuty;
    boolean emergencyCall;

    public Doctor(int id, String name, String department, String role) {
        super(id, name, department, role);
    }

    public class GP extends Doctor {
        public GP(int id, String name, String department, String specialtyArea) {
            super(id, name, department, "General Practitioner");
            this.specialtyArea = specialtyArea;
            this.onDuty = false;
            this.emergencyCall = false;
        }
    }

    public class Cardiologist extends Doctor {
        public Cardiologist(int id, String name, String department, String specialtyArea) {
            super(id, name, department, "Cardiologist");
            this.specialtyArea = specialtyArea;
            this.onDuty = false;
            this.emergencyCall = false;
        }
    }

    public class Psychiatrist extends Doctor {
        public Psychiatrist(int id, String name, String department, String specialtyArea) {
            super(id, name, department, "Psychiatrist");
            this.specialtyArea = specialtyArea;
            this.onDuty = false;
            this.emergencyCall = true;
        }
    }

    public class Radiologist extends Doctor {
        public Radiologist(int id, String name, String department, String specialtyArea) {
            super(id, name, department, "Radiologist");
            this.specialtyArea = specialtyArea;
            this.onDuty = false;
            this.emergencyCall = false;
        }
    }

    public class Neurologist extends Doctor {
        public Neurologist(int id, String name, String department, String specialtyArea) {
            super(id, name, department, "Neurologist");
            this.specialtyArea = specialtyArea;
            this.onDuty = false;
            this.emergencyCall = true;
        }
    }

    public class Anesthesiologist extends Doctor {
        public Anesthesiologist(int id, String name, String department, String specialtyArea) {
            super(id, name, department, "Anesthesiologist");
            this.specialtyArea = specialtyArea;
            this.onDuty = false;
            this.emergencyCall = true;
        }
    }

    public class Surgeon extends Doctor {
        public Surgeon(int id, String name, String department, String specialtyArea) {
            super(id, name, department, "Surgeon");
            this.specialtyArea = specialtyArea;
            this.onDuty = false;
            this.emergencyCall = true;
        }
    }
    public String getSpecialtyArea() {
        return specialtyArea;
    }

    public void setOnDuty(boolean onDuty){
        this.onDuty = onDuty;
    }

    public String isEmergencyCall() {
        return (emergencyCall ? "Emergency call available" : "Emergency call not available");
    }
    @Override
    public String getDetails(){
        return "Doctor: " + getName()
                + " Department: " + getDepartment()
                + " Specialty: " + specialtyArea
                + " On Duty: " + (onDuty ? "Yes" : "No")
                + " On Call: " + (emergencyCall ? "Emergency call available" : "Emergency call not available");
    }

    @Override
    public String getWorkingDays() {
        return "Doctors work 40 hours per week.";
    }

    public void markOnDuty(boolean duty) {
        this.onDuty = duty;
        System.out.println(getName() + " is " + (duty ? " on Duty" : " off Duty"));
    }
}
