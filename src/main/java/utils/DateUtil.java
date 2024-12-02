package utils;

/**
 * Program : StockAssistant
 * Author : llj
 * Create : 2024-09-02 17:37
 **/

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static String processDate(String dateStr) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy年M月d日");

        LocalDate date = LocalDate.parse(dateStr, inputFormatter);

        return date.format(outputFormatter);
    }

    public static String getTomorrowDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String todayDate = today.format(formatter);

        // 获取当天是星期几
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        LocalDate tomorrow;

        // 如果是星期一，则返回上周五的日期
        if (dayOfWeek == DayOfWeek.FRIDAY) {
            tomorrow = today.plusDays(3); // 上周五
        } else if (dayOfWeek == DayOfWeek.SATURDAY) {
            tomorrow = today.plusDays(2);
        } else {
            tomorrow = today.plusDays(1); // 前一天
        }

        //return "20241021";
        return tomorrow.format(formatter);
    }
}
