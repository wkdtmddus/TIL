import React, { useState } from "react";
import styles from "./modal.module.css";
import Button from "../Button/Button";
import Header from "../Header/Header";
import { useAnnouncementStore } from "@/app/store/useAnnouncementStore"; 

interface CalendarModalProps {
  onClose: () => void;
  onConfirm: (selectedDate: Date | null) => void;
  isStart: boolean;
}
export default function CalendarModal({ onClose, onConfirm, isStart }: CalendarModalProps) {
  const [selectedDate, setSelectedDate] = useState<Date | null>(null);
  const [currentDate] = useState<Date>(new Date());
  const { setStartDate } = useAnnouncementStore();
  const { setEndDate } = useAnnouncementStore();

  const today = new Date();
  const monthsToRender = 12;

  const handleDayClick = (day: number, month: number, year: number) => {
    // Set the time to noon to avoid timezone issues when converting to ISO format
    const clickedDate = new Date(year, month, day, 12, 0, 0); // Set hours to 12 to prevent timezone shift
    if (clickedDate >= today || (clickedDate.getDate() === today.getDate() && clickedDate.getMonth() === today.getMonth() && clickedDate.getFullYear() === today.getFullYear())) {
      setSelectedDate(clickedDate);
    }
  };

  const getMonthData = (year: number, month: number) => {
    const daysInMonth = new Date(year, month + 1, 0).getDate();
    const firstDayOfMonth = new Date(year, month, 1).getDay();
    return { daysInMonth, firstDayOfMonth };
  };

  function formatDateWithDayOfWeek(date: Date | null): string {
    if (!date) return "날짜를 선택해주세요";
  
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const day = date.getDate().toString().padStart(2, '0');
  
    // Get day of the week (0 = Sunday, 6 = Saturday)
    const daysOfWeek = ['일', '월', '화', '수', '목', '금', '토'];
    const dayOfWeek = daysOfWeek[date.getDay()];
  
    return `${year}. ${month}. ${day} (${dayOfWeek})`;
  }




  const handleConfirm = () => {
    if (selectedDate) {
      // Format the date as a string and update Zustand store
      console.log(selectedDate)
      console.log(isStart)
      if (isStart) {
      setStartDate(formatDateWithDayOfWeek(selectedDate)); // Save the formatted date
      } else {
        setEndDate(formatDateWithDayOfWeek(selectedDate));
      }
      onConfirm(selectedDate); // Pass the selected date to the parent component
    }
  };

  const renderMonth = (monthOffset: number) => {
    const baseDate = new Date(currentDate.getFullYear(), currentDate.getMonth() + monthOffset, 1);
    const year = baseDate.getFullYear();
    const month = baseDate.getMonth();
    const { daysInMonth, firstDayOfMonth } = getMonthData(year, month);

    return (
      <>
        <div key={monthOffset} className={styles.monthContainer}>
          <div className={styles.monthTitle}>
            <h3>{`${year}년 ${month + 1}월`}</h3>
          </div>

          <div className={styles.calendarContainer}>
            {Array.from({ length: firstDayOfMonth }).map((_, index) => (
              <div key={`empty-${index}`} className={styles.calendarDay}></div>
            ))}
            {Array.from({ length: daysInMonth }, (_, index) => {
              const day = index + 1;
              const date = new Date(year, month, day);
              const isToday = day === today.getDate() && month === today.getMonth() && year === today.getFullYear();
              const isSelected = selectedDate?.getDate() === day && selectedDate?.getMonth() === month && selectedDate?.getFullYear() === year;
              const isPastDate = date < today && !isToday;

              const isDisabled = isPastDate;

              const dayOfWeek = new Date(year, month, day).getDay(); // Get the day of the week (0 = Sunday, 6 = Saturday)
              const isSunday = dayOfWeek === 0;
              const isSaturday = dayOfWeek === 6;

              return (
                <div
                  key={`day-${year}-${month}-${day}`}
                  className={`${styles.calendarDay} ${isToday ? styles.today : ""} ${isSelected ? styles.selected : ""} ${isDisabled ? styles.disabled : ""} ${isSunday ? styles.sunday : ""} ${isSaturday ? styles.saturday : ""}`}
                  onClick={() => !isDisabled && handleDayClick(day, month, year)}
                >
                  {isToday ? "오늘" : day}
                </div>
              );
            })}
          </div>
        </div>
        <hr className={styles.divideline2}></hr>
      </>
    );
  };

  return (
    <div className={styles.modalBackground}>
      <div className={styles.modalContainer}>
        <div className={styles.headermargin}>
          <Header
            imagesSrc="/image/back-icon.png"
            altText="뒤로 가기"
            href="/announcement"
            navigateType='not'
            title='날짜 선택'
            onClick={onClose}
          />
        </div>

        <div className={styles.weekdaysFixed}>
          <div className={styles.weekdaysContainer}>
            {["일", "월", "화", "수", "목", "금", "토"].map((weekday, index) => (
              <div
                key={index}
                className={`${styles.weekday} ${index === 0 ? styles.sunday : ""} ${index === 6 ? styles.saturday : ""}`}
              >
                {weekday}
              </div>
            ))}
          </div>
        </div>

        <hr className={styles.divideline}></hr>

        <div className={styles.monthScrollContainer}>
          {Array.from({ length: monthsToRender }).map((_, index) => renderMonth(index))}
        </div>

        <div className={styles.calendarBottom}>
          <Button
            label={selectedDate ? formatDateWithDayOfWeek(selectedDate) : "날짜를 선택해주세요"}
            type="button"
            disabled={!selectedDate}
            onClick={handleConfirm}
          />
        </div>

      </div>
    </div>
  );
}
