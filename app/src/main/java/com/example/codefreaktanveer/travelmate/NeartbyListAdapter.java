package com.example.codefreaktanveer.travelmate;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import model.LocationData;


/**
 * Created by Code Freak Tanveer on 22/08/2016.
 */
public class NeartbyListAdapter extends ArrayAdapter<LocationData.Result> {
    private  Context context;
    private  ArrayList<LocationData.Result> locationList;

    private static class ViewHolder{
        TextView txtLocationName;
        TextView txtdistance;
        TextView txtlocationType;
        TextView txtOpened;private RatingBar ratingBar;

    }

    public NeartbyListAdapter(Context context, ArrayList<LocationData.Result> locationlist) {

        super(context, R.layout.nearby_list, locationlist);
        this.context=context;
        this.locationList=locationlist;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view=inflater.inflate(R.layout.nearby_list,null);
            convertView=view;
            viewHolder=new ViewHolder();
            viewHolder.txtLocationName= (TextView) convertView.findViewById(R.id.txtLocationName);
            viewHolder.txtdistance= (TextView) convertView.findViewById(R.id.txtDistance);
            viewHolder.txtlocationType= (TextView) convertView.findViewById(R.id.txtLocationType);
            viewHolder.txtOpened= (TextView) convertView.findViewById(R.id.txtOpenStatus);
            viewHolder.ratingBar= (RatingBar) convertView.findViewById(R.id.ratingBar);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        LocationData.Result result= locationList.get(position);
        Double getRating=result.getRating();
        if(getRating==null){
            getRating=0.0;
        }
        float rating=getRating.floatValue();
//        LocationData.Result.OpeningHours openingHour=result.getOpeningHours();
//        Boolean placeOpened=new Boolean(openingHour.getOpenNow());
//        Log.e("AShE NA",placeOpened+"");
//
//        if(placeOpened==null){
//            placeOpened=false;
//        }


        boolean opened=true;
        viewHolder.txtOpened.setText("Closed");
        if(opened){
            viewHolder.txtOpened.setText("Open");
        }
        ArrayList<String> typeList= (ArrayList<String>) result.getTypes();
        String placeName=result.getName();
        viewHolder.txtLocationName.setText(placeName);
        viewHolder.txtlocationType.setText(typeList.get(0));
        viewHolder.txtdistance.setText("");
        viewHolder.ratingBar.setRating(rating);



        return convertView;
    }
}
