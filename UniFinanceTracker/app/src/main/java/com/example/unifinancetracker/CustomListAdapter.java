package com.example.unifinancetracker;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListAdapter extends ArrayAdapter {
    //to reference the Activity
    private final Activity context;

    //to store the animal images
    private final Expense[] showlist;

    public CustomListAdapter(Activity context, Expense[] nameArrayParam){

        super(context,R.layout.listview_row , nameArrayParam);

        this.context=context;
        this.showlist = nameArrayParam;



    }
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listview_row, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView nameTextField = (TextView) rowView.findViewById(R.id.nameTextViewID);
        TextView infoTextField = (TextView) rowView.findViewById(R.id.infoTextViewID);
        TextView start = (TextView) rowView.findViewById(R.id.startd);
        TextView end = (TextView) rowView.findViewById(R.id.endd);
        Button btn = (Button) rowView.findViewById(R.id.delBtn);
        Expense a = showlist[position];
        //this code sets the values of the objects to values from the arrays
        nameTextField.setText(a.category);
        infoTextField.setText(a.amount);
        start.setText(a.datetime);
        return rowView;

    }
}
