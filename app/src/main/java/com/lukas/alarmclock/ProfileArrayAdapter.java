package com.lukas.alarmclock;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.sql.Time;
import java.util.ArrayList;

/**
 * Created by Lukas on 26.02.2018.
 */

public class ProfileArrayAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<TimeProfile> list = new ArrayList<TimeProfile>();
    private Context context;

    public ProfileArrayAdapter(ArrayList<TimeProfile> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.profile_list_item1, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView) view.findViewById(R.id.list_item_string);
        listItemText.setText(list.get(position).toString());

        //Handle buttons and add onClickListeners
        ImageButton deleteBtn = (ImageButton) view.findViewById(R.id.delete_btn);
        ImageButton editBtn = (ImageButton) view.findViewById(R.id.edit_btn);
        ImageButton clockBtn = (ImageButton) view.findViewById(R.id.clock_btn);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                MainActivity act = (MainActivity) context;
                TimeProfile tp = list.get(position);
                Intent intent = new Intent(context, ProfileActivity.class);
                intent.putExtra("profile", tp);
                intent.putExtra("position", position);
                act.startProfileActivity(intent);
                notifyDataSetChanged();
            }
        });
        clockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity act = (MainActivity) context;
                TimeProfile tp = list.get(position);
                Intent intent = new Intent(context, ClockActivity.class);
                intent.putExtra("profile", tp);
                act.startClockActivity(intent);
                notifyDataSetChanged();
            }
        });

        return view;
    }

}

