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
import androidx.sqlite.db.SupportSQLiteDatabase
import ru.evteev.sportapp.domain.Sport
import java.util.UUID

@Dao
interface SportDAO {
    @Query("SELECT * FROM sports")
    fun getSports() : LiveData<List<Sport>>
    @Query("SELECT * FROM sports WHERE id = (:id)")
    fun getSport(id: UUID) : LiveData<Sport>
    @Update()
    fun updateSport(sport: Sport);
    @Insert()
    fun addSport(sport: Sport)
}

@Database(entities = [Sport::class], version = 2, exportSchema = true)
abstract class SportDatabase : RoomDatabase(){
    abstract fun sportDao() : SportDAO

    companion object {
        @Volatile
        private var INSTANCE: SportDatabase? = null;

        fun get(context: Context): SportDatabase {
            if(INSTANCE != null) {
                return INSTANCE as SportDatabase;
            }
            INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    SportDatabase::class.java,
                    "sport_db"
            )
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        println("DB created");
                    }
                })
                .fallbackToDestructiveMigration()
                .build();
            return INSTANCE!!;
        }
    }
}