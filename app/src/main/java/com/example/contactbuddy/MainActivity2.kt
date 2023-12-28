package com.example.contactbuddy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity2 : AppCompatActivity() {

    private lateinit var name:TextInputEditText
    private lateinit var phone:TextInputEditText
    private lateinit var save:Button
    private lateinit var db:DBHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        name=findViewById(R.id.textedit)
        phone=findViewById(R.id.textedit2)
        save=findViewById(R.id.button)

        db= DBHelper(this)
        save.setOnClickListener(){
            val names=name.text.toString()
            val numbers=phone.text.toString()
            val saved=db.saveuserdata(names,numbers)

            Toast.makeText(this,saved.toString(),Toast.LENGTH_SHORT)
            if (TextUtils.isEmpty(names) || TextUtils.isEmpty(numbers))
            {
                Toast.makeText(this,"Add Name & Phone Number",Toast.LENGTH_SHORT).show()

            }else{
                if (saved){
                    Toast.makeText(this,"Save Contact",Toast.LENGTH_SHORT).show()
                    name.setText("")
                    phone.setText("")
                }else{
                    Toast.makeText(this,"Exist Contact",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}