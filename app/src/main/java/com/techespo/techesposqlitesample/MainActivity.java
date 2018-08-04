package com.techespo.techesposqlitesample;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.techespo.techesposqlitesample.adapter.GridListAdapter;
import com.techespo.techesposqlitesample.databases.DBHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         dbHelper = new DBHelper(this);
        ((Button) findViewById(R.id.btnInsert)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues  values = new ContentValues();
                values.put("name","abc");
                values.put("email","test@test.com");
                dbHelper.insertMethod(values);
                Toast.makeText(MainActivity.this,"Data inserted Successfully!", Toast.LENGTH_LONG).show();
            }
        });
        ((Button) findViewById(R.id.btnUpdate)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues  values = new ContentValues();
                values.put("name","zzz");
                values.put("email","test1@test.com");
                dbHelper.updateMethod(values,"1");
                Toast.makeText(MainActivity.this,"Data inserted Successfully!", Toast.LENGTH_LONG).show();
            }
        });

        ((Button) findViewById(R.id.btnSelect)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Cursor c =  dbHelper.openDB().rawQuery("Select * from user", null);
               StringBuilder  builder  = new StringBuilder();
               ArrayList<UserDao> itemList = new ArrayList<UserDao>();
               if (c.moveToFirst()){
                   do{
                       UserDao obj = new UserDao();
                       obj.setName(c.getString(1));
                       obj.setPassword(c.getString(0));
                       itemList.add(obj);
                   }while (c.moveToNext());
                   if(itemList != null && itemList.size()>0){
                       showGrid(itemList);
                   }
                   ((TextView) findViewById(R.id.message)).setText(builder.toString());
               }
            }
        });
    }

    private void showGrid(final ArrayList<UserDao> item){
        GridView gridView = (GridView) findViewById(R.id.gridview);
        GridListAdapter adapter =  new GridListAdapter(this,item);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Toast.makeText(MainActivity.this,"Grid Item Selected:"
                       +item.get(position).getName(),Toast.LENGTH_LONG).show();
            }
        });
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"Grid Long Item Selected:"
                        +item.get(position).getName(),Toast.LENGTH_LONG).show();
                return false;
            }
        });

    }

}
