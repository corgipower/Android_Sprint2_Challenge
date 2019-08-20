package com.lambdaschool.sprint2_challenge.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.lambdaschool.sprint2_challenge.R
import com.lambdaschool.sprint2_challenge.data.Groceries
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.activity_item_detail.view.*

class ItemDetailActivity : AppCompatActivity() {

    companion object {
        const val DETAIL_KEY = "GROCERY_DETAIL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        val itemDetail = intent.getSerializableExtra("DETAIL_KEY") as Groceries

        img_item_detail.setImageDrawable(ContextCompat.getDrawable(this, itemDetail.iconId))
        tv_item_detail.text = itemDetail.itemName
    }
}
