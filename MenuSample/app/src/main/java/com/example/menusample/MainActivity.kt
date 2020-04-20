package com.example.menusample

import android.app.LauncherActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter

class MainActivity : AppCompatActivity() {
    private var _menuList: MutableList<MutableMap<String,*>>? = null
    private var FROM  = arrayOf("name","price")
    private var TO = intArrayOf(R.id.tvMenuName,R.id.tvMenuPrice)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        _menuList = createTeisyokuList()
        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        val adapter = SimpleAdapter(applicationContext,_menuList,R.layout.row,FROM,TO)
        lvMenu.adapter = adapter
        lvMenu.onItemClickListener = ListItemClickListener()
    }
    private fun createTeisyokuList(): MutableList<MutableMap<String, *>> {
        val menuList: MutableList<MutableMap<String,*>> = mutableListOf()
        var menu = mutableMapOf("name" to "からあげ定食", "price" to 800, "desc" to "若鳥の唐揚げにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "からあげ", "price" to 800,"desc" to "若鳥の唐揚げにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)

        return menuList
    }
    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            val item = parent.getItemAtPosition(position) as MutableMap<String, Any>
            val menuName = item["name"] as String
            val menuPrice = item["price"] as Int
            val intent = Intent(applicationContext,MenuThanksActivity::class.java)
            intent.putExtra("menuName",menuName)
            intent.putExtra("menuPrice", "${menuPrice}円")
            startActivity(intent)
        }
    }
}
