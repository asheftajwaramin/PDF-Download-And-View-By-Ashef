package com.asheftajwaramin.pdfmanagementbyashef;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.File;
import java.util.ArrayList;

public class PDFGetterAdapter extends BaseAdapter {

    Context context;
    ArrayList<PDFDoc> pdfDocs;
    PDFGetterAdapter pdfGetterAdapter;

    public PDFGetterAdapter(Context context, ArrayList<PDFDoc> pdfDocs) {
        this.context = context;
        this.pdfDocs = pdfDocs;
        this.pdfGetterAdapter = this;
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

        ImageView deleteIcon = convertView.findViewById(R.id.deleteIcon);
        deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(context);
                alertDialogBuilder.setTitle("Are you sure?");
                alertDialogBuilder.setMessage(pdfDoc.getName() + " will be deleted");
                alertDialogBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       boolean deleted = deletePDF(pdfDoc.getPath(),position);
                       if (deleted) {
                           pdfDocs.remove(pdfDoc);
                           pdfGetterAdapter.notifyDataSetChanged();

                       }

                    }
                })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setCancelable(false);
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

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
    private boolean deletePDF(String path, int position)
    {
        File file = new File(path);
        boolean deleted = file.delete();
        if (deleted)
        {
            Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Delete failed, Try again later.", Toast.LENGTH_SHORT).show();
        }
        return deleted;
    }
}
