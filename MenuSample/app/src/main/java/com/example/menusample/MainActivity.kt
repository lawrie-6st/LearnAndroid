package com.example.menusample

import android.app.LauncherActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast

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
        registerForContextMenu(lvMenu)
    }
    private fun createTeisyokuList(): MutableList<MutableMap<String, *>> {
        val menuList: MutableList<MutableMap<String,*>> = mutableListOf()
        var menu = mutableMapOf("name" to "からあげ定食", "price" to 800, "desc" to "若鳥の唐揚げにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "からあげ", "price" to 800,"desc" to "若鳥の唐揚げにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)

        return menuList
    }
    private fun createCurryList(): MutableList<MutableMap<String,*>> {
        val menuList: MutableList<MutableMap<String,*>> = mutableListOf()
        var menu = mutableMapOf("name" to "ポークカレー","price" to 420, "desc" to "特選スパイスを効かせた国産ポーク１００％のカレーです。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "ビーフカレー","price" to 420, "desc" to "特選スパイスを効かせた国産ビーフ１００％のカレーです。")
        menuList.add(menu)
        return menuList
    }
    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            val item = parent.getItemAtPosition(position) as MutableMap<String, *>
            order(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_option_menu_list,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menuListOptionTeishoku ->
                _menuList = createTeisyokuList()
            R.id.menuListOptionCurry ->
                _menuList = createCurryList()
        }
        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        val adapter = SimpleAdapter(applicationContext,_menuList,R.layout.row,FROM,TO)
        lvMenu.adapter = adapter
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu_context_menu_list,menu)
        menu.setHeaderTitle(R.string.menu_list_context_header)

    }

    private fun order(menu: MutableMap<String,*>) {
        val menuName = menu["name"] as String
        val menuPrice = menu["price"] as Int
        val intent = Intent(applicationContext,MenuThanksActivity::class.java)
        intent.putExtra("menuName",menuName)
        intent.putExtra("menuPrice", "${menuPrice}円")
        startActivity(intent)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val listPosition = info.position
        val menu = _menuList!![listPosition]

        when(item.itemId){
            R.id.menuListContextDesc -> {
                val desc = menu["desc"] as String
                Toast.makeText(applicationContext,desc,Toast.LENGTH_LONG).show()
            }
            R.id.menuListContextOrder -> {
                order(menu)
            }
        }

        return super.onContextItemSelected(item)
    }


}
