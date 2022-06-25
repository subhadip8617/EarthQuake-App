package com.example.earthquake;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class QuakeAdapter extends ArrayAdapter<QuakeClass> {
    public QuakeAdapter(@NonNull Context context, int resource, @NonNull ArrayList<QuakeClass> objects) {
        super(context, resource, objects);
    }

//    private int getMagnitudeColor(float mag){
//        int col = ContextCompat.getColor(getContext(),R.color.magnitude1);
//        if(mag <= 2){
//            col = ContextCompat.getColor(getContext(),R.color.magnitude1);
//        }
//        else if (mag <= 3){
//            col = ContextCompat.getColor(getContext(),R.color.magnitude2);
//        }
//        else if (mag <= 4){
//            col = ContextCompat.getColor(getContext(),R.color.magnitude3);
//        }
//        else if (mag <= 5){
//            col = ContextCompat.getColor(getContext(),R.color.magnitude4);
//        }
//        else if (mag <= 6){
//            col = ContextCompat.getColor(getContext(),R.color.magnitude5);
//        }
//        else if (mag <= 7){
//            col = ContextCompat.getColor(getContext(),R.color.magnitude6);
//        }
//        else if (mag <= 8){
//            col = ContextCompat.getColor(getContext(),R.color.magnitude7);
//        }
//        else if (mag <= 9){
//            col = ContextCompat.getColor(getContext(),R.color.magnitude8);
//        }
//        else if (mag <= 10){
//            col = ContextCompat.getColor(getContext(),R.color.magnitude9);
//        }
//        else{
//            col = ContextCompat.getColor(getContext(),R.color.magnitude10plus);
//        }
//        return col;
//    }

    private int getMagnitudeColor(float magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        QuakeClass item = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.shapecell,parent,false);
        }

        TextView mag = convertView.findViewById(R.id.magnitude);
        TextView cord= convertView.findViewById(R.id.cord);
        TextView location = convertView.findViewById(R.id.location);
        TextView date = convertView.findViewById(R.id.date);
        TextView time = convertView.findViewById(R.id.time);
        String place = item.getLoaction();
        String parts[] = place.split(" of ");
        mag.setText(item.getMag().toString());
        if(parts.length == 1){
            cord.setText("Near the");
            location.setText(parts[0]);
        }
        else{
            cord.setText(parts[0]+" of");
            location.setText(parts[1]);
        }
        date.setText(item.getDate());
        time.setText(item.getTime());

        GradientDrawable gradientDrawable = (GradientDrawable) mag.getBackground();
        int col = getMagnitudeColor(item.getMag());
        gradientDrawable.setColor(col);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Intent.ACTION_VIEW);
                it.setData(Uri.parse(item.getUrl()));
                getContext().startActivity(it);
            }
        });

        return convertView;
    }
}
