package com.linetest.dialogmanycallback

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.dialog_test.*

class TestDialog(context : Context) : Dialog(context) {
    var adapter : DialogAdapter?= null
    var listItems : ArrayList<CheckList> = ArrayList()
    var listener : ItemClickListener<String> ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_test)

        setCanceledOnTouchOutside(true) // false면 밖 터치할 때 다이얼로그가 없어짐

        init()
    }
    private fun init() {
        window?.apply {
            setBackgroundDrawable(ColorDrawable())
            setDimAmount(0.5f) // 다이얼로그 뒤 배경색 지우기, 숫자는 %만큼 투명도랄까?
            val attr = attributes
            attr.width = ViewGroup.LayoutParams.MATCH_PARENT
            attr.height = ViewGroup.LayoutParams.WRAP_CONTENT
            attributes = attr
        }

        adapter = DialogAdapter(context, listItems)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        listener?.apply {
            adapter?.setOnClickListenr(object : ItemClickListener<String> {
                override fun onClick(position: Int, item: String) {
                    listener?.onClick(position, item)
                    dismiss()
                }
            })
        }
    }

    class Builder(context:Context) {
        private val dialog = TestDialog(context)

        fun setData(listItems: List<String>) : Builder {
            listItems.apply {
                dialog.listItems.clear()
                dialog.listItems.addAll(listItems.map { CheckList(it) })
            }
            return this
        }
        fun setOnItemClickListener(listener : ItemClickListener<String>) : Builder {
            dialog.listener = listener
            return this
        }
        fun show() : TestDialog {
            dialog.show()
            return dialog
        }
    }
}