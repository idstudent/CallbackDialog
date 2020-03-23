package com.linetest.dialogmanycallback

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var dialog: TestDialog.Builder ?= null
    private var listItems : ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

    }
    private fun init() {
        dialog = TestDialog.Builder(this@MainActivity)

        for(i in 0 until 5) {
            listItems.add(i.toString())
        }
        dialog?.apply {
            setData(listItems)
            setOnItemClickListener(object : ItemClickListener<String> {
                override fun onClick(position: Int, item: String) {
                    result.text = item
                    Toast.makeText(applicationContext, "pos : $position", Toast.LENGTH_SHORT).show()
                }
            })
        }
        dialog?.show()
    }
}
