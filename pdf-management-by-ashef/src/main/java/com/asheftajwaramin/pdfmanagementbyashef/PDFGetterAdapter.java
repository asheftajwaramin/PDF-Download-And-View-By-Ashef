package com.asheftajwaramin.pdfmanagementbyashef;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PDFGetterAdapter extends BaseAdapter {

    Context context;
    ArrayList<PDFDoc> pdfDocs;

    public PDFGetterAdapter(Context context, ArrayList<PDFDoc> pdfDocs) {
        this.context = context;
        this.pdfDocs = pdfDocs;
    }

    @Override
    public int getCount() {
        return pdfDocs.size();
    }

    @Override
    public Object getItem(int position) {
        return pdfDocs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.pdf_getter_adapter_layout, parent, false);
        }
        PDFDoc pdfDoc = (PDFDoc) this.getItem(position);
        TextView nameTxt= (TextView) convertView.findViewById(R.id.nameTxt);
        ImageView img= (ImageView) convertView.findViewById(R.id.pdfImage);

        nameTxt.setText(pdfDoc.getName());
        img.setImageResource(R.drawable.pdf_icon);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPDFView(pdfDoc.getPath());
            }
        });
        return convertView;
    }
    private void openPDFView(String path)
    {
        Intent intent = new Intent(context, PDFViewerActivity.class);
        intent.putExtra("PDFPath", path);
        context.startActivity(intent);
    }
}
