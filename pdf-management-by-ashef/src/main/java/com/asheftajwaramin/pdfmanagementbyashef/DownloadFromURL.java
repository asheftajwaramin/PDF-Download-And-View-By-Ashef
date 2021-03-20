package com.asheftajwaramin.pdfmanagementbyashef;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

public class DownloadFromURL {

    String url,fileName = "";
    Context context;

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
    }
}
