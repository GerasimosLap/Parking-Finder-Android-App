package com.example.parking.util;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RequiresApi(api = Build.VERSION_CODES.O)

public class TimeRangeTest {

    private LocalDateTime from;
    private LocalDateTime to;
    private TimeRange tr;

    @Before
    public void setUp() {
        tr = new TimeRange(LocalDateTime.now(),30);
        from=tr.getFrom();
        to=tr.getTo();

    }

    @Test
    public void FullConTest() {
        TimeRange time = new TimeRange(LocalDateTime.now(),LocalDateTime.now());
        assertNotNull(time);
    }



    @Test
        public void getFromTest(){
        LocalDateTime a = LocalDateTime.of(2021, 2, 13, 15, 56);
        TimeRange time = new TimeRange(a,30);
        from=a;
        assertEquals(from.withNano(0),time.getFrom().withNano(0));
    }

    @Test
    public void differenceTest(){
        TimeRange tr = new TimeRange(30);

        assertEquals(30, tr.getDifference());

        tr = new TimeRange(LocalDateTime.now(), 30);
        assertEquals(30, tr.getDifference());
    }

    @Test
    public void containsRange(){
        TimeRange tr1 = new TimeRange(30);
        TimeRange tr2 = new TimeRange(15);

        LocalDateTime ldc = LocalDateTime.now();
        ldc = ldc.minusMinutes(2);
        TimeRange tr3 = new TimeRange(ldc,10);

        assertTrue(tr1.containsRange(tr2));
        assertFalse(tr2.containsRange(tr1));
        assertFalse(tr1.containsRange(tr3));
        assertTrue(tr1.containsRange(tr1));
    }

    @Test
    public void containsDateTime(){
        LocalDateTime ldc = LocalDateTime.now();
        LocalDateTime ldc1, ldc2, ldc3,ldc4;

        ldc1 = ldc.minusMinutes(2);
        ldc2 = ldc.plusMinutes(5);
        ldc3 = ldc.plusMinutes(31);
        ldc4 = ldc.plusMinutes(5);
        TimeRange tr = new TimeRange(ldc, ldc.plusDays(1).plusHours(1));
        TimeRange tr1 = new TimeRange(ldc,10);

        assertFalse(tr1.containsDateTime(ldc1));
        assertTrue(tr1.containsDateTime(ldc2));
        assertFalse(tr1.containsDateTime(ldc3));
        assertTrue(tr1.containsDateTime(ldc));
        assertTrue(tr.containsDateTime(ldc4));
    }

    @Test
    public void addMinutesTest(){
        TimeRange time = new TimeRange(LocalDateTime.now(),0);
        time.setTo(time.addMinutes(time.getTo(),30));
        assertEquals(30,time.getDifference());
    }

    @Test
    public void toStringTest(){
        String fromstr = from.format(TimeRange.formatter);
        String tostr = to.format(TimeRange.formatter);
        String str= "TimeRange{" +
                "from=" + fromstr +
                ", to=" + tostr +
                '}';
        System.out.println(tr);
        assertEquals(str,tr.toString());
    }
}

