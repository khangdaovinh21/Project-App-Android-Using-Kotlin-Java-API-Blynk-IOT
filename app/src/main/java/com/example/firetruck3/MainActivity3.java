package com.example.firetruck3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        WebView webView = findViewById(R.id.webView);
        Button button = findViewById(R.id.button);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        String url = "https://docs.google.com/forms/d/e/1FAIpQLSfgQH7lBfwvIKWUF5msNDQ7yX8n2fKNY1Fp4-aOJEodU584DA/viewform?usp=sf_link";
        webView.loadUrl(url);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
