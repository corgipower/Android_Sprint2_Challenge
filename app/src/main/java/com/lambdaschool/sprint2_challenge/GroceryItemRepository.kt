package com.lambdaschool.sprint2_challenge

import com.lambdaschool.sprint2_challenge.ShoppingItemConstants.ICON_IDS
import com.lambdaschool.sprint2_challenge.ShoppingItemConstants.ITEM_NAMES_RAW

class GroceryItemRepository {
    companion object {
        val groceryList = mutableListOf<Groceries>()
        fun createPuppyList() {
            for (i in 0 until ICON_IDS.size) {
                groceryList.add(Groceries(ITEM_NAMES_RAW[i], ICON_IDS[i], false))
            }
        }
    }
}