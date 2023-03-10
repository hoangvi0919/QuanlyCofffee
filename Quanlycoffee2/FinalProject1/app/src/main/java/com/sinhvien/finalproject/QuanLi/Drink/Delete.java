package com.sinhvien.finalproject.QuanLi.Drink;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sinhvien.finalproject.DBHelper;
import com.sinhvien.finalproject.R;

public class Delete extends AppCompatActivity {

    ListView lvDisplay;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        lvDisplay = findViewById(R.id.lvDisplay);
        dbHelper = new DBHelper(this);
        addDrinktoList(dbHelper);

        lvDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder del = new AlertDialog.Builder(Delete.this);
                del.setMessage("Are you sure ??").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Drink drink = (Drink) parent.getItemAtPosition(position);
                                dbHelper.deleteDrink(drink);
                                addDrinktoList(dbHelper);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alt = del.create();
                alt.setTitle("Delete Drink");
                alt.show();
            }
        });
    }

    private void addDrinktoList(DBHelper dbHelper) {
        ArrayAdapter arrayAdapter;
        arrayAdapter = new ArrayAdapter<Drink>(Delete.this, android.R.layout.simple_list_item_1,dbHelper.getAllDrink());
        lvDisplay.setAdapter(arrayAdapter);
    }
}