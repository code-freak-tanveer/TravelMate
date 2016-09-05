package com.example.codefreaktanveer.travelmate;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

/**
 * Created by Nipun on 24-Aug-16.
 */
public class MomentListAdapter extends ArrayAdapter<ImageMoment> {
    private Context context;
    private ArrayList<ImageMoment> imageMoments;

    public MomentListAdapter(Context context, ArrayList<ImageMoment> imageMoments) {
        super(context, R.layout.moment_list_contents, imageMoments);
        this.context = context;
        this.imageMoments = imageMoments;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.moment_list_contents,null,true);
        TextView momentNameTextView = (TextView) convertView.findViewById(R.id.momentNameContent);
        ImageView momentImageView = (ImageView) convertView.findViewById(R.id.momentImageContent);
        TextView momentDateTimeTextView = (TextView) convertView.findViewById(R.id.momentDateTime);

//        byte[] outImage=imageMoments.get(position).getImage();
//        ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
//        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        momentImageView.setImageBitmap(BitmapFactory.decodeStream(new ByteArrayInputStream(imageMoments.get(position).getImage())));
        momentNameTextView.setText(imageMoments.get(position).getMoment_name());
        momentDateTimeTextView.setText(imageMoments.get(position).getMoment_date_time());

        return convertView;
    }
}
