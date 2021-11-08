package com.example.sqlitetest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Random;

import static com.example.sqlitetest.DBHelper.KEY_VOL1;
import static com.example.sqlitetest.DBHelper.KEY_VOL2;
import static com.example.sqlitetest.DBHelper.KEY_OF;
import static com.example.sqlitetest.DBHelper.KEY_CURRENT;
import static com.example.sqlitetest.DBHelper.TABLE_CONT;
import static com.example.sqlitetest.DBHelper.TABLE_NAME;
import static com.example.sqlitetest.DBHelper.TABLE_Q1;
import static com.example.sqlitetest.DBHelper.TABLE_Q2;
import static com.example.sqlitetest.DBHelper.TABLE_Q3;
import static com.example.sqlitetest.DBHelper.TABLE_Q4;
import static com.example.sqlitetest.DBHelper.TABLE_Q5;
import static com.example.sqlitetest.DBHelper.TABLE_Q6;
import static com.example.sqlitetest.DBHelper.TABLE_SETTINGS;
import static com.example.sqlitetest.DBHelper.TABLE_SHORT;
import static com.google.android.material.internal.ContextUtils.getActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnAdd, btnPtrn,btnDescr;
    DBHelper dbHelper;
    String Q3_text="Вы говорите неприятные вещи, значит, вы — плохой человек.";
    String Q5_text="Рак вызывает смерть.";
    String Q1_text="Опоздание говорит о твоем равнодушии ко мне!";
    String Q4_text="Чтобы справиться со стрессом, я ем шоколад.";
    String Q6_text="Мне абсолютно все равно, потому что у нашего руководства слова постоянно расходятся с делом.";
    String Q2_text="Я не могу купить у вас это, потому что это слишком дорого.";
    int TClick,onstart=0,DClick=0,set_current,set_of,current_i;
    public static int set1;
    String Q1_res,Q2_res,Q3_res,Q4_res,Q5_res,Q6_res,Pt;
    String str,ret = new String();
    ColorStateList oldColors = null;
    //SQLiteDatabase database = dbHelper.getWritableDatabase();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            switch(id){
                case R.id.menu_settings :
                    SampleDialogFragment df = new SampleDialogFragment();
                    df.show(getSupportFragmentManager(), "SampleDialog");
                    return true;
                case R.id.menu_help:
                    return true;
            }
            //headerView.setText(item.getTitle());
            return super.onOptionsItemSelected(item);
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        current_i=1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPtrn = (Button) findViewById(R.id.btnPtrn);
        btnPtrn.setOnClickListener(this);
        btnDescr = (Button) findViewById(R.id.btnDescr);
        btnDescr.setOnClickListener(this);
        dbHelper = new DBHelper(this);
        load_settings();
        btnPtrn.performClick();
    }
    @Override
    protected void onStart() {
        super.onStart();
        /*String TAG = "Жизненный цикл";
        Toast.makeText(getApplicationContext(), String.valueOf(set1), Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onRestart()");*/
        //load_settings();
    }
    public void saveColor() {
        if (onstart==0) {
            TextView Q1_tv = findViewById(R.id.Q1);
            oldColors =  Q1_tv.getTextColors();
            onstart=1;}
    }
    public void load_settings() {
       SQLiteDatabase database = dbHelper.getWritableDatabase();
         try (Cursor cursor = database.query(TABLE_SETTINGS, null, "_id = 1", null, null, null, null)) {
            cursor.moveToPosition(0);
            set1=cursor.getInt(cursor.getColumnIndex(KEY_VOL1));
            set_current=cursor.getInt(cursor.getColumnIndex(KEY_CURRENT));
            set_of=cursor.getInt(cursor.getColumnIndex(KEY_OF));}


        dbHelper.close();
    }
    public void save_settings() {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String where = "KEY_ID = 1" ;
        contentValues.put(KEY_VOL1, set1);
        database.update(TABLE_SETTINGS, contentValues, where, null);
        dbHelper.close();
    }
    public void change_settings(int i) {
        //MainActivity.setTitle("Прогресс: ");
        //Toast.makeText(getApplicationContext(), String.valueOf(set1), Toast.LENGTH_SHORT).show();
        switch (i) {
            case 0:
                set1=0;
                setTitle("NLP Magic");
                break;
            case 1:
                ret="44";
                setTitle("Прогресс: "+String.valueOf(set_current)+" / "+String.valueOf(set_current));
                set1=1;
                setTitle("NLP Magic");
                break;
            case 2:
                set1=2;
                setTitle("NLP Magic");
                break;
        }

    }

    public String getQ(int q) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        if (q == 1) {
            try (Cursor cursor3 = database.query(TABLE_Q1, null, "_id = ?", new String[]{str}, null, null, null)) {
                cursor3.moveToPosition(0);
                ret = cursor3.getString(cursor3.getColumnIndex(KEY_VOL2)) + "\n";}}
        if (q == 2) {
            try (Cursor cursor4 = database.query(TABLE_Q2, null, "_id = ?", new String[]{str}, null, null, null)) {
                cursor4.moveToPosition(0);
                ret= cursor4.getString(cursor4.getColumnIndex(KEY_VOL2)) + "\n";}}
        if (q == 3) {
            try (Cursor cursor3 = database.query(TABLE_Q3, null, "_id = ?", new String[]{str}, null, null, null)) {
                cursor3.moveToPosition(0);
                ret= cursor3.getString(cursor3.getColumnIndex(KEY_VOL2)) + "\n";}}
        if (q == 4) {
            try (Cursor cursor3 = database.query(TABLE_Q4, null, "_id = ?", new String[]{str}, null, null, null)) {
                cursor3.moveToPosition(0);
                ret= cursor3.getString(cursor3.getColumnIndex(KEY_VOL2)) + "\n";}}
        if (q == 5) {
            try (Cursor cursor3 = database.query(TABLE_Q5, null, "_id = ?", new String[]{str}, null, null, null)) {
                cursor3.moveToPosition(0);
                ret= cursor3.getString(cursor3.getColumnIndex(KEY_VOL2)) + "\n";}}
        if (q == 6) {
            try (Cursor cursor3 = database.query(TABLE_Q6, null, "_id = ?", new String[]{str}, null, null, null)) {
                cursor3.moveToPosition(0);
                ret= cursor3.getString(cursor3.getColumnIndex(KEY_VOL2)) + "\n";}}
        dbHelper.close();
       return ret;
     }
    @Override
    public void onClick(View v) {
        //setTitle("Прогресс: "+String.valueOf(set_current)+" / "+String.valueOf(
        change_settings(1);
        TextView text1 = findViewById(R.id.textView1);
        TextView text2 = findViewById(R.id.textView2);
        Button button=findViewById(R.id.btnDescr);
        String nmi = new String();
        String emi = new String();
        String table = new String();
       SQLiteDatabase database = dbHelper.getWritableDatabase();

        switch (v.getId()) {
           /* case R.id.btnAdd:
                contentValues.put(KEY_VOL1, name);
                contentValues.put(KEY_VOL2, email);
                database.insert(TABLE_NAME, null, contentValues);
                break;*/
            case R.id.btnDescr:

                if (DClick<2) {
                if (DClick==0) {
                    table=TABLE_NAME;
                    DClick=1;
                    button.setText("Краткие выводы");
                    }
                else if (DClick==1) {
                    table=TABLE_SHORT;
                    DClick=2;
                }
                try (Cursor cursor2 = database.query(table, null, "_id = ?", new String[]{str}, null, null, null)) {
                cursor2.moveToPosition(0);
                Pt=cursor2.getString(cursor2.getColumnIndex(KEY_VOL2));}
                text2.setText(Pt);
                }
                break;

            case R.id.btnPtrn:
                TClick=0;
                DClick=0;
                text2.setText("");
                button.setText("Полное описание");
                if (onstart>0) {TextView Q1_tv = findViewById(R.id.Q1);
                    Q1_tv.setTextColor(oldColors);}
                Random random = new Random();
                switch (set1) {
                    case 0:
                        break;
                    case 1:
                        str=String.valueOf(current_i);
                        if (current_i<26) {
                            current_i=current_i+1;
                        }
                        else {current_i=1;}
                        //str=String.valueOf(random.nextInt(26)+1);
                        break;
                    case 2:
                        break;
                }

                TextView Q1tv= findViewById(R.id.Q1);
                Q1tv.setText(Q1_text+"\n");
                try (Cursor cursor1 = database.query(TABLE_CONT, null, "_id = ?", new String[]{str}, null, null, null)) {
                    cursor1.moveToPosition(0);
                    text1.setText(cursor1.getString(cursor1.getColumnIndex(KEY_VOL2))+"\n");}

                getQ(1);
                break;
            case R.id.btnClear:
                database.delete(TABLE_NAME, null, null);
                break;

        }
        dbHelper.close();
    }

    public void TextonClick(View v)
    {   TextView Q1_tv = findViewById(R.id.Q1);
        saveColor();
        int my_blue = getResources().getColor(R.color.my_blue);
        if (TClick==10) {
            Q1_tv.setText(Q6_text + "\n\n" + getQ(6));
            Q1_tv.setTextColor(my_blue);
            TClick = 11; }
        else if (TClick==9)  {
            Q1_tv.setText(Q6_text + "\n");
            Q1_tv.setTextColor(oldColors);
            TClick = 10; }
        else if (TClick==8) {
            Q1_tv.setText(Q5_text + "\n\n" + getQ(5));
            Q1_tv.setTextColor(my_blue);
            TClick = 9; }
        else if (TClick==7)  {
            Q1_tv.setText(Q5_text + "\n");
            Q1_tv.setTextColor(oldColors);
            TClick = 8; }
        else if (TClick==6) {
            Q1_tv.setText(Q4_text + "\n\n" + getQ(4));
            Q1_tv.setTextColor(my_blue);
            TClick = 7; }
        else if (TClick==5)  {
            Q1_tv.setText(Q4_text + "\n");
            Q1_tv.setTextColor(oldColors);
            TClick = 6; }
        else if (TClick==4) {
            Q1_tv.setText(Q3_text + "\n\n" + getQ(3));
            Q1_tv.setTextColor(my_blue);
            TClick = 5; }
        else if (TClick==3)  {
            Q1_tv.setText(Q3_text + "\n");
            Q1_tv.setTextColor(oldColors);
            TClick = 4; }
        else if (TClick==2) {
            Q1_tv.setText(Q2_text + "\n\n" + getQ(2));
            Q1_tv.setTextColor(my_blue);
            TClick = 3; }
        else if (TClick==1)  {
            Q1_tv.setText(Q2_text + "\n");
            Q1_tv.setTextColor(oldColors);
            TClick = 2; }
        else if (TClick==0) {
            Q1_tv.setText(Q1_text + "\n\n" + getQ(1));
            Q1_tv.setTextColor(my_blue);
            TClick = 1; }
        }
}
//Toast toast = Toast.makeText(getApplicationContext(),"insert into " + TABLE_NAME  +" values('2','2','123')", Toast.LENGTH_LONG);
//toast.show();