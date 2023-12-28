package com.example.contactbuddy

import android.content.Intent
import android.database.Cursor
import android.icu.text.Transliterator.Position
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var button: FloatingActionButton
    lateinit var dbh:DBHelper
    private lateinit var newArray:ArrayList<DataList>
    private lateinit var searchEditText: EditText
    private lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView=findViewById(R.id.recycler)
        button=findViewById(R.id.floatingActionButton)

        button.setOnClickListener {
            intent= Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }
        dbh= DBHelper(this)
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        displayuser()


        searchEditText = findViewById(R.id.searchEditText)

        // Set up the search functionality
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Filter the list based on the search query
                filterList(charSequence.toString())
            }

            override fun afterTextChanged(editable: Editable?) {}
        })
    }
    private fun filterList(query: String) {
        val filteredList = newArray.filter {
            it.name.contains(query, ignoreCase = true) || it.contact.contains(query)
        }
        adapter.updateList(filteredList)
    }
    override fun onResume() {
        super.onResume()
        // Call the displayuser() method when the activity is resumed
        displayuser()
    }

    private  fun displayuser(){
        Log.d("mmm","back home")
        val newcursor:Cursor=dbh.gettext()
        newArray= ArrayList<DataList>()
        while (newcursor!!.moveToNext()){
            val uname=newcursor.getString(0)
            val unumber=newcursor.getString(1)
            newArray.add(DataList(uname,unumber))
        }
        adapter=MyAdapter(newArray)
        recyclerView.adapter=adapter
        adapter.onItemClickListener(object :MyAdapter.onitemClickListener{
            override fun onItemClick(position: Int) {
               val intent = Intent(this@MainActivity,MainActivity3::class.java)
                intent.putExtra("name",newArray[position].name)
                intent.putExtra("phone",newArray[position].contact)
                startActivity(intent)

            }

        })
    }




    private fun showToast(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
    }
}
