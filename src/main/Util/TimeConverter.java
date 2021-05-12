package main.Util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeConverter {

    public static LocalDateTime localToEST(LocalDateTime localDateTime){

        ZonedDateTime toZDT = localDateTime.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
        ZonedDateTime estzdt = toZDT.withZoneSameInstant(ZoneId.of("America/New_York"));
        return estzdt.toLocalDateTime();
    }
    
}
