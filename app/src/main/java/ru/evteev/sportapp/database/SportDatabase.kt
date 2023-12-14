package ru.evteev.sportapp.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import ru.evteev.sportapp.domain.Sport
import java.util.UUID

@Dao
interface SportDAO {
    @Query("SELECT * FROM sport")
    fun getSports() : LiveData<List<Sport>>
    @Query("SELECT * FROM sport WHERE id = (:id)")
    fun getSport(id: UUID) : LiveData<Sport>
    @Update()
    fun updateSport(sport: Sport);
    @Insert()
    fun addSport(sport: Sport)
}

@Database(entities = [Sport::class], version = 1)
abstract class SportDatabase : RoomDatabase(){
    abstract fun sportDao() : SportDAO

    companion object {
        @Volatile
        private var INSTANCE: SportDatabase? = null;

        fun get(context: Context): SportDatabase {
            val temp = INSTANCE;
            if(temp != null) {
                return temp;
            }

            synchronized(this) {
                val inst = Room.databaseBuilder(
                    context.applicationContext,
                    SportDatabase::class.java,
                    "sport_db"
                ).build();

                INSTANCE = inst;
                return inst;
            }
        }
    }
}