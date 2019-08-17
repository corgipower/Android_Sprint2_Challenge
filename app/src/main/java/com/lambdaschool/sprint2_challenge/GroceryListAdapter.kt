package com.lambdaschool.sprint2_challenge

import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.grocery_list_item.view.*

class GroceryListAdapter(val groceryList: MutableList<Groceries>) : RecyclerView.Adapter<GroceryListAdapter.ViewHolder>() {
    override fun getItemCount(): Int {
        return groceryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val grocery = groceryList[position]
        holder.bindModel(grocery)

        holder.groceryListParent.setOnClickListener {
            grocery.isSelected = !grocery.isSelected
            notifyItemChanged(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.grocery_list_item, parent, false) as View)
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val groceryIconView: ImageView = view.grocery_list_icon
        val groceryTextView: TextView = view.grocery_list_text
        val groceryListParent: LinearLayout = view.grocery_list_parent

        fun bindModel(grocery: Groceries) {
            groceryIconView.setImageResource(grocery.iconId)
            groceryTextView.text = grocery.itemName
            if (grocery.isSelected) {
                groceryListParent.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.colorAccent))
            }
        }
    }
}