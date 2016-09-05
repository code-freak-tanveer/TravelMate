package com.example.codefreaktanveer.travelmate;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codefreaktanveer.travelmate.R;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import model.WeatherApi;
import retrofit2.Callback;

/**
 * Created by Code Freak Tanveer on 21/08/2016.
 */
public class WeatherListAdapdet extends ArrayAdapter<WeatherApi.Daily.Datum_> {



    static  class ViewHolder{
        TextView txtMinTemp;
       TextView txtMaxTemp;
        TextView txthumidity;
        TextView txtrain;
        TextView txtDay;
        ImageView imgWeather;

    }
    private final ArrayList<WeatherApi.Daily.Datum_> weatherList;
    private Context context;
    public WeatherListAdapdet(Context context, ArrayList<WeatherApi.Daily.Datum_> weatherList) {
        super(context, R.layout.forcast_list, weatherList);
        this.context=context;
        this.weatherList=weatherList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view=inflater.inflate(R.layout.forcast_list,null);
            convertView=view;
            viewHolder.txtMinTemp= (TextView) convertView.findViewById(R.id.txtMinTemp);
            viewHolder.txtMaxTemp= (TextView) convertView.findViewById(R.id.txtMaxTemp);
            viewHolder.txthumidity= (TextView) convertView.findViewById(R.id.txtHumidity);
            viewHolder.txtrain= (TextView) convertView.findViewById(R.id.txtRain);
            viewHolder.txtDay= (TextView) convertView.findViewById(R.id.txtDay);
            viewHolder.imgWeather= (ImageView) convertView.findViewById(R.id.imgWeather);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();

        }
        WeatherApi.Daily.Datum_ datum_=weatherList.get(position);
            String summery=datum_.getSummary();
            String MaxTemp=""+datum_.getTemperatureMax()+"\u00b0F";
            String MinTemp=""+datum_.getTemperatureMin()+"\u00b0F";
            NumberFormat formatter = new DecimalFormat("#0.00");
        String humidity=""+(formatter.format(datum_.getHumidity()*100))+"%";
            String rain=""+(formatter.format(datum_.getPrecipProbability()*100))+"%";
            int day=datum_.getTime();
        Log.e("DAY:    ",day+"");
        String datestr=String.valueOf(day*1000);
        Date date = new Date(Long.parseLong(datestr));
        SimpleDateFormat sdf = new SimpleDateFormat("EE");
        String formattedDate = sdf.format(date);


            viewHolder.txtMinTemp.setText(String.valueOf(MinTemp));
            viewHolder.txtMaxTemp.setText(String.valueOf(MaxTemp));
            viewHolder.txthumidity.setText(String.valueOf(humidity));
            viewHolder.txtrain.setText(String.valueOf(rain));
            viewHolder.txtDay.setText(formattedDate);

        return convertView;
    }
}
