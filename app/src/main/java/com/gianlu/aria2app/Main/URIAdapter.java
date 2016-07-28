package com.gianlu.aria2app.Main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.gianlu.aria2app.R;

import java.util.List;

public class URIAdapter extends BaseAdapter {
    private Context context;
    private List<String> objs;

    public URIAdapter(Context context, List<String> objs) {
        this.context = context;
        this.objs = objs;
    }

    @Override
    public int getCount() {
        return objs.size();
    }

    @Override
    public String getItem(int i) {
        return objs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint({"InflateParams", "ViewHolder"})
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.uri_custom_item, null);

        ((TextView) view.findViewById(R.id.uriCustomItem_uri)).setText(getItem(i));
        view.findViewById(R.id.uriCustomItem_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                final EditText uri = new EditText(context);
                uri.setText(getItem(i));
                uri.setInputType(InputType.TYPE_TEXT_VARIATION_URI);

                dialog.setView(uri)
                        .setTitle(R.string.uri)
                        .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int ii) {
                                if (uri.getText().toString().trim().isEmpty()) return;
                                objs.remove(i);
                                objs.add(uri.getText().toString().trim());

                                notifyDataSetChanged();
                            }
                        }).create().show();
            }
        });
        view.findViewById(R.id.uriCustomItem_remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                objs.remove(i);

                notifyDataSetChanged();
            }
        });

        return view;
    }
}