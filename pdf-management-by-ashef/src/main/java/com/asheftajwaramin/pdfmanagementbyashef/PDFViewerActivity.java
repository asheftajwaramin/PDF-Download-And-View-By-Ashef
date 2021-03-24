package com.asheftajwaramin.pdfmanagementbyashef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.ScrollBar;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;

import java.io.File;

public class PDFViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);

        PDFView pdfView = findViewById(R.id.pdfView);
        ScrollBar scrollBar = findViewById(R.id.scrollbar);
        pdfView.setScrollBar(scrollBar);
        scrollBar.setHorizontal(false);

        Intent intent = this.getIntent();
        String pdfPath = intent.getExtras().getString("PDFPath");

        File file = new File(pdfPath);

        if (file.canRead())
        {
            pdfView.fromFile(file).defaultPage(1).swipeVertical(true).enableSwipe(true).onLoad(new OnLoadCompleteListener() {
                @Override
                public void loadComplete(int nbPages) {
                    Toast.makeText(PDFViewerActivity.this, "This PDF has " + nbPages + " page", Toast.LENGTH_SHORT).show();
                }
            }).load();
        }

    }
}