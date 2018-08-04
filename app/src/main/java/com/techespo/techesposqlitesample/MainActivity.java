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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.techespo.techesposqlitesample.databases.DBHelper;

public class MainActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
*/
       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

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

               if (c.moveToFirst()){
                   do{
                       builder.append("id:"+c.getString(0)+"|| Name:"+c.getString(1)+"\n");
                   }while (c.moveToNext());

                   ((TextView) findViewById(R.id.message)).setText(builder.toString());
               }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
