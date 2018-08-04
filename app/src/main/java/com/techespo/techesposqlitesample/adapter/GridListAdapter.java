package com.techespo.techesposqlitesample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.techespo.techesposqlitesample.R;
import com.techespo.techesposqlitesample.UserDao;

import java.util.ArrayList;

public class GridListAdapter  extends BaseAdapter{
    private ArrayList<UserDao> items;
    private Context  context;
    public GridListAdapter(Context context, ArrayList<UserDao> items){
        this.items = items;
        this.context = context;
    }
    @Override
    public int getCount() {
        return items.size();
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

        UserDao obj = items.get(position);
        View v = convertView;
        if(v ==  null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.grid_item_layout,null);

        }else{
            v.setTag(position);
        }

        ((TextView)v.findViewById(R.id.txt_name)).setText(obj.getName());
        ((TextView)v.findViewById(R.id.txt_pass)).setText(obj.getPassword());
        return v;
    }
}
