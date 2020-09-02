package barcic.funpark.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity(tableName = "stroj")

public class Stroj implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    @NonNull
    @ColumnInfo(name = "Stroj")
    private String nazivStroja;
    private String godinaProizvodnje;
    private String mjestoProizvodnje;
    private String putanjaSlika;

}
