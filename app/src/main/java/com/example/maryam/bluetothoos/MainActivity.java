package com.example.maryam.bluetothoos;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button send;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send = findViewById(R.id.send);
        imageView =  findViewById(R.id.image);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send();
            }
        });
    }
    private void send(){
        imageView.buildDrawingCache();
        Bitmap bm = imageView.getDrawingCache();
        File f = new File(Environment.getExternalStorageDirectory()+"/temp_1.jpg");

        try {
            //sakhte file va ijade bitmap image dakhele an
            f.createNewFile();
            FileOutputStream out = new FileOutputStream(Environment.getExternalStorageDirectory()+"/temp_1.jpg");
            bm.compress(Bitmap.CompressFormat.JPEG,90,out);
            out.close();

        }catch (IOException e){
            e.printStackTrace();
        }


        //ersal az tariqe bluetooth
        File source = new File(Environment.getExternalStorageDirectory()+"/temp_1.jpg");
        Intent i = new Intent();
        i.setAction(Intent.ACTION_SEND);
        i.setType("image/jpeg");
        i.putExtra(Intent.EXTRA_STREAM , Uri.fromFile(source));
        startActivity(i);


    }
}
