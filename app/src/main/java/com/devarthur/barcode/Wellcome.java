package com.devarthur.barcode;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Wellcome extends AppCompatActivity {

    private static final int RQ_PERMISSIONS_MEDIA = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome);

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //Permission not Granted so we will be educated enough to ask for permission
            askForMediaPermission();

            return;
        }
        else{
            //If we have all the necessary permissions, then we can go on...
            goToQrCodeReader();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){

            case RQ_PERMISSIONS_MEDIA: {

                if(grantResults.length > 0 ){
                    goToQrCodeReader();

                }else{
                    Toast.makeText(getApplicationContext(), ("Erro de permissão") , Toast.LENGTH_SHORT).show();
                }
                return;

            }


        }
    }

    private void askForMediaPermission(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA
                    }, RQ_PERMISSIONS_MEDIA);
        }
    }

    private void goToQrCodeReader(){
        Intent intent = new Intent(Wellcome.this, QRCodeReader.class);
        startActivity(intent);
    }
}
