package com.example.empire.farmshare;

import android.Manifest;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class CreateFarm extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView, displayIV;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    File file;
    Intent GalIntent, CropIntent;
    Uri uri;
    public static final int RequestPermissionCode = 1;
    private Dialog bg_Image_dialog;
    private RelativeLayout imageLayout;

    private EditText locationEd, farmSizeEd, productTypeEd, productCycleEd, durationEd;
    private String location, farmSize, productType, productCycle, duration;
    private Button submitBtn;

    private Dialog messageDialog;

    private ImageView home_btn;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_farm);

        EnableRuntimePermission();

        imageView = (ImageView) findViewById(R.id.imageView);
        home_btn = (ImageView) findViewById(R.id.home_btn);
        imageLayout = (RelativeLayout) findViewById(R.id.imageLayout);
        locationEd = (EditText) findViewById(R.id.locationEd);
        farmSizeEd = (EditText) findViewById(R.id.farmSizeEd);
        productTypeEd = (EditText) findViewById(R.id.productTypeEd);

        productCycleEd = (EditText) findViewById(R.id.productCycleEd);
        durationEd = (EditText) findViewById(R.id.durationEd);

        submitBtn = (Button) findViewById(R.id.submitBtn);
        backBtn = (ImageView) findViewById(R.id.backBtn);

        imageView.setOnClickListener(this);
        submitBtn.setOnClickListener(this);
        home_btn.setOnClickListener(this);
        backBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView:
                bgChooserMethod();
                break;
            case R.id.submitBtn:
                submitInfo();
                break;
            case R.id.home_btn:
                navToNext(Home2.class);
                break;
            case R.id.backBtn:
                onBackPressed();
                break;
            default:
                break;
        }
    }

    public void navToNext(Class newIntent) {
        Intent intent = new Intent(CreateFarm.this, newIntent);
        startActivity(intent);
    }

    private void submitInfo() {

        location = locationEd.getText().toString().trim();
        farmSize = farmSizeEd.getText().toString().trim();
        productType = productTypeEd.getText().toString().trim();
        productCycle = productCycleEd.getText().toString().trim();
        duration = durationEd.getText().toString().trim();

        if (location.isEmpty() || equals(null)) {
            stateMessage("Location is empty");
        } else if (farmSize.isEmpty() || equals(null)) {
            stateMessage("Farm size is empty");
        } else if (productType.isEmpty() || equals(null)) {
            stateMessage("Product Type is empty");
        } else if (productCycle.isEmpty() || equals(null)) {
            stateMessage("Product cycle is empty");
        } else if (duration.isEmpty() || equals(null)) {
            stateMessage("Duration is empty");
        } else {
            stateMessage("You have added a new farm successfully !!!");
        }

    }

    public void stateMessage(final String message) {
        messageDialog = new Dialog(CreateFarm.this);
        messageDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        messageDialog.setContentView(R.layout.message_layout);

        messageDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
//        messageDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        messageDialog.getWindow().setGravity(Gravity.CENTER);
        messageDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        messageDialog.setCanceledOnTouchOutside(true);
        messageDialog.show();

        TextView messageTV = (TextView) messageDialog.findViewById(R.id.messageTV);
        TextView closeBtn = (TextView) messageDialog.findViewById(R.id.dialogCloseTv);

        messageTV.setText(message);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messageDialog.cancel();
            }
        });

    }

    private void success() {

    }


    private void getImageFromCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void getImageFromGallery() {
        try {
            GalIntent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            startActivityForResult(Intent.createChooser(GalIntent, "Select Image From Gallery"), 2);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(CreateFarm.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//            displayIV.setImageBitmap(imageBitmap);
//        }
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0 && resultCode == RESULT_OK) {

            ImageCropFunction();

        } else if (requestCode == 2) {

            if (data != null) {

                uri = data.getData();

                ImageCropFunction();


            }
        } else if (requestCode == 1) {

            if (data != null) {

                Bundle bundle = data.getExtras();

                Bitmap bitmap = bundle.getParcelable("data");
//                displayIV.setImageBitmap(bitmap);

                Drawable dr = new BitmapDrawable(bitmap);
                imageLayout.setBackgroundDrawable(dr);

            }
        }
    }

    public void ImageCropFunction() {

        // Image Crop Code
        try {
            CropIntent = new Intent("com.android.camera.action.CROP");

            CropIntent.setDataAndType(uri, "image/*");

            CropIntent.putExtra("crop", "true");
            CropIntent.putExtra("outputX", 180);
            CropIntent.putExtra("outputY", 90);
            CropIntent.putExtra("aspectX", 4);
            CropIntent.putExtra("aspectY", 2);
            CropIntent.putExtra("scaleUpIfNeeded", true);
            CropIntent.putExtra("return-data", true);

            startActivityForResult(CropIntent, 1);

        } catch (ActivityNotFoundException e) {

        }
    }


    public void bgChooserMethod() {

        bg_Image_dialog = new Dialog(CreateFarm.this);
        bg_Image_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        bg_Image_dialog.setContentView(R.layout.choose_image);

        bg_Image_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        bg_Image_dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        bg_Image_dialog.getWindow().setGravity(Gravity.CENTER);
        bg_Image_dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        bg_Image_dialog.setCanceledOnTouchOutside(true);
        bg_Image_dialog.show();

        Button bg_package_btn = (Button) bg_Image_dialog.findViewById(R.id.bg_package_btn);
        Button photo_gallery_btn = (Button) bg_Image_dialog.findViewById(R.id.photo_gallery_btn);
        Button bg_cancel_btn = (Button) bg_Image_dialog.findViewById(R.id.bg_cancel_btn);

        bg_package_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImageFromCamera();
                bg_Image_dialog.cancel();
            }
        });

        photo_gallery_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImageFromGallery();
                bg_Image_dialog.cancel();
            }
        });

        bg_cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bg_Image_dialog.cancel();
            }
        });

    }


    public void EnableRuntimePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(CreateFarm.this,
                Manifest.permission.CAMERA)) {

            Toast.makeText(CreateFarm.this, "CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(CreateFarm.this, new String[]{
                    Manifest.permission.CAMERA}, RequestPermissionCode);

        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case RequestPermissionCode:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

//                    Toast.makeText(Home.this,"Permission Granted.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(CreateFarm.this, "Permission not Granted", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }


}
