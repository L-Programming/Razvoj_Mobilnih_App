package barcic.funpark.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import barcic.funpark.model.Stroj;

@Database(entities = {Stroj.class}, version = 1, exportSchema = false)
//@TypeConverters({DateConverter.class})
public abstract class StrojBaza extends RoomDatabase {

    public abstract StrojDAO StrojDAO();
    private static StrojBaza INSTANCE;

    public static StrojBaza getBaza (Context context){
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), StrojBaza.class, "stroj-baza").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }
}
