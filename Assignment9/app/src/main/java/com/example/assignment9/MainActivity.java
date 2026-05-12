package com.example.assignment9;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.window.OnBackInvokedDispatcher;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
     Button btn;
    EditText url;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
           btn=findViewById(R.id.btn);
           url=findViewById(R.id.url);
           webView=findViewById(R.id.webView);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                String uri=url.getText().toString();
                webView.setWebViewClient(new WebViewClient());
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl(uri);
                }
                });
            return insets;
        });
    }

    @NonNull
    @Override
    public void onBackPressed(){
        if(webView.canGoBack())
            webView.goBack();
        else
            super.onBackPressed();
    }
}