package com.asheftajwaramin.pdfmanagementbyashef;

import android.content.Context;
import android.content.Intent;

public class ViewFromURL {
    Context context;
    String url;

    public ViewFromURL(Context context, String url) {
        this.context = context;
        this.url = url;
    }
    public void viewPDF()
    {
        Intent intent = new Intent(context, PDFViewerOnlineActivity.class);
        intent.putExtra("PDFUrl",url);
        context.startActivity(intent);
    }
}
