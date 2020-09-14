package com.neeru.anonymous;

public class Attendance {
    private String subject;
    private int attended;
    private int total;
    private int days;
    private int percent;

    public Attendance(String subject, int attended, int total, int days, int percent) {
        this.subject = subject;
        this.attended = attended;
        this.total = total;
        this.days = days;
        this.percent = percent;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "Subject='" + subject + '\'' +
                ", attended=" + attended +
                ", total=" + total +
                ", days=" + days +
                ", percent=" + percent +
                '}';
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getAttended() {
        return attended;
    }

    public void setAttended(int attended) {
        this.attended = attended;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
}
