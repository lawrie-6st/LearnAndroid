package com.example.fragmentsample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

/**
 * A simple [Fragment] subclass.
 */
class MenuThanksFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu_thanks, container, false)
        val extras: Bundle?
        if (_isLayoutLarge) {
            extras = arguments
        } else {
            val intent = activity?.intent
            extras = intent?.extras
        }

        val menuName = extras?.getString("menuName")
        val menuPrice = extras?.getString("menuPrice")
        val tvMenuName = view.findViewById<TextView>(R.id.tvMenuName)
        val tvMenuPrice = view.findViewById<TextView>(R.id.tvMenuPrice)
        tvMenuName.text = menuName
        tvMenuPrice.text = menuPrice
        // Inflate the layout for this fragment
        val btBackButton = view.findViewById<Button>(R.id.bt_BackButton)
        btBackButton.setOnClickListener(ButtonClickListener())
        return view
    }

    private inner class ButtonClickListener : View.OnClickListener {
        override fun onClick(view: View) {
            if (_isLayoutLarge) {
                val transaction = fragmentManager?.beginTransaction()
                transaction?.remove(this@MenuThanksFragment)
                transaction?.commit()
            } else {
                activity?.finish()
            }
        }
    }

    private var _isLayoutLarge = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val menuListFragment = fragmentManager?.findFragmentById(R.id.fragmentMenuList)
        if (menuListFragment == null) {
            _isLayoutLarge = false
        }
    }


}
