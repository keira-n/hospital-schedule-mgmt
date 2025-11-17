package hospital.schedule;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// 1. IMPORT THIS
import java.util.Date;
// REMOVED: java.time.LocalDate

@Document(collection = "leaveRequest") 
public class LeaveRequest {

    @Id
    private String id; 

    private int employeeId;
    
    // 2. THIS IS THE FIX:
    private Date startDate; 
    private Date endDate;
    
    private String status;
    private String reason;

    public LeaveRequest() {
    }
    
    // 3. Update the constructor
    public LeaveRequest(int employeeId, Date startDate, Date endDate, String status, String reason)
    {
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.reason = reason;
    }

    // --- Getters and Setters ---
    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    // 4. Update Getters/Setters
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}