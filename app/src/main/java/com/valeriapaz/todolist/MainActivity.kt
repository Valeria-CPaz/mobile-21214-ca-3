package com.valeriapaz.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

/*
Project done by following YouTube tutorial:
https://www.youtube.com/watch?v=BBWyXo-3JGQ
 */

class MainActivity : AppCompatActivity() {

    private  lateinit var todoAdapter: ToDoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = ToDoAdapter(mutableListOf())

        rvToDoList.adapter = todoAdapter
        rvToDoList.layoutManager = LinearLayoutManager(this)

        btnAddToDo.setOnClickListener {
            val todoTitle = etToDoTitle.text.toString()
            if(todoTitle.isNotEmpty()) {
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