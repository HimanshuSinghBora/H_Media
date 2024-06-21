package com.example.h_media;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class photo extends AppCompatActivity {
    private ArrayList<String> imagePaths;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter imageRVAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        Button image;
        Button music;
        Button video;
        image = findViewById(R.id.image);
        music = findViewById(R.id.music);
        video = findViewById(R.id.video);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2=new Intent(photo.this,photo.class);
                startActivity(i2);
            }
        });
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3=new Intent(photo.this,song.class);
                startActivity(i3);
            }
        });
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(photo.this,video.class);
                startActivity(i1);
            }
        });
        imagePaths = new ArrayList<>();
        recyclerView = findViewById(R.id.recycleView);
        getImagePath();
        prepareRecyclerView();
    }
    private void prepareRecyclerView() {
        imageRVAdapter = new RecyclerViewAdapter(photo.this, imagePaths);
        GridLayoutManager manager = new GridLayoutManager(photo.this, 4);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(imageRVAdapter);
    }
    private void getImagePath() {
        boolean isSDPresent = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        if (isSDPresent) {
            final String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};
            final String orderBy = MediaStore.Images.Media._ID;
            Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null, orderBy);
            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                cursor.moveToPosition(i);
                int dataColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                imagePaths.add(cursor.getString(dataColumnIndex));
            }
            cursor.close();
        }
    }
}