package hospital.staffClasses;

public abstract class Employee {
    private int id;
    private String name;
    private String department;
    private String role;

    public Employee(int id, String name, String department, String role) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.role = role;
    }

    // Encapsulation: 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    //final method
    public final void displayEmployeeID() {
        System.out.println("Employee ID: " + id);
    }
    // Abstract methods
    public abstract String getDetails();

    public abstract String getWorkingDays();

    @Override
    public String toString() {
        return "Employee ID: " + id + " | Name: " + name + " | Role: " + role;
    }
}
