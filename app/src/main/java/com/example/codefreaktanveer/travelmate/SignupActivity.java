package com.example.codefreaktanveer.travelmate;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.android.photoutil.ImageLoader;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.DataMiddleware;
import model.User;

public class SignupActivity extends AppCompatActivity {
    private EditText fullName;
    private EditText userName;
    private EditText password;
    private Button signupButton;
    private DataMiddleware db;
    private static String imagePath;
    //private EditText email;


    private static final int GALLERY_REQUST =12223 ;
    private CameraPhoto cameraPhoto;
    private GalleryPhoto galleryPhoto;
    private final int CAMERA_REQUEST=13323;
    private ImageView imgProfile;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        imgProfile= (ImageView) findViewById(R.id.imgProfile);
        registerForContextMenu(imgProfile);
        cameraPhoto=new CameraPhoto(getApplicationContext());
        galleryPhoto=new GalleryPhoto(getApplicationContext());

        fullName= (EditText) findViewById(R.id.txtFullName);
        userName= (EditText) findViewById(R.id.txtUserName);
        password= (EditText) findViewById(R.id.txtPassword);
        signupButton= (Button) findViewById(R.id.signupButton);
        db=new DataMiddleware(this);
        verifyStoragePermissions(this);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if(v.getId()==R.id.imgProfile){
            MenuInflater menuInflater=getMenuInflater();
            menuInflater.inflate(R.menu.option_menu,menu);
        }

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        int id=item.getItemId();
        switch (id){
            case  R.id.optionCamera:
                try {
                    startActivityForResult(cameraPhoto.takePhotoIntent(),CAMERA_REQUEST);
                    cameraPhoto.addToGallery();

                } catch (IOException e) {
                    Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;
            case  R.id.optionGallary:
                startActivityForResult(galleryPhoto.openGalleryIntent(),GALLERY_REQUST);
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(resultCode==RESULT_OK){
           if(requestCode==CAMERA_REQUEST){
               imagePath=cameraPhoto.getPhotoPath();
               try {
                   Bitmap image= ImageLoader.init().from(imagePath).requestSize(128,128).getBitmap();
                    imgProfile.setImageBitmap(image);
               } catch (FileNotFoundException e) {
                   Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
               }
           }else if(requestCode==GALLERY_REQUST){
               Uri uri=data.getData();
               galleryPhoto.setPhotoUri(uri);
               imagePath=galleryPhoto.getPath();
               try {
                   Bitmap image= ImageLoader.init().from(imagePath).requestSize(128,128).getBitmap();
                   imgProfile.setImageBitmap(image);
               } catch (FileNotFoundException e) {
                   Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
               }
           }
       }
    }
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have read or write permission
        int writePermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int readPermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
        int camerPermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);

        if (camerPermission!=PackageManager.PERMISSION_GRANTED||writePermission != PackageManager.PERMISSION_GRANTED || readPermission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE

            );
        }
    }
    public void signup() {

        if (!validate()) {
            onSignupFailed();
            return;
        }

        signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        final String name = fullName.getText().toString();
        final String email = userName.getText().toString();
        final String passwordString = password.getText().toString();

        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        User user=new User("Regular User",passwordString,name,imagePath,email);
                        if(db.registerUser(user)){
                            Toast.makeText(SignupActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            userName.setText("");
                            fullName.setText("");
                            password.setText("");
                            imgProfile.setImageBitmap(null);
                        }
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = fullName.getText().toString();
        String email = userName.getText().toString();
        String passwordString = password.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            fullName.setError("at least 3 characters");
            valid = false;
        } else {
            fullName.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            userName.setError("enter a valid email address");
            valid = false;
        } else {
            userName.setError(null);
        }

        if (passwordString.isEmpty() || password.length() < 4 || password.length() > 10) {
            password.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            password.setError(null);
        }

        return valid;
    }
    public void registerUser(View view) {
    signup();
    }

    public void navigateToSignin(View view) {
        Intent signinIntent=new Intent(SignupActivity.this,LoginActivity.class);
        startActivity(signinIntent);
    }
}
