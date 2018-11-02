
package com.example.vicol.labutm;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ImageViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        //recive intent from main activity
        //with bitmap
        Intent intent = getIntent();
        //extract bitmap
        Bitmap bitmap  = intent.getParcelableExtra("BitmapImage");
        //set bitmap as source for imageview
        imageView.setImageBitmap(bitmap);


        //TODO check for erors
    }

    public void back(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
