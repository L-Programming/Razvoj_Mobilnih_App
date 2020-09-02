package barcic.funpark.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import barcic.funpark.model.Stroj;

@Dao
public interface StrojDAO {

    @Query("select * from Stroj")
    LiveData<List<Stroj>> dohvatiStrojeve();

    @Insert
    void dodajNoviStroj (Stroj stroj);

    @Update
    void promjeniStroj(Stroj stroj);


    @Delete
    void obrisiStroj (Stroj stroj);

}
