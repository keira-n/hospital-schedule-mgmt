/* ----- Information about leaving ----- */
package hospital.schedule;

import java.time.LocalDate;

public class LeaveRequest {
    String eID; 
    LocalDate starDate; 
    LocalDate endDate;
    String status;
    String reason;

    // Constructor
    public LeaveRequest(String eID, LocalDate startDate, LocalDate endDate, String status, String reason)
    {
        this.eID = eID;
        this.starDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.reason = reason;
    }

    // Getters
    public String getEmployeeID()
    {
        return eID;
    }

    public LocalDate getStartDate()
    {
        return starDate;
    }

    public LocalDate getEndDate()
    {
        return endDate;
    }

    public String getStatus()
    {
        return status;
    }

    public String getReason()
    {
        return reason;
    }
}
