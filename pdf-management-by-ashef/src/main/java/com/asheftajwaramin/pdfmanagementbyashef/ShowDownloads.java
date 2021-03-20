package com.asheftajwaramin.pdfmanagementbyashef;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;

public class ShowDownloads {

    Context context;

    public ShowDownloads(Context context) {
        this.context = context;
    }

    public PDFGetterAdapter pdfListAdapter()
    {
        return new PDFGetterAdapter(context,getPDFs());
    }

    private ArrayList<PDFDoc> getPDFs()
    {
        ArrayList<PDFDoc> pdfDocs = new ArrayList<>();
        File downloadsFolder = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        PDFDoc pdfDoc;
        if (downloadsFolder.exists())
        {
            File[] files = downloadsFolder.listFiles();

            for (File file : files) {
                if (file.getPath().endsWith(".pdf")) {
                    pdfDoc = new PDFDoc();
                    pdfDoc.setName(file.getName());
                    pdfDoc.setPath(file.getAbsolutePath());
                    pdfDocs.add(pdfDoc);
                }
            }
        }
        return pdfDocs;
    }
}
