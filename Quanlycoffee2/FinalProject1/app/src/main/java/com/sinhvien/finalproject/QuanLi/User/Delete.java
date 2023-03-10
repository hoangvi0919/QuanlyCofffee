 package com.sinhvien.finalproject.QuanLi.User;

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
         ActionBar actionBar = getSupportActionBar();
         actionBar.hide();
         setContentView(R.layout.activity_user_delete);

         lvDisplay = findViewById(R.id.lvDisplay);
         dbHelper = new DBHelper(Delete.this);
         ArrayAdapter arrayAdapter;
         addUsertoList(dbHelper);

         lvDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener(){
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                 AlertDialog.Builder del = new AlertDialog.Builder(Delete.this);
                 del.setMessage("Are you sure ??").setCancelable(false)
                         .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialog, int which) {
                                 User user = (User) parent.getItemAtPosition(position);
                                 dbHelper.deleteUser(user);
                                 addUsertoList(dbHelper);
                             }
                         })
                         .setNegativeButton("No", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialog, int which) {
                                 dialog.cancel();
                             }
                         });
                 AlertDialog alt = del.create();
                 alt.setTitle("Delete Account");
                 alt.show();
             }
         });
     }
     private void addUsertoList(DBHelper dbHelper) {
         ArrayAdapter arrayAdapter;
         arrayAdapter = new ArrayAdapter<User>(Delete.this, android.R.layout.simple_list_item_1,dbHelper.getAllUser());
         lvDisplay.setAdapter(arrayAdapter);
    }
}