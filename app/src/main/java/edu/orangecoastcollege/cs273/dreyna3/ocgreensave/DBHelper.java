package edu.orangecoastcollege.cs273.dreyna3.ocgreensave;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 12/1/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "OCGsStaff";
    private static final int DATABASE_VERSION = 6;

    // For Bale Tracking
    private static final String BALE_TABLE = "BaleHistory";
    private static final String BALE_KEY_FIELD_ID = "_id";
    private static final String FIELD_BALEUSERNAME = "username";
    private static final String FIELD_DATE = "date";
    private static final String FIELD_BALETYPE= "type";
    private static final String FIELD_BALEWEIGHT = "weight";

    // For Employee Schedule Tracking
    private static final String EMPLOYEE_TABLE = "EmployeeSchedules";
    private static final String EMPLOYEE_KEY_FIELD_ID = "_id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_WEEKSTARTDAY = "week_start_day";
    // Hours for each day in that week
    private static final String FIELD_MONDAY = "monday";
    private static final String FIELD_TUESDAY = "tuesday";
    private static final String FIELD_WEDNESDAY = "wednesday";
    private static final String FIELD_THURSDAY = "thursday";
    private static final String FIELD_FRIDAY = "friday";
    private static final String FIELD_SATURDAY = "saturday";
    private static final String FIELD_SUNDAY = "sunday";


    /**
     * Constructs a database helper
     * @param context context of the activity
     */
    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Creates the database
     * @param sqLiteDatabase the SQLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Bale Table creation
        String table = "CREATE TABLE " + BALE_TABLE + "("
                + BALE_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_BALEUSERNAME + " TEXT, "
                + FIELD_DATE + " TEXT, "
                + FIELD_BALETYPE + " TEXT, "
                + FIELD_BALEWEIGHT + " REAL)";
        sqLiteDatabase.execSQL(table);

        // Employee Schedule creation
        table = "CREATE TABLE " + EMPLOYEE_TABLE + "("
                + EMPLOYEE_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_NAME + " TEXT, "
                + FIELD_WEEKSTARTDAY + " TEXT, "
                + FIELD_MONDAY + " TEXT, "
                + FIELD_TUESDAY + " TEXT, "
                + FIELD_WEDNESDAY + " TEXT, "
                + FIELD_THURSDAY + " TEXT, "
                + FIELD_FRIDAY + " TEXT, "
                + FIELD_SATURDAY + " TEXT, "
                + FIELD_SUNDAY + " TEXT)";
        sqLiteDatabase.execSQL(table);
    }

    /**
     * Upgrades the tables
     * @param sqLiteDatabase the SQLiteDatabase
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BALE_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EMPLOYEE_TABLE);
        onCreate(sqLiteDatabase);
    }

    ////////////DB FUNCTIONS////////////

    /**
     * Clears the databases for debug purposes
     */
    public void clearDatabases(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(BALE_TABLE, null, null);
        db.delete(EMPLOYEE_TABLE, null, null);
    }

    /**
     * Adds a bale to the database
     * @param bale bale to add
     */
    public void addBale(Bale bale){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_BALEUSERNAME, bale.getUser());
        values.put(FIELD_DATE, bale.getDate());
        values.put(FIELD_BALETYPE, bale.getType());
        values.put(FIELD_BALEWEIGHT, bale.getWeight());

        db.insert(BALE_TABLE, null, values);
        db.close();;
    }

    /**
     * Adds an employee to the datbase
     * @param employee employee to add
     */
    public void addEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_NAME, employee.getName());
        values.put(FIELD_WEEKSTARTDAY, employee.getWeekDate());
        values.put(FIELD_MONDAY, employee.getMondayHours());
        values.put(FIELD_TUESDAY, employee.getTuesdayHours());
        values.put(FIELD_WEDNESDAY, employee.getWednesdayHours());
        values.put(FIELD_THURSDAY, employee.getThursdayHours());
        values.put(FIELD_FRIDAY, employee.getFridayHours());
        values.put(FIELD_SATURDAY, employee.getSaturdayHours());
        values.put(FIELD_SUNDAY, employee.getSundayHours());

        db.insert(EMPLOYEE_TABLE, null, values);
        db.close();
    }

    /**
     * Deletes a given employee
     * @param employee employee to delete
     */
    public void deleteEmployee(Employee employee){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(EMPLOYEE_TABLE, EMPLOYEE_KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(employee.getId())});
        db.close();
    }

    /**
     * Updates a given employee in the database
     * @param employee employee to update
     */
    public void updateEmployee(Employee employee){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_NAME, employee.getName());
        values.put(FIELD_WEEKSTARTDAY, employee.getWeekDate());
        values.put(FIELD_MONDAY, employee.getMondayHours());
        values.put(FIELD_TUESDAY, employee.getTuesdayHours());
        values.put(FIELD_WEDNESDAY, employee.getWednesdayHours());
        values.put(FIELD_THURSDAY, employee.getThursdayHours());
        values.put(FIELD_FRIDAY, employee.getFridayHours());
        values.put(FIELD_SATURDAY, employee.getSaturdayHours());
        values.put(FIELD_SUNDAY, employee.getSundayHours());

        db.update(EMPLOYEE_TABLE, values, EMPLOYEE_KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(employee.getId())});
        db.close();
    }

    /**
     * Returns the bale table in the database
     * @return an arraylist of bales
     */
    public List<Bale> getAllBales(){
        List<Bale> balesList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(
                BALE_TABLE,
                new String[]{
                        BALE_KEY_FIELD_ID,
                        FIELD_BALEUSERNAME,
                        FIELD_DATE,
                        FIELD_BALETYPE,
                        FIELD_BALEWEIGHT
                }, null, null, null, null, null
        );

        if (c.moveToFirst()){
            do{
                Bale bale = new Bale(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getDouble(4)
                );
                balesList.add(bale);
            } while (c.moveToNext());
        }

        c.close();
        db.close();
        return balesList;
    }

    /**
     * Returns the employee table in the database
     * @return arraylist of employees
     */
    public List<Employee> getAllEmployees(){
        List<Employee> employeeList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.query(
                EMPLOYEE_TABLE,
                new String[]{
                        BALE_KEY_FIELD_ID,
                        FIELD_NAME,
                        FIELD_WEEKSTARTDAY,
                        FIELD_MONDAY,
                        FIELD_TUESDAY,
                        FIELD_WEDNESDAY,
                        FIELD_THURSDAY,
                        FIELD_FRIDAY,
                        FIELD_SATURDAY,
                        FIELD_SUNDAY
                }, null, null, null, null, null
        );

        if (c.moveToFirst()){
            do{
                Employee employee = new Employee(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4),
                        c.getString(5),
                        c.getString(6),
                        c.getString(7),
                        c.getString(8),
                        c.getString(9)
                );
                employeeList.add(employee);
            } while (c.moveToNext());
        }

        c.close();
        db.close();
        return employeeList;
    }

    public void updateBale(Bale bale) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_BALEUSERNAME, bale.getUser());
        values.put(FIELD_DATE, bale.getDate());
        values.put(FIELD_BALETYPE, bale.getType());
        values.put(FIELD_BALEWEIGHT, bale.getWeight());

        db.update(BALE_TABLE, values, BALE_KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(bale.getId())});
        db.close();
    }

    public void deleteBale(Bale bale) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(BALE_TABLE, BALE_KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(bale.getId())});
        db.close();
    }

    public void deleteBalesByType(List<Bale> baleList){
        SQLiteDatabase db = this.getWritableDatabase();

        for (Bale b : baleList){
            db.delete(BALE_TABLE, BALE_KEY_FIELD_ID + " = ?",
                    new String[]{String.valueOf(b.getId())});
        }
        db.close();
    }
}
