package com.valeriapaz.todolist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_todo.*

/*
Project done by following YouTube tutorial:
https://www.youtube.com/watch?v=BBWyXo-3JGQ
https://www.youtube.com/watch?v=wtpRp2IpCSo&t=335s
 */

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: ToDoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Here I tried to implement Shared Preferences code, but when I click on Load, it crashes =(


        val sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        btnSave.setOnClickListener {
            val savedText = etToDoTitle.text.toString()
            val isDone = cbDone.isChecked

            editor.apply {
                putString("todo", savedText)
                putBoolean("isDone", isDone)
                apply()
            }
        }

        btnLoad.setOnClickListener {
            val savedText = sharedPref.getString("todo", null)
            val isDone = sharedPref.getBoolean("isDone", false)

            etToDoTitle.setText(savedText)
            cbDone.isChecked = isDone
        }

        //-----------------------------------------------------------------
        todoAdapter = ToDoAdapter(mutableListOf())

        rvToDoList.adapter = todoAdapter
        rvToDoList.layoutManager = LinearLayoutManager(this)

        btnAddToDo.setOnClickListener {
            val todoTitle = etToDoTitle.text.toString()
            if (todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addToDo(todo)
                etToDoTitle.text.clear()
            }
        }

        btnDeleteDoneToDos.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }
}