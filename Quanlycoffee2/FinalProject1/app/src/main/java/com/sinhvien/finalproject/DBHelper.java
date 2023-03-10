package com.sinhvien.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;

import androidx.annotation.Nullable;

import com.sinhvien.finalproject.QuanLi.Department.Department;
import com.sinhvien.finalproject.QuanLi.Drink.Drink;
import com.sinhvien.finalproject.QuanLi.User.User;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String LOG = "DatabaseHelper";

    public static final String DATABASE_NAME = "data";

    public static final String TABLE_USER = "User";
    public static final String TABLE_DEPARTMENT = "Department";
    public static final String TABLE_MEMBER = "Member";
    public static final String TABLE_BILL = "Bill";
    public static final String TABLE_INNER_BILL = "InnerBill";
    public static final String TABLE_DRINK = "Drink";
    public static final String TABLE_DRINK_TYPE = "DrinkType";

    public static final String ID = "Id";
    public static final String USERID = "UserId";
    public static final String DEPARTMENT_ID = "DepId";
    public static final String MEMBER_ID = "MemberId";
    public static final String BILL_ID = "BillId";
    public static final String INNER_ID = "InnerId";
    public static final String DRINK_ID = "DrinkID";

    public static final String USERNAME = "user";
    public static final String PASSWORD = "pass";
    public static final String NAME = "ten";
    public static final String DEPARTMENT_NAME="DepName";
    public static final String PHONE_NUMBER = "sdt";
    public static final String ADDRESS = "diachi";
    public static final String POINT = "point";
    public static final String MONEY = "money";
    public static final String TYPENAME = "typename";
    public static final String AMOUNT = "amount";
    public static final String IMAGE = "image";
    public static final String IMAGENAME = "imagename";
    public static final String STOCK = "stock";

    public static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + DEPARTMENT_NAME + " TEXT NOT NULL, " + USERNAME + " TEXT NOT NULL, " + PASSWORD + " TEXT NOT NULL, " + NAME + " TEXT, " + PHONE_NUMBER + " TEXT, " + ADDRESS + " TEXT" +")";
    public static final String CREATE_TABLE_DEPARTMENT = "CREATE TABLE " + TABLE_DEPARTMENT + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT" + ")";
    public static final String CREATE_TABLE_MEMBER = "CREATE TABLE " + TABLE_MEMBER + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + DEPARTMENT_NAME + " TEXT NOT NULL," + NAME + " TEXT," + POINT + " LONG" + ")";
    //public static final String CREATE_TABLE_BILL = "CREATE TABLE " + TABLE_BILL + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + USERID + " INTEGER," + MONEY + " LONG," + POINT + " LONG" + ")";
    //public static final String CREATE_TABLE_INNER_BILL = "CREATE TABLE " + TABLE_INNER_BILL + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + BILL_ID + " INTEGER," + DRINK_ID + " INTEGER," + AMOUNT + " INTEGER" + ")";
    public static final String CREATE_TABLE_DRINK = "CREATE TABLE "+ TABLE_DRINK + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT," + TYPENAME + " TEXT," + IMAGE + " BLOB," + MONEY + " LONG," + POINT + " LONG" + ")";
    //public static final String CREATE_TABLE_TYPE_DRINK = "CREATE TABLE " + TABLE_DRINK_TYPE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT" + ")" ;

    public ByteArrayOutputStream byteArrayOutputStream;
    public byte[] imageInBytes;
    public DBHelper(Context context) {
        super(context,  DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_DEPARTMENT);
        db.execSQL(CREATE_TABLE_MEMBER);
//        db.execSQL(CREATE_TABLE_BILL);
//        db.execSQL(CREATE_TABLE_INNER_BILL);
        db.execSQL(CREATE_TABLE_DRINK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists " + TABLE_USER);
        db.execSQL("drop Table if exists " + TABLE_DEPARTMENT);
        db.execSQL("drop Table if exists " + TABLE_MEMBER);
        db.execSQL("drop Table if exists " + TABLE_BILL);
        db.execSQL("drop Table if exists " + TABLE_INNER_BILL);
        db.execSQL("drop Table if exists " + TABLE_DRINK);

        onCreate(db);
    }

    public Boolean checkUsername(String user){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE user = ?",new String[] {user});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean checkAdmin(String user,String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_USER + " WHERE user = ? and pass = ? and DepName = 'Admin'", new String[]{user,pass});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean checkStaff(String user, String pass){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_USER+ " WHERE user = ? and pass = ? and DepName = 'Staff'", new String[]{user,pass});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DEPARTMENT_NAME,user.getDepName());
        contentValues.put(USERNAME,user.getUser());
        contentValues.put(PASSWORD,user.getPass());
        contentValues.put(NAME,user.getName());
        contentValues.put(PHONE_NUMBER,user.getPhone_Number());
        contentValues.put(ADDRESS,user.getAddress());

        long result = db.insert(TABLE_USER, null, contentValues);
        if (result == -1){
            return false;
        }else
            return true;
    }
    public Boolean addDepartment(Department department){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,department.getName());

        long result = db.insert(TABLE_DEPARTMENT, null, contentValues);
        if (result==-1){
            return false;
        }else
            return true;
    }
    public Boolean addDrink(Drink drink){
        SQLiteDatabase db = this.getWritableDatabase();
        Bitmap imagetostore = drink.getImage();

        byteArrayOutputStream= new ByteArrayOutputStream();
        imagetostore.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);

        imageInBytes=byteArrayOutputStream.toByteArray();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,drink.getName());
        contentValues.put(TYPENAME,drink.getTypeName());
        contentValues.put(IMAGE,imageInBytes);
        contentValues.put(MONEY,drink.getMoney());
        contentValues.put(POINT,drink.getPoint());

        long result = db.insert(TABLE_DRINK,null,contentValues);
        if (result==-1){
            return false;
        }else
            return true;
    }

    public Boolean deleteUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM "+ TABLE_USER + " WHERE "+ID +" = "+user.getId();
        Cursor cursor = db.rawQuery(queryString,null);
        if (cursor.moveToFirst()){
            return true;
        }else
            return false;
    }
    public Boolean deleteDepartment(Department department){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM "+TABLE_DEPARTMENT + " WHERE "+ ID + " = "+department.getId();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            return true;
        }else
            return false;
    }
    public Boolean deleteDrink(Drink drink){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + TABLE_DRINK + " WHERE " + ID + " = " + drink.getId();
        Cursor cursor = db.rawQuery(queryString,null);
        if (cursor.moveToFirst()){
            return true;
        }else
            return false;
    }

    public List<User> getAllUser(){
        List<User> userlist = new ArrayList<>();
        String queryString = "SELECT * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String Department = cursor.getString(1);
                String Username = cursor.getString(2);
                String Password = cursor.getString(3);
                String Name = cursor.getString(4);
                String Phone = cursor.getString(5);
                String Address = cursor.getString(6);

                User user = new User(id,Department,Username,Password,Name,Phone,Address);
                userlist.add(user);
            }while (cursor.moveToNext());
        }else{}
        cursor.close();
        return userlist;
    }
    public List<Department> getAllDepartment(){
        List<Department> departmentslist= new ArrayList<>();
        String queryString = "SELECT * FROM " + TABLE_DEPARTMENT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            do {
                int depid = cursor.getInt(0);
                String Name = cursor.getString(1);

                Department department = new Department(depid,Name);
                departmentslist.add(department);
            }while (cursor.moveToNext());
        }else{}
        cursor.close();
        return departmentslist;
    }

    public List<Drink> getAllDrink(){
        List<Drink> drinkList = new ArrayList<>();
        String queryString = "SELECT * FROM " + TABLE_DRINK;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,  null);
        if (cursor.moveToFirst()){
            do {
                int drinkid = cursor.getInt(0);
                String Name = cursor.getString(1);
                String TypeName = cursor.getString(2);
                byte[] image = cursor.getBlob(3);
                Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
                long money = cursor.getLong(4);
                long point = cursor.getLong(5);

                Drink drink = new Drink(drinkid,Name,TypeName,bitmap,money,point);
                drinkList.add(drink);
            }while (cursor.moveToNext());
        }else {}
        cursor.close();
        return drinkList;
    }
    public List<String> getDepartment(){
        List<String> list = new ArrayList<String>();

        String Query = "SELECT * FROM " +TABLE_DEPARTMENT;

        SQLiteDatabase db1 = this.getReadableDatabase();
        Cursor cursor = db1.rawQuery(Query,null);

        if (cursor.moveToFirst()){
            do{
                list.add(cursor.getString(1));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db1.close();
        return list;
    }
}
