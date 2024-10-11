package com.ssafy.ios.lineup.backend.common.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * packageName    : com.ssafy.ios.lineup.backend.common.util fileName       : DateTimeUtil author :
 * moongi date           : 9/26/24 description    :
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DateTimeUtil {

    public static String getKoreanDayOfWeek(DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case MONDAY:
                return "월";
            case TUESDAY:
                return "화";
            case WEDNESDAY:
                return "수";
            case THURSDAY:
                return "목";
            case FRIDAY:
                return "금";
            case SATURDAY:
                return "토";
            case SUNDAY:
                return "일";
            default:
                return "";
        }
    }

    // 영어 요일을 한글로 변환하는 메서드
    public static String convertToKoreanDayOfWeek(String dateWithDay) {
        // 영어 요일을 한글 요일로 치환하기 위한 맵핑
        Map<String, String> dayOfWeekMap = Map.of(
                "Mon", "월",
                "Tue", "화",
                "Wed", "수",
                "Thu", "목",
                "Fri", "금",
                "Sat", "토",
                "Sun", "일"
        );

        // 영어 요일 부분만 추출 (예: "(Tue)")
        String englishDay = dateWithDay.substring(dateWithDay.indexOf("(") + 1,
                dateWithDay.indexOf(")"));

        // 영어 요일을 한글 요일로 변경
        String koreanDay = dayOfWeekMap.getOrDefault(englishDay, englishDay);

        // 최종적으로 한글 요일로 변환하여 반환
        return dateWithDay.replace(englishDay, koreanDay);
    }

    /**
     * LocalDateTime을 "yyyy.MM.dd (요일)" 형식의 문자열로 변환하는 메서드
     *
     * @param dateTime 변환할 LocalDateTime 객체
     * @return 포맷된 날짜 문자열 (예: 2024.10.01 (화))
     */
//    public static String formatDate(LocalDateTime dateTime) {
//        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd (E)", Locale.ENGLISH); // 영어 요일로 포맷
//        String formattedDate = dateTime.format(dateFormatter);
//
//        // 영어 요일을 한글 요일로 변환
//        return convertToKoreanDayOfWeek(formattedDate);
//    }

    /**
     * LocalDateTime을 "yyyy.MM.dd (요일)" 형식의 문자열로 변환하는 메서드
     *
     * @param dateTime 변환할 LocalDateTime 객체
     * @return 포맷된 날짜 문자열 (예: 2024. 09. 28 (토))
     */
    public static String formatDate(LocalDateTime dateTime) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy. MM. dd", Locale.KOREA);
        String formattedDate = dateTime.format(dateFormatter);
        String dayOfWeek = getKoreanDayOfWeek(dateTime.getDayOfWeek());
        return formattedDate + " (" + dayOfWeek + ")";
    }

    /**
     * LocalDateTime에서 시간 부분만 "HH:mm" 형식으로 반환하는 메서드
     *
     * @param dateTime 변환할 LocalDateTime 객체
     * @return 포맷된 시간 문자열 (예: 07:00)
     */
    public static String formatTime(LocalDateTime dateTime) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return dateTime.toLocalTime().format(timeFormatter);
    }

    /**
     * 날짜와 시간을 문자열로 받아서 LocalDateTime 객체로 변환하는 메서드
     *
     * @param date "yyyy.MM.dd" 형식의 날짜 문자열
     * @param time "HH:mm" 형식의 시간 문자열
     * @return LocalDateTime 객체
     */
    public static LocalDateTime parseDateTime(String date, String time) {
        String formattedDate = date.split(" ")[0];
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy. MM. dd (E)",
                Locale.KOREA);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
//        LocalDateTime parsedDate = LocalDateTime.of(LocalDate.parse(formattedDate, dateFormatter),
//                LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm")));

        LocalDate localDate = LocalDate.parse(date, dateFormatter);
        LocalTime localTime = LocalTime.parse(time, timeFormatter);
//        log.info(String.valueOf(localDate));
//        log.info(String.valueOf(localTime));
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);

        log.info("LocalDateTime before Zone: " + localDateTime);  // 로그: 변환 전 LocalDateTime 확인

        // 명시적으로 시간대를 지정하여 ZonedDateTime 생성
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Asia/Seoul"));

        log.info("ZonedDateTime with Time Zone: " + zonedDateTime);  // 로그: 변환 후 ZonedDateTime 확인
        return localDateTime;
    }

    /**
     * LocalDateTime을 날짜와 시간 문자열로 분리하는 메서드
     *
     * @param dateTime LocalDateTime 객체
     * @return 분리된 날짜 및 시간 정보 배열 [0]: 날짜, [1]: 시간
     */
    public static String[] splitDateTime(LocalDateTime dateTime) {
        String date = formatDate(dateTime);
        String time = formatTime(dateTime);
        return new String[]{date, time};
    }


}
