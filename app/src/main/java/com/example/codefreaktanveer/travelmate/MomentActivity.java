package com.example.codefreaktanveer.travelmate;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import model.DataMiddleware;

public class MomentActivity extends AppCompatActivity {

    int event_id;
    ImageMoment imageMoment;
    private static final int CAMERA_REQUEST = 1;
    ImageView imageShowImageView;
    ImageButton cameraBtnIdImageBtn;
    DataMiddleware databaseOperations;
    EditText momentNameEditTxt;
    Button saveMomentBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moment);
        setTitle("Create Moment");

        event_id = getIntent().getIntExtra("event_id", event_id);
        imageShowImageView = (ImageView) findViewById(R.id.imageShow);
        cameraBtnIdImageBtn = (ImageButton) findViewById(R.id.cameraBtnId);
        momentNameEditTxt = (EditText) findViewById(R.id.momentName);
//        saveMomentBtn = (Button) findViewById(R.id.saveMoment);


        if(!hasCamera())
            cameraBtnIdImageBtn.setEnabled(false);
    }

    private boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    public void cameraBtn(View view) {

        String momentNm = momentNameEditTxt.getText().toString();

        if (momentNm.isEmpty()) {
            Toast.makeText(MomentActivity.this, "Enter Moment Name Please", Toast.LENGTH_SHORT).show();

        }
        if (momentNm.isEmpty() == false) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA_REQUEST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String current_date = sdf.format(c.getTime());

        databaseOperations=new DataMiddleware(this);

        if(requestCode == CAMERA_REQUEST && resultCode == RESULT_OK){
            String moment_name = momentNameEditTxt.getText().toString();
//            Toast.makeText(MomentActivity.this, data+"", Toast.LENGTH_LONG).show();
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");
            imageShowImageView.setImageBitmap(photo);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArrayImage = stream.toByteArray();



            imageMoment = new ImageMoment(event_id, moment_name, byteArrayImage, current_date);
            databaseOperations.addMoment(imageMoment);
            Toast.makeText(MomentActivity.this, "Moment Successfully Archived", Toast.LENGTH_SHORT).show();
            momentNameEditTxt.setText("");
        }
    }


    public void onBackPressed() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int choice) {
                switch (choice) {
                    case DialogInterface.BUTTON_POSITIVE:
                        Intent intent = new Intent(getApplicationContext(), EventDetailActivity.class);
                        intent.putExtra("event_id", event_id);
                        startActivity(intent);
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you Want to go Back?")
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

}
