package com.kodego.app.todoapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDo(
    var name:String,
    var desc:String
    ){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}

