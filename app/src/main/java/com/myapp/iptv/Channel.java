package com.myapp.iptv;

import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class LiveTvActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_tv);

        RecyclerView recyclerView = findViewById(R.id.recycler_channels);
        VideoView videoView = findViewById(R.id.video_player_view);

        List<Channel> channels = new ArrayList<>();
        channels.add(new Channel("قناة الجزيرة مباشر", "https://live.aljazeera.net/1000/chunk.m3u8"));
        channels.add(new Channel("قناة القرآن الكريم", "https://win.holylive.net/quran/playlist.m3u8"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ChannelAdapter(channels, channel -> {
            videoView.setVideoURI(Uri.parse(channel.url));
            videoView.start();
        }));
    }
}

