package ru.ftptpf.introduction.practice;

public class TimeInterval {

    private int seconds;
    private int minutes;
    private int hours;

    public TimeInterval(int totalSeconds) {
        this.seconds = totalSeconds % 3600 % 60;
        this.minutes = totalSeconds % 3600 / 60;
        this.hours = totalSeconds / 3600;
    }

    public TimeInterval(int seconds, int minutes, int hours) {
        this.seconds = seconds;
        this.minutes = minutes;
        this.hours = hours;
    }

    public int totalSeconds() {
        return seconds + minutes * 60 + hours * 3600;
    }

    public TimeInterval sum(TimeInterval seconds) {
        return new TimeInterval(this.totalSeconds() + seconds.totalSeconds());
    }

    public void print() {
        System.out.println("h: " + hours + ", m: " + minutes + ", s: " + seconds);
    }
}
