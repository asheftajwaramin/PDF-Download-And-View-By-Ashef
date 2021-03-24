package com.asheftajwaramin.pdfmanagementbyashef;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.ScrollBar;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.File;

public class PDFViewerOnlineActivity extends AppCompatActivity {
    PDFView pdfView;
    String fileName;
    AlertDialog alertDialog;
    File downloadedFile;
    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer_online);
        pdfView = findViewById(R.id.pdfViewOnline);
        ScrollBar scrollBar = findViewById(R.id.scrollbarOnline);
        pdfView.setScrollBar(scrollBar);
        scrollBar.setHorizontal(false);

        Intent intent = this.getIntent();
        String pdfUrl = intent.getExtras().getString("PDFUrl");
        downloadTempPDF(pdfUrl);
    }
    private void downloadTempPDF(String url)
    {
        DownloadManager downloadManager = (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        fileName = "temp.pdf";
        DownloadManager.Request request =  new DownloadManager.Request(uri);
        request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS +"temp/" , fileName);
        downloadManager.enqueue(request);
        MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(this);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setTitle("Please Wait");
        alertDialogBuilder.setMessage("Don't close the app. This may take some long time for large PDF file.");
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        registerReceiver(readPDF, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

    }
    BroadcastReceiver readPDF = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            alertDialog.dismiss();
            downloadedFile = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS + "temp/" + fileName);

            if (downloadedFile.canRead())
            {
                pdfView.fromFile(downloadedFile).defaultPage(1).swipeVertical(true).enableSwipe(true).onLoad(new OnLoadCompleteListener() {
                    @Override
                    public void loadComplete(int nbPages) {
                        Toast.makeText(PDFViewerOnlineActivity.this, "This PDF has " + nbPages + " page", Toast.LENGTH_SHORT).show();
                    }
                }).load();
            }
        }
    };

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    @Override
    protected void onDestroy() {
        boolean deleted = downloadedFile.delete();
        unregisterReceiver(readPDF);
        super.onDestroy();
    }
}