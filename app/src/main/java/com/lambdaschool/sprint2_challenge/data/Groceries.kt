package com.lambdaschool.sprint2_challenge.data

import java.io.Serializable

data class Groceries(
        val itemName: String,
        val iconId: Int,
        var isSelected: Boolean
): Serializable