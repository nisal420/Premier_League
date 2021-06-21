package com.sample;

import java.io.Serializable;
import java.util.Objects;

public class Date implements Serializable,Comparable <Date>{
    private int date;
    private int month;
    private int year;


    public Date(int date, int month, int year) {
        this.date = date;
        this.month = month;
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Date)) return false;
        Date date1 = (Date) o;
        return getYear() == date1.getYear() &&
                getMonth() == date1.getMonth() &&
                getDate() == date1.getDate();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getYear(), getMonth(), getDate());
    }

    @Override
    public String toString() {
        return "on "+ date+"/"+month+"/"+year;
    }



    @Override
    public int compareTo(Date o) {
        int calender = this.getYear() - o.getYear();
        if (calender == 0){
            calender = this.getMonth()-o.getMonth();
            if (calender == 0){
                calender = this.getDate()-o.getDate();
            }
            return calender;
        }
        return calender;
    }
}
