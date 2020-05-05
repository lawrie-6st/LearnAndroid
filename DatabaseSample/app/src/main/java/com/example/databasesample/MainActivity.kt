package com.example.databasesample

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var _cocktailId = -1
    private var _cocktailName = ""
    private var _helper = DatabaseHelper(this@MainActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val lvCocktail = findViewById<ListView>(R.id.lvCocktail)
        lvCocktail.onItemClickListener = ListItemClickListener()
    }
    fun onSaveButtonClick(view: View) {
        val etNote = findViewById<EditText>(R.id.etNote)
        val note = etNote.text.toString()
        val db = _helper.writableDatabase
        val sqlDelete = "DELETE FROM cocktailmemos WHERE _id = ?"
        var stmt = db.compileStatement(sqlDelete)
        stmt.bindLong(1,_cocktailId.toLong())
        stmt.executeUpdateDelete()
        val sqlInsert = "INSERT INTO cocktailmemos (_id, name, note) VALUES(?,?,?)"
        stmt = db.compileStatement(sqlInsert)
        stmt.bindLong(1,_cocktailId.toLong())
        stmt.bindString(2,_cocktailName)
        stmt.bindString(3,note)
        stmt.executeInsert()
        etNote.setText("")
        val tvCocktailName = findViewById<TextView>(R.id.tvCocktailName)
        tvCocktailName.text = getString(R.string.tv_name)
        val btnSave = findViewById<Button>(R.id.btnSave)
        btnSave.isEnabled = false
    }
    private inner class ListItemClickListener :AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            _cocktailId = position
            _cocktailName = parent.getItemAtPosition(position) as String
            val  tvCocktilName = findViewById<TextView>(R.id.tvCocktailName)
            tvCocktilName.text = _cocktailName
            val btnSave = findViewById<Button>(R.id.btnSave)
            btnSave.isEnabled = true

            val db = _helper.writableDatabase
            val sql = "SELECT * FROM cocktailmemos WHERE _id = ${_cocktailId}"
            val cursor = db.rawQuery(sql,null)
            var note = ""
            while(cursor.moveToNext()) {
                val idxNote = cursor.getColumnIndex("note")
                note = cursor.getString(idxNote)
            }
            val etNote = findViewById<EditText>(R.id.etNote)
            etNote.setText(note)
        }
    }

    override fun onDestroy() {
        _helper.close()
        super.onDestroy()
    }
}