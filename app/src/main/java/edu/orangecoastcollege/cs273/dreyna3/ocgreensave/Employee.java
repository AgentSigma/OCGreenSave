package edu.orangecoastcollege.cs273.dreyna3.ocgreensave;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Daniel on 12/1/2017.
 */

public class Employee implements Parcelable {
    private int id;
    private String name;
    private String weekDate;
    private String mondayHours;
    private String tuesdayHours;
    private String wednesdayHours;
    private String thursdayHours;
    private String fridayHours;
    private String saturdayHours;
    private String sundayHours;

    public Employee(int id, String name, String weekDate, String mondayHours, String tuesdayHours,
                    String wednesdayHours, String thursdayHours, String fridayHours,
                    String saturdayHours, String sundayHours) {
        this.id = id;
        this.name = name;
        this.weekDate = weekDate;
        this.mondayHours = mondayHours;
        this.tuesdayHours = tuesdayHours;
        this.wednesdayHours = wednesdayHours;
        this.thursdayHours = thursdayHours;
        this.fridayHours = fridayHours;
        this.saturdayHours = saturdayHours;
        this.sundayHours = sundayHours;
    }

    public Employee(String name, String weekDate, String mondayHours, String tuesdayHours,
                    String wednesdayHours, String thursdayHours, String fridayHours,
                    String saturdayHours, String sundayHours) {
        this.name = name;
        this.weekDate = weekDate;
        this.mondayHours = mondayHours;
        this.tuesdayHours = tuesdayHours;
        this.wednesdayHours = wednesdayHours;
        this.thursdayHours = thursdayHours;
        this.fridayHours = fridayHours;
        this.saturdayHours = saturdayHours;
        this.sundayHours = sundayHours;
    }

    protected Employee(Parcel in) {
        id = in.readInt();
        name = in.readString();
        weekDate = in.readString();
        mondayHours = in.readString();
        tuesdayHours = in.readString();
        wednesdayHours = in.readString();
        thursdayHours = in.readString();
        fridayHours = in.readString();
        saturdayHours = in.readString();
        sundayHours = in.readString();
    }

    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeekDate() {
        return weekDate;
    }

    public void setWeekDate(String weekDate) {
        this.weekDate = weekDate;
    }

    public String getMondayHours() {
        return mondayHours;
    }

    public void setMondayHours(String mondayHours) {
        this.mondayHours = mondayHours;
    }

    public String getTuesdayHours() {
        return tuesdayHours;
    }

    public void setTuesdayHours(String tuesdayHours) {
        this.tuesdayHours = tuesdayHours;
    }

    public String getWednesdayHours() {
        return wednesdayHours;
    }

    public void setWednesdayHours(String wednesdayHours) {
        this.wednesdayHours = wednesdayHours;
    }

    public String getThursdayHours() {
        return thursdayHours;
    }

    public void setThursdayHours(String thursdayHours) {
        this.thursdayHours = thursdayHours;
    }

    public String getFridayHours() {
        return fridayHours;
    }

    public void setFridayHours(String fridayHours) {
        this.fridayHours = fridayHours;
    }

    public String getSaturdayHours() {
        return saturdayHours;
    }

    public void setSaturdayHours(String saturdayHours) {
        this.saturdayHours = saturdayHours;
    }

    public String getSundayHours() {
        return sundayHours;
    }

    public void setSundayHours(String sundayHours) {
        this.sundayHours = sundayHours;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(weekDate);
        parcel.writeString(mondayHours);
        parcel.writeString(tuesdayHours);
        parcel.writeString(wednesdayHours);
        parcel.writeString(thursdayHours);
        parcel.writeString(fridayHours);
        parcel.writeString(saturdayHours);
        parcel.writeString(sundayHours);
    }
}
