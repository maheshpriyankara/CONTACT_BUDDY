package com.example.contactbuddy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

private lateinit var name:EditText
    private lateinit var phone:EditText
    private lateinit var delete:ImageView
    private lateinit var edit:ImageView
    private lateinit var db:DBHelper


class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        name=findViewById(R.id.editTextPersonName)
        phone=findViewById(R.id.editTextPhone)
        delete=findViewById(R.id.imageView3)
        edit=findViewById(R.id.imageView4)
        db= DBHelper(this)

        name.setText(intent.getStringExtra("name"))
        phone.setText(intent.getStringExtra("phone"))

        delete.setOnClickListener{
            val names=name.text.toString()
            val deletedata=db.deleteuserdata(names)


                if (deletedata){
                    Toast.makeText(this,"Delete Contact Successfully", Toast.LENGTH_SHORT).show()
                    name.setText("")
                    phone.setText("")
                }else{
                    Toast.makeText(this,"Not Delete", Toast.LENGTH_SHORT).show()
                }

        }
        edit.setOnClickListener{
            val names=name.text.toString()
            val numbers= phone.text.toString()
            val updatedata=db.updateuserdata(names,numbers)


            if (updatedata){
                Toast.makeText(this,"Contact updated Successfully", Toast.LENGTH_SHORT).show()
                name.setText("")
                phone.setText("")
            }else{
                Toast.makeText(this,"Not Updated", Toast.LENGTH_SHORT).show()
            }
        }

    }
}