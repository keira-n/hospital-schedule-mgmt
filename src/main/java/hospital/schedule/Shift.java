/*----- Information about 1 shift ----- */
package hospital.schedule;

import java.time.LocalDate;
import java.time.LocalTime;

public class Shift
{
    String shiftID; 
    String eID; 
    LocalDate date;
    LocalTime startTime;
    LocalTime endTime;
    String role;

    // Constructor
    public Shift(String shiftID, String eID, LocalDate date, LocalTime startTime, LocalTime endTime, String role)
    {
        this.shiftID = shiftID;
        this.eID = eID; 
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.role = role;
    }

    // Modify the shift (overloading method)
    public void edit(LocalDate date)
    {
        this.date = date;
    }

    public void edit(LocalTime startTime, LocalTime endTime)
    {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters
    public String getEmployeeID()
    {
        return eID;
    }

    public LocalDate getDate()
    {
        return date;
    }

    public LocalTime getStartTime()
    {
        return startTime;
    }

    public LocalTime getEndTime()
    {
        return endTime;
    }

    public String getRole()
    {
        return role;
    }

}