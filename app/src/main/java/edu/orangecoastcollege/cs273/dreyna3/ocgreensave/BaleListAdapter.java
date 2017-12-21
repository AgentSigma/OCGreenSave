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
import android.widget.TextView;

import java.util.List;

/**
 * Created by Daniel on 12/7/2017.
 */

public class BaleListAdapter extends ArrayAdapter<Bale> {

    private TextView mNameTextView;
    private TextView mDateTextView;
    private TextView mTypeTextView;
    private TextView mWeightTextView;
    private List<Bale> mBales;
    private Context mContext;
    private LinearLayout mLinearLayout;
    private int mId;

    /**
     * Constructs a BaleListAdapter
     * @param context activity's context
     * @param resource res id of the layout to inflate
     * @param bales list of bales to populate the item
     */
    public BaleListAdapter(@NonNull Context context, int resource, @NonNull List<Bale> bales) {
        super(context, resource, bales);
        mContext = context;
        mId = resource;
        mBales = bales;
    }

    /**
     * Inflates the item properly and sets its views
     * @param position index of the list item
     * @param convertView
     * @param parent
     * @return the list item
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mId, null);
        Bale bale = mBales.get(position);

        mNameTextView = view.findViewById(R.id.baleItemName);
        mDateTextView = view.findViewById(R.id.baleItemDate);
        mTypeTextView = view.findViewById(R.id.baleItemType);
        mWeightTextView = view.findViewById(R.id.baleItemWeight);
        mLinearLayout = view.findViewById(R.id.baleItemLinearLayout);

        mNameTextView.setText(bale.getUser());
        mDateTextView.setText(bale.getDate());
        mTypeTextView.setText(bale.getType());
        mWeightTextView.setText(String.valueOf(bale.getWeight()));

        mLinearLayout.setTag(bale);

        return view;
    }
}
