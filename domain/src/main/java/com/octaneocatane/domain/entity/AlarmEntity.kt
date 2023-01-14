package com.octaneocatane.domain.entity

data class AlarmEntity(
    val id: Int = UNDEFINED_ID,
    val time: Int,
    val description: String
) {
    companion object{
        const val UNDEFINED_ID = 0
    }
}
