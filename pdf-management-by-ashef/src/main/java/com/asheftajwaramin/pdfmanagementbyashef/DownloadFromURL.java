package com.asheftajwaramin.pdfmanagementbyashef;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class DownloadFromURL {

    String url,fileName = "";
    Context context;
    AlertDialog alertDialog;

    public DownloadFromURL(Context context, String url ) {
        this.url = url;
        this.context = context;
    }
    public DownloadFromURL(Context context, String url, String fileName) {
        this.url = url;
        this.context = context;
        this.fileName = fileName;
    }

    public void downloadPDF()
    {
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        if (fileName.isEmpty())
        {
            fileName = url.substring(url.lastIndexOf("/")+1);
        }
        DownloadManager.Request request =  new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS + "",fileName);
        downloadManager.enqueue(request);
        MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(context);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setTitle("Please Wait");
        alertDialogBuilder.setMessage("File is downloading. Don't close the app. This may take some long time for large PDF file.");
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        context.registerReceiver(downloading, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    BroadcastReceiver downloading = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
           alertDialog.dismiss();
        }
    };
}
