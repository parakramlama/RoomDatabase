package com.example.roomdatabase.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.R
import com.example.roomdatabase.adapter.StudentAdapter
import com.example.roomdatabase.db.StudentDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewStudentsActivity : AppCompatActivity() {

    private lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_students)

        recyclerView = findViewById(R.id.recyclerView)

        CoroutineScope(Dispatchers.IO).launch {
//            val lstStudents = StudentDB(this@ViewStudentsActivity).getStudentDAO().getAllStudents()
            val lstStudents = StudentDB.getInstance(this@ViewStudentsActivity).getStudentDAO().getAllStudents()

            withContext(Main){
                recyclerView.adapter = StudentAdapter(this@ViewStudentsActivity,lstStudents)
                recyclerView.layoutManager = LinearLayoutManager(this@ViewStudentsActivity)
            }
        }
    }
}



