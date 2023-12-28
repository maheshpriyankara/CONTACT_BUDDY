package com.example.contactbuddy

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context):SQLiteOpenHelper(context,"userData",null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table UserData(name TEXT primary key,contact TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists Userdata")
    }

    fun saveuserdata(name:String,contact:String):Boolean{
        val db=this.writableDatabase
        val cv=ContentValues()
        cv.put("name",name)
        cv.put("contact",contact)

        val result=db.insert("userdata",null,cv)
        if (result==-1 .toLong()) {
            return false
        }
        return true

    }
    fun updateuserdata(name:String,contact:String):Boolean{
        val db=this.writableDatabase
        val cv=ContentValues()

        cv.put("contact",contact)
        val cursor:Cursor=db.rawQuery("select * from Userdata where name= ? ", arrayOf(name))
       if ( cursor.count>0) {
           val result = db.update("userdata", cv, "name=?", arrayOf(name))
           return result != -1
       }
        else{
            return false
        }

    }
    fun deleteuserdata(name:String):Boolean{
        val db=this.writableDatabase
        val cursor:Cursor=db.rawQuery("select * from Userdata where name= ? ", arrayOf(name))
        if ( cursor.count>0) {
            val result = db.delete("userdata", "name=?", arrayOf(name))
            return result != -1
        }
        else{
            return false
        }

    }
    fun gettext():Cursor{
        val db=this.writableDatabase
        val cursor=db.rawQuery("select * from Userdata",null)
        return cursor
    }

}