package com.example.roomdatabase.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import com.example.roomdatabase.R
import com.example.roomdatabase.db.StudentDB
import com.example.roomdatabase.entity.Student
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AdddstudentActivity : AppCompatActivity() {
    private lateinit var etFullName: TextInputEditText
    private lateinit var etAge: TextInputEditText
    private lateinit var etAddress: TextInputEditText
    private lateinit var rdoMale: RadioButton
    private lateinit var rdoFemale: RadioButton
    private lateinit var rdoOthers: RadioButton
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addstudent)

        etFullName = findViewById(R.id.etFullName)
        etAge = findViewById(R.id.etAge)
        etAddress = findViewById(R.id.etAddress)
        rdoMale = findViewById(R.id.rdoMale)
        rdoFemale = findViewById(R.id.rdoFemale)
        rdoOthers = findViewById(R.id.rdoOthers)
        btnSave = findViewById(R.id.btnSave)

        btnSave.setOnClickListener {
            saveStudent()
        }
    }

    private fun saveStudent() {
        val fullName = etFullName.text.toString()
        val age = etAge.text.toString().toInt()
        val address = etAddress.text.toString()
        var gender = ""
        when {
            rdoFemale.isSelected -> {
                gender = "Female"
            }
            rdoMale.isSelected -> {
                gender = "Male"
            }
            rdoOthers.isSelected -> {
                gender = "Others"
            }
        }

        val student = Student(fullName, age, gender, address)
        try {
            CoroutineScope(Dispatchers.IO).launch {
                StudentDB.getInstance(this@AdddstudentActivity).getStudentDAO().insertStudent(student)
                withContext(Main){
                    Toast.makeText(this@AdddstudentActivity, "Student Added", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (ex: Exception) {
            Toast.makeText(this, "Error ${ex.localizedMessage}", Toast.LENGTH_SHORT).show()
        }

    }
}