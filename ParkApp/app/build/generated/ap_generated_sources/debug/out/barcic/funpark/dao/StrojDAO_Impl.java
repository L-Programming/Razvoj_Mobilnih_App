package barcic.funpark.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.lifecycle.ComputableLiveData;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.InvalidationTracker.Observer;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import barcic.funpark.model.Stroj;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unchecked")
public final class StrojDAO_Impl implements StrojDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfStroj;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfStroj;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfStroj;

  public StrojDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfStroj = new EntityInsertionAdapter<Stroj>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `stroj`(`id`,`Stroj`,`godinaProizvodnje`,`mjestoProizvodnje`,`putanjaSlika`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Stroj value) {
        stmt.bindLong(1, value.getId());
        if (value.getNazivStroja() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNazivStroja());
        }
        if (value.getGodinaProizvodnje() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getGodinaProizvodnje());
        }
        if (value.getMjestoProizvodnje() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getMjestoProizvodnje());
        }
        if (value.getPutanjaSlika() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPutanjaSlika());
        }
      }
    };
    this.__deletionAdapterOfStroj = new EntityDeletionOrUpdateAdapter<Stroj>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `stroj` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Stroj value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfStroj = new EntityDeletionOrUpdateAdapter<Stroj>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `stroj` SET `id` = ?,`Stroj` = ?,`godinaProizvodnje` = ?,`mjestoProizvodnje` = ?,`putanjaSlika` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Stroj value) {
        stmt.bindLong(1, value.getId());
        if (value.getNazivStroja() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNazivStroja());
        }
        if (value.getGodinaProizvodnje() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getGodinaProizvodnje());
        }
        if (value.getMjestoProizvodnje() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getMjestoProizvodnje());
        }
        if (value.getPutanjaSlika() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPutanjaSlika());
        }
        stmt.bindLong(6, value.getId());
      }
    };
  }

  @Override
  public void dodajNoviStroj(Stroj stroj) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfStroj.insert(stroj);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void obrisiStroj(Stroj stroj) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfStroj.handle(stroj);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void promjeniStroj(Stroj stroj) {
    __db.beginTransaction();
    try {
      __updateAdapterOfStroj.handle(stroj);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<Stroj>> dohvatiStrojeve() {
    final String _sql = "select * from Stroj";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<Stroj>>(__db.getQueryExecutor()) {
      private Observer _observer;

      @Override
      protected List<Stroj> compute() {
        if (_observer == null) {
          _observer = new Observer("Stroj") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfNazivStroja = _cursor.getColumnIndexOrThrow("Stroj");
          final int _cursorIndexOfGodinaProizvodnje = _cursor.getColumnIndexOrThrow("godinaProizvodnje");
          final int _cursorIndexOfMjestoProizvodnje = _cursor.getColumnIndexOrThrow("mjestoProizvodnje");
          final int _cursorIndexOfPutanjaSlika = _cursor.getColumnIndexOrThrow("putanjaSlika");
          final List<Stroj> _result = new ArrayList<Stroj>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Stroj _item;
            _item = new Stroj();
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            final String _tmpNazivStroja;
            _tmpNazivStroja = _cursor.getString(_cursorIndexOfNazivStroja);
            _item.setNazivStroja(_tmpNazivStroja);
            final String _tmpGodinaProizvodnje;
            _tmpGodinaProizvodnje = _cursor.getString(_cursorIndexOfGodinaProizvodnje);
            _item.setGodinaProizvodnje(_tmpGodinaProizvodnje);
            final String _tmpMjestoProizvodnje;
            _tmpMjestoProizvodnje = _cursor.getString(_cursorIndexOfMjestoProizvodnje);
            _item.setMjestoProizvodnje(_tmpMjestoProizvodnje);
            final String _tmpPutanjaSlika;
            _tmpPutanjaSlika = _cursor.getString(_cursorIndexOfPutanjaSlika);
            _item.setPutanjaSlika(_tmpPutanjaSlika);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }
}
