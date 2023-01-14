package com.octaneocatane.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AlarmListDao {

    @Query("SELECT * FROM alarm_items")
    fun getAlarmList(): LiveData<List<AlarmItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAlarm(alarmItemDbModel: AlarmItemDbModel)

    @Query("DELETE FROM alarm_items WHERE id=:alarmItemId")
    suspend fun deleteAlarm(alarmItemId: Int)

    @Query("SELECT * FROM alarm_items WHERE id=:alarmItemId LIMIT 1")
    suspend fun getAlarm(alarmItemId: Int): AlarmItemDbModel
}