package com.example.vicol.labutm;

import android.app.Notification;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.vicol.labutm.service.notifyService.CHANNEL_1_ID;


public class MainActivity extends AppCompatActivity {

    NotificationManagerCompat notificationManagerCompat ;
    private NotificationManagerCompat notificationManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManager = NotificationManagerCompat.from(this);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //extract bit map from  camerafoo()
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        //create new intent
        Intent intent = new Intent(this,ImageViewActivity.class);
        //put bitmap in intent
        intent.putExtra("BitmapImage",bitmap);
        //redirect
        startActivity(intent);




//        Toast toast = Toast.makeText(getApplicationContext(),"activity result",Toast.LENGTH_LONG);
//        toast.show();
    }

    public void  sendNotification(View view ){

        EditText title,content;
        title = (EditText)findViewById(R.id.title);
        content = (EditText)findViewById(R.id.content);

        String strTitle,strContent;
        strTitle = title.getText().toString();
        strContent = content.getText().toString();

        if(strTitle.equals("")){strTitle = "empty title";}
        if(strContent.equals("")){strContent ="empty content";}


        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_alert)
                .setContentTitle(strTitle)
                .setContentText(strContent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();


        notificationManager.notify(1, notification);
    }

    public void camerafoo(View view){

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            takePictureIntent.putExtra("android.intent.extras.CAMERA_FACING", 1);
//            takePictureIntent.putExtra("android.intent.extras.CA")
            startActivityForResult(takePictureIntent, 1);
    }
    }




    public void search(View view){
        EditText search = (EditText)findViewById(R.id.searchText);
        String link,strSearch;

        link = "http://www.google.com/search?q=";
        strSearch = search.getText().toString();

        //check if user input is not empty
        if(strSearch.equals("")){
            Toast toast = Toast.makeText(getApplicationContext(),"fill the search",Toast.LENGTH_LONG);
            toast.show();
        }
        else {
            //remove white spaces from string and replalce with +
            strSearch = strSearch.replaceAll("\\s+", "+");
            //concat link with user string
            link = link + strSearch;

            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
            startActivity(browserIntent);

        }



    }





}
