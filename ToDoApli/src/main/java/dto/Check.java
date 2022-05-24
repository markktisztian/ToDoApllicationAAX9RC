package dto;

import java.time.LocalDate;

public class Check {
    public boolean checkIfEmpty(String task, LocalDate startdate, LocalDate finishdate) {
        return (task.isEmpty() || startdate == null || finishdate == null);
    }

    public boolean checkDates(LocalDate startdate, LocalDate finishdate) {
        return startdate.isAfter(finishdate);
    }
}
