package controller;
import staffClasses.Employee;
import staffClasses.Doctors;
import staffClasses.MaintenanceStaff;
import schedule.Shift
import schedule.LeaveRequest
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class CalendarManager {
    private List<Shift> shiftList;
    private List<LeaveRequest> leaveList;

    // Constructor
    public CalendarManager() {
        this.shiftList = new ArrayList<>();
        this.leaveList = new ArrayList<>();
    }


    // Add a new shift
    public void addShift(Shift shift) {
        shiftList.add(shift);
        System.out.println("Shift added for employee ID: " + shift.getEmployeeId() + " on " + shift.getShiftDate());
    }

    // Remove a shift by ID
    public void removeShift(int shiftId) {
        shiftList.removeIf(s -> s.getId() == shiftId);
        System.out.println("Shift with ID " + shiftId + " removed.");
    }

    // Get all shifts for a specific date
    public List<Shift> getDaySchedule(LocalDate date) {
        List<Shift> result = new ArrayList<>();
        for (Shift s : shiftList) {
            if (s.getShiftDate().equals(date)) {
                result.add(s);
            }
        }
        return result;
    }

    // Get all shifts for an employee
    public List<Shift> getShiftsByEmployee(int employeeId) {
        List<Shift> result = new ArrayList<>();
        for (Shift s : shiftList) {
            if (s.getEmployeeId() == employeeId) {
                result.add(s);
            }
        }
        return result;
    }

    // Display schedule for a date
    public void displayDaySchedule(LocalDate date) {
        System.out.println("=== Schedule for " + date + " ===");
        List<Shift> dayShifts = getDaySchedule(date);
        if (dayShifts.isEmpty()) {
            System.out.println("No shifts scheduled.");
        } else {
            for (Shift s : dayShifts) {
                System.out.println(s);
            }
        }
    }


    // Submit a new leave request
    public void requestLeave(LeaveRequest leave) {
        leaveList.add(leave);
        System.out.println("Leave request submitted for employee ID: " + leave.getEmployeeId());
    }

    // Approve a leave request
    public void approveLeave(int leaveId) {
        for (LeaveRequest l : leaveList) {
            if (l.getId() == leaveId) {
                l.approve();
                System.out.println("Leave request " + leaveId + " approved.");
                return;
            }
        }
        System.out.println("Leave request ID " + leaveId + " not found.");
    }

    // Reject a leave request
    public void rejectLeave(int leaveId) {
        for (LeaveRequest l : leaveList) {
            if (l.getId() == leaveId) {
                l.reject();
                System.out.println("Leave request " + leaveId + " rejected.");
                return;
            }
        }
        System.out.println("Leave request ID " + leaveId + " not found.");
    }
    // View all leave requests
    public void displayAllLeaves() {
        System.out.println("Leave Requests");
        for (LeaveRequest l : leaveList) {
            System.out.println(l);
        }
    }
}
