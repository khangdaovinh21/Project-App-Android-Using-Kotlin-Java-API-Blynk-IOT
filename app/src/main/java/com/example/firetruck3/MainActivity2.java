package com.example.firetruck3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        WebView webView = findViewById(R.id.webView);
        VideoView videoView = findViewById(R.id.videoView);
        Button button = findViewById(R.id.button);
        Button newButton = findViewById(R.id.newButton);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        String url = "https://www.piercemfg.com/electric-fire-truck-reference-guide";
        webView.loadUrl(url);

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.demo2;
        videoView.setVideoURI(Uri.parse(videoPath));
        videoView.start();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);
            }
        });

        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity4.class);
                startActivity(intent);
            }
        });
    }
}
