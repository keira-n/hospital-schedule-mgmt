package hospital.schedule;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// 1. IMPORT THIS
import java.util.Date;

// We do NOT need java.time.LocalDate or LocalTime
// import java.time.LocalDate;
// import java.time.LocalTime;

@Document(collection = "shift") 
public class Shift
{
    @Id
    private String id; 

    private int employeeId; 
    
    // 2. THIS IS THE FIX:
    // Change from LocalDate to the old Date object
    private Date date;
    
    // Change from LocalTime to String
    private String startTime;
    private String endTime;
    
    private String role;

    public Shift() {
    }

    // 3. Update the constructor
    public Shift(int employeeId, Date date, String startTime, String endTime, String role)
    {
        this.employeeId = employeeId; 
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.role = role;
    }

    // --- Getters and Setters ---
    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    // 4. Update Getters/Setters for new types
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}