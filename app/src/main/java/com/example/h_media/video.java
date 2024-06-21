package com.example.h_media;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class video extends AppCompatActivity implements VideoRVAdapter.VideoClickInterface {
    private ArrayList<VideoRVModel> videoRVModelArrayList;
    private VideoRVAdapter videoRVAdapter;
    private RecyclerView videoRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        Button image;
        Button music;
        Button video;
        image = findViewById(R.id.image);
        music = findViewById(R.id.music);
        video = findViewById(R.id.video);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2=new Intent(video.this,photo.class);
                startActivity(i2);
            }
        });
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3=new Intent(video.this,song.class);
                startActivity(i3);

            }
        });
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(video.this,video.class);
                startActivity(i1);

            }
        });
        videoRV = findViewById(R.id.recycleView);
        videoRVModelArrayList = new ArrayList<>();
        videoRVAdapter = new VideoRVAdapter(videoRVModelArrayList, this, this::onVideoClick);
        videoRV.setLayoutManager(new GridLayoutManager(this, 2));
        videoRV.setAdapter(videoRVAdapter);
        getVideos();
    }
    private void getVideos(){
        ContentResolver contentResolver=getContentResolver();
        Uri uri= MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor=contentResolver.query(uri,null,null,null,null);
        if(cursor!=null&&cursor.moveToFirst()){
            do{
                @SuppressLint("Range") String videoTitle=cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.TITLE));
                @SuppressLint("Range") String videoPath=cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
                Bitmap videoThumbnail= ThumbnailUtils.createVideoThumbnail(videoPath,MediaStore.Images.Thumbnails.MINI_KIND);

                videoRVModelArrayList.add(new VideoRVModel(videoTitle,videoPath,videoThumbnail));
            }
            while (cursor.moveToNext());
        }
    }
    public void onVideoClick(int position) {
        Intent intent=new Intent(video.this, videoPlayer.class);
        intent.putExtra("videoName",videoRVModelArrayList.get(position).getVideoName());
        intent.putExtra("videoPath",videoRVModelArrayList.get(position).getVideoPath());
        startActivity(intent);



    }
}