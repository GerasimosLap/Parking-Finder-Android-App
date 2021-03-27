package com.example.parking.util;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@RequiresApi(api = Build.VERSION_CODES.O)
public class TimeRange {
    private LocalDateTime from;
    private LocalDateTime to;

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/uuuu HH:mm");
    public TimeRange(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        this.to = to;
    }

    public TimeRange(LocalDateTime from, long extraMinutesTillExchange) {
        this.from = from;
        this.to = addMinutes(from, extraMinutesTillExchange);
    }
    public TimeRange(long extraMinutesTillExchange) {
        this.from = LocalDateTime.now();
        this.to = addMinutes(from, extraMinutesTillExchange);
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    /**
     * @return Τα λεπτά μεταξύ του from και του to του object
     */
    public long getDifference(){
        return Duration.between(from, to).toMinutes();
    }

    /**
     * @param ld1 Ένα LocalDateTime object που ανιπαριστά το from
     * @param ld2 Ένα LocalDateTime object που ανιπαριστά το to
     * @return Τα λεπτά μεταξύ του from και του to του που δώθηκαν
     */
    public static long getDifference(LocalDateTime ld1, LocalDateTime ld2){
        return Duration.between(ld1, ld2).toMinutes();
    }

    /**
     * @return {@code true} Αν τα χρονικά όρια του TimeRange object που δώθηκε είναι εντός αυτού του object
     */
    public boolean containsRange(final TimeRange other)
    {
        return getDifference(this.from, other.from) >= 0 &&
                getDifference(other.to, this.to) >= 0;
    }

    /**
     * @param ld Μια χρονική στιγμή ως LocalDateTime
     * @return {@code true} Αν η δωθήσα χρονική στιγμή είναι εντός των χρονικών ορίων του αντικειμένου
     */
    public boolean containsDateTime(LocalDateTime ld)
    {
        return getDifference(this.from, ld) >= 0 &&
                getDifference(ld, this.to) >= 0;
    }

    /**
     * @param date Η δωθήσα χρονική στιγμήProvided time instant
     * @param minutes Τα λεπτά που είναι να προστεθούν
     * @return Μια καινούρια χρονική στιγμή η οποία είναι όσα λεπτά καθορίστηκε πιο μετά στον χρόνο
     */
    public LocalDateTime addMinutes(LocalDateTime date, long minutes){
        return date.plusMinutes(minutes);
    }


    @Override
    public String toString() {
        String fromstr = from.format(formatter);
        String tostr = to.format(formatter);
        return "TimeRange{" +
                "from=" + fromstr +
                ", to=" + tostr +
                '}';
    }
}
