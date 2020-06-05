package com.example.bai2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RowAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Row> rows;

    public RowAdapter(Context context, int layout, List<Row> rows) {
        this.context = context;
        this.layout = layout;
        this.rows = rows;
    }

    @Override
    public int getCount() {
        return rows.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout,null);

        TextView txtName = convertView.findViewById(R.id.title);
        ImageView img = convertView.findViewById(R.id.img);
        ImageView thump = convertView.findViewById(R.id.thump);

        final Row row = rows.get(position);
        txtName.setText(row.getTitle());
        img.setImageResource(row.getImage());
        thump.setImageResource(row.getThump());

        View.OnClickListener onClickOpenWebView;
        onClickOpenWebView=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), ActivityWebview.class);
                String url=row.getUrl();
                myIntent.putExtra("URL", url);
                v.getContext().startActivity(myIntent);
//                Toast.makeText(null, "hello", Toast.LENGTH_LONG).show();
            }
        };

        txtName.setOnClickListener(onClickOpenWebView);
        thump.setOnClickListener(onClickOpenWebView);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), ActivityImage.class);
                myIntent.putExtra("SRC", row.getImage());
                v.getContext().startActivity(myIntent);
            }
        });

        return convertView;
    }
}
