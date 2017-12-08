package edu.orangecoastcollege.cs273.dreyna3.ocgreensave;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Daniel on 12/7/2017.
 */

public class ScheduleListAdapter extends ArrayAdapter<Employee>{
    private Context mContext;
    private int rId;
    private List<Employee> mEmployees;

    private LinearLayout mLinearLayout;
    private TextView mNameTextView;
    private TextView mWeekDateTextView;
    private TextView mMonday;
    private TextView mTuesday;
    private TextView mWednesday;
    private TextView mThursday;
    private TextView mFriday;
    private TextView mSaturday;
    private TextView mSunday;

    /**
     * Constructs a ScheduleListAdapter
     * @param context current activity context
     * @param resource res id of the layout to inflate
     * @param objects list of employee objects
     */
    public ScheduleListAdapter(@NonNull Context context, int resource, @NonNull List<Employee> objects) {
        super(context, resource, objects);
        mContext = context;
        rId = resource;
        mEmployees = objects;
    }

    /**
     * Properly inflates the list item with info on the associated employee
     * @param position index of the list item
     * @param convertView
     * @param parent
     * @return the list item view
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(rId, null);
        Employee employee = mEmployees.get(position);

        mLinearLayout = (LinearLayout) view.findViewById(R.id.scheduleItemLayout);
        mWeekDateTextView = (TextView) view.findViewById(R.id.scheduleItemWeekDate);
        mNameTextView = (TextView) view.findViewById(R.id.scheduleItemName);
        mMonday = (TextView) view.findViewById(R.id.scheduleItemMondayHours);
        mTuesday = (TextView) view.findViewById(R.id.scheduleItemTuesdayHours);
        mWednesday = (TextView) view.findViewById(R.id.scheduleItemWednesdayHours);
        mThursday = (TextView) view.findViewById(R.id.scheduleItemThursdayHours);
        mSaturday = (TextView) view.findViewById(R.id.scheduleItemSaturdayHours);
        mSunday = (TextView) view.findViewById(R.id.scheduleItemSundayHours);
        mFriday = (TextView) view.findViewById(R.id.scheduleItemFridayHours);

        mNameTextView.setText(employee.getName());
        mWeekDateTextView.setText(employee.getWeekDate());
        mMonday.setText(employee.getMondayHours());
        mTuesday.setText(employee.getTuesdayHours());
        mWednesday.setText(employee.getWednesdayHours());
        mThursday.setText(employee.getThursdayHours());
        mFriday.setText(employee.getFridayHours());
        mSaturday.setText(employee.getSaturdayHours());
        mSunday.setText(employee.getSundayHours());

        mLinearLayout.setTag(employee);
        return view;
    }
}
