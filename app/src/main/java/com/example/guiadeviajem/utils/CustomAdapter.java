package com.example.guiadeviajem.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.guiadeviajem.R;
import com.example.guiadeviajem.database.position.PositionEntity;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<PositionEntity> {
    private List<PositionEntity> positionEntityList;

    public CustomAdapter(Context context, int textView, List<PositionEntity> list) {
        super(context, textView, list);
        this.positionEntityList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView name, latitude, longitude;
        View view = convertView;
        DecimalFormat format;

        if (view == null) {
            Context context = getContext();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.fragment_listview, null);
        }
        PositionEntity positionEntity = positionEntityList.get(position);
        if (positionEntity != null) {
            name = (TextView) view.findViewById(R.id.fragment_text_view);
            latitude = (TextView) view.findViewById(R.id.fragment_latitude);
            longitude = (TextView) view.findViewById(R.id.fragment_longitude);
            name.setText(positionEntity.name);

            format = new DecimalFormat("0.00000");

            latitude.setText(format.format(Double.parseDouble(positionEntity.latitude)));
            longitude.setText(format.format(Double.parseDouble(positionEntity.longitude)));
        }
        return view;
    }

}
