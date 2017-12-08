package edu.orangecoastcollege.cs273.dreyna3.ocgreensave;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Daniel on 12/1/2017.
 * Employee Object
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

    /**
     * Constructs an Employee object for the database
     * @param id id in the database
     * @param name employee name
     * @param weekDate employee working week date
     * @param mondayHours monday hours
     * @param tuesdayHours tuesday hours
     * @param wednesdayHours wednesday hours
     * @param thursdayHours thursday horus
     * @param fridayHours friday hours
     * @param saturdayHours saturday hours
     * @param sundayHours sunday hours
     */
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

    /**
     * Constructs an Employee object
     * @param name employee name
     * @param weekDate employee working week date
     * @param mondayHours monday hours
     * @param tuesdayHours tuesday hours
     * @param wednesdayHours wednesday hours
     * @param thursdayHours thursday horus
     * @param fridayHours friday hours
     * @param saturdayHours saturday hours
     * @param sundayHours sunday hours
     */
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

    /**
     * Constructs a Parcelable Employee object
     * @param in parcel
     */
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

    /**
     * Creates the parcel for Employee
     */
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

    /**
     * Returns database id of the employee
     * @return id of employee
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of the employee
     * @return name of employee
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the nme of the employee
     * @param name name of employee
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the week date of the employee
     * @return week date
     */
    public String getWeekDate() {
        return weekDate;
    }

    /**
     * Sets the week date of the employee
     * @param weekDate week date
     */
    public void setWeekDate(String weekDate) {
        this.weekDate = weekDate;
    }

    /**
     * Returns monday hours
     * @return monday hours
     */
    public String getMondayHours() {
        return mondayHours;
    }

    /**
     * Sets monday hours
     * @param mondayHours monday hours
     */
    public void setMondayHours(String mondayHours) {
        this.mondayHours = mondayHours;
    }

    /**
     * Gets tuesday hours
     * @return tuesday hours
     */
    public String getTuesdayHours() {
        return tuesdayHours;
    }

    /**
     * Sets tuesday hours
     * @param tuesdayHours tuesday hours
     */
    public void setTuesdayHours(String tuesdayHours) {
        this.tuesdayHours = tuesdayHours;
    }

    /**
     * Gets wednesday hours
     * @return wednesday hours
     */
    public String getWednesdayHours() {
        return wednesdayHours;
    }

    /**
     * sets wednesday hours
     * @param wednesdayHours wednesday hours
     */
    public void setWednesdayHours(String wednesdayHours) {
        this.wednesdayHours = wednesdayHours;
    }

    /**
     * Gets thursday hours
     * @return thursday hours
     */
    public String getThursdayHours() {
        return thursdayHours;
    }

    /**
     * Sets thursday hours
     * @param thursdayHours thursday hours
     */
    public void setThursdayHours(String thursdayHours) {
        this.thursdayHours = thursdayHours;
    }

    /**
     * Gets friday hours
     * @return friday hours
     */
    public String getFridayHours() {
        return fridayHours;
    }

    /**
     * Sets friday hours
     * @param fridayHours friday hours
     */
    public void setFridayHours(String fridayHours) {
        this.fridayHours = fridayHours;
    }

    /**
     * Gets saturday hours
     * @return saturday hours
     */
    public String getSaturdayHours() {
        return saturdayHours;
    }

    /**
     * Sets saturday hours
     * @param saturdayHours saturday hours
     */
    public void setSaturdayHours(String saturdayHours) {
        this.saturdayHours = saturdayHours;
    }

    /**
     * gets sunday hours
     * @return sunday hours
     */
    public String getSundayHours() {
        return sundayHours;
    }

    /**
     * Sets sunday hours
     * @param sundayHours sunday hours
     */
    public void setSundayHours(String sundayHours) {
        this.sundayHours = sundayHours;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Writes to the parcel for Employee
     * @param parcel
     * @param i
     */
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
