package com.zd.study.activity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.util.Util;
import com.zd.study.R;

import java.util.ArrayList;

public class VedioPlayerTestActivity extends AppCompatActivity {
    private ArrayList<MediaItem> mediaItems;
    private ExoPlayer player;
    private PlayerView playerView;

    private Button mTest1;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mediaItems != null && mediaItems.size() > 0) {

            } else {

            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle_layout);
        playerView = findViewById(R.id.player_view);
        mTest1 = findViewById(R.id.test1);
        mTest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 开始播放
                player.play();
            }
        });
//        getData();
    }

    private ExoPlayer player1;

    //    private ExoPlayer initPlayer() {
//        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
//        ExoTrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
//        DefaultTrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
//        return ExoPlayerFactory.newSimpleInstance(this, trackSelector);
//    }
    private void initializePlayer() {
        //全部使用默认设置初始化ExoPlayer
        player = new ExoPlayer.Builder(this).build();

//        player = ExoPlayerFactory.newSimpleInstance(
//                new DefaultRenderersFactory(this),
//                new DefaultTrackSelector(), new DefaultLoadControl());

        //将显示控件绑定ExoPlayer
        playerView.setPlayer(player);
        String pathEv = Environment.getExternalStorageDirectory().getAbsolutePath();
        Log.e("ceshiceshi", pathEv);
        String path = "/storage/emulated/0/8754A18339201063C20220610093518AC1303E10049300BS20220610093518853662AC1303E100493055CCE.mov";

//        String path = "https://www.deskpro.cn/cinccmedia/media/500508/ccrecord/20220610/8754A18339201063C20220610093518AC1303E10049300BS20220610093518853662AC1303E100493055CCE.mov";
        Uri uri = Uri.parse(path);
//        MediaSource mediaSource = buildMediaSource(uri);
//        player.prepare(mediaSource, true, false);

//        File file = new File(path);
        //构建媒体播放的一个Item， 一个item就是一个播放的多媒体文件
//        MediaItem item = MediaItem.fromUri(Uri.fromFile(file));
        MediaItem item = MediaItem.fromUri(uri);
        //设置ExoPlayer需要播放的多媒体item
        player.setMediaItem(item);
        //设置播放器是否当装备好就播放， 如果看源码可以看出，ExoPlayer的play()方法也是调用的这个方法
//        player.setPlayWhenReady(true);
        //初始化播放位置
//        player.seekTo(0, 0);
        //资源准备，如果设置 setPlayWhenReady(true) 则资源准备好就立马播放。
        player.prepare();
        player.play();
    }

    //    private MediaSource buildMediaSource(Uri uri) {
//        return new ExtractorMediaSource.Factory(
//                new DefaultHttpDataSourceFactory("exoplayer-codelab")).
//                createMediaSource(uri);
//    }
    private void getData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                mediaItems = new ArrayList<>();
                ContentResolver resolver = getContentResolver();
                Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                String[] objs = new String[]{
                        MediaStore.Video.Media.DISPLAY_NAME,//在sdcard时候的名称
                        MediaStore.Video.Media.DURATION,//视频的时长，毫秒
                        MediaStore.Video.Media.SIZE,//文件大小，单位字节
                        MediaStore.Video.Media.ARTIST,//演唱者
                        MediaStore.Video.Media.DATA//在sdcard上路径
                };

                Cursor cursor = resolver.query(uri, objs, null, null, null);
                if (cursor != null) {
                    //循环
                    while (cursor.moveToNext()) {

                        String name = cursor.getString(0);
                        long duration = cursor.getLong(1);
                        long size = cursor.getLong(2);
                        String artist = cursor.getString(3);
                        String data = cursor.getString(4);

                    }

                    cursor.close();
                }

                handler.sendEmptyMessage(0);

            }
        }.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initializePlayer();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Util.SDK_INT <= 23 || player == null) {
            initializePlayer();
        }
    }
}
