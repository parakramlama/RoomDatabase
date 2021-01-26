package com.example.roomdatabase.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.roomdatabase.R
import com.example.roomdatabase.db.StudentDB
import com.example.roomdatabase.entity.Student
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateStudentActivity : AppCompatActivity() {

    private lateinit var etFullname : EditText
    private lateinit var etAge : EditText
    private lateinit var btnUpdate : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_student)

        etFullname = findViewById(R.id.etFullName)
        etAge = findViewById(R.id.etAge)
        btnUpdate = findViewById(R.id.btnUpdate)

        val intent = intent.getParcelableExtra<Student>("student")
        if (intent != null) {
            etFullname.setText(intent.fullName)
            etAge.setText(intent.age.toString())
        }

        btnUpdate.setOnClickListener {
            val student = Student(fullName = etFullname.text.toString(),age = etAge.text.toString().toInt())
            student.stdId = intent!!.stdId

            CoroutineScope(Dispatchers.IO).launch {
                StudentDB.getInstance(this@UpdateStudentActivity).getStudentDAO().updateStudent(student)
//                withContext(Main){
                startActivity(Intent(this@UpdateStudentActivity,ViewStudentsActivity::class.java))
//                }
            }
        }

    }
}

