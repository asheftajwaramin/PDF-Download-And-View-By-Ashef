package com.asheftajwaramin.pdfdownloadandview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.asheftajwaramin.pdfmanagementbyashef.DownloadFromURL;
import com.asheftajwaramin.pdfmanagementbyashef.ShowDownloads;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        button = findViewById(R.id.downloadButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownloadFromURL downloadFromURL = new DownloadFromURL(MainActivity.this, "https://file-examples-com.github.io/uploads/2017/10/file-example_PDF_1MB.pdf");
                downloadFromURL.downloadPDF();
                registerReceiver(onDownloadComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
            }
        });
        ShowDownloads showDownloads = new ShowDownloads(MainActivity.this);
        listView.setAdapter(showDownloads.pdfListAdapter());

    }
    BroadcastReceiver onDownloadComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ShowDownloads showDownloads = new ShowDownloads(MainActivity.this);
            listView.setAdapter(showDownloads.pdfListAdapter());
        }
    };
}