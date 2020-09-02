package barcic.funpark.dao;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public final class StrojBaza_Impl extends StrojBaza {
  private volatile StrojDAO _strojDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `stroj` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `Stroj` TEXT NOT NULL, `godinaProizvodnje` TEXT, `mjestoProizvodnje` TEXT, `putanjaSlika` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"4a7750a806864c6878ce282a654c0949\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `stroj`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsStroj = new HashMap<String, TableInfo.Column>(5);
        _columnsStroj.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsStroj.put("Stroj", new TableInfo.Column("Stroj", "TEXT", true, 0));
        _columnsStroj.put("godinaProizvodnje", new TableInfo.Column("godinaProizvodnje", "TEXT", false, 0));
        _columnsStroj.put("mjestoProizvodnje", new TableInfo.Column("mjestoProizvodnje", "TEXT", false, 0));
        _columnsStroj.put("putanjaSlika", new TableInfo.Column("putanjaSlika", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysStroj = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesStroj = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoStroj = new TableInfo("stroj", _columnsStroj, _foreignKeysStroj, _indicesStroj);
        final TableInfo _existingStroj = TableInfo.read(_db, "stroj");
        if (! _infoStroj.equals(_existingStroj)) {
          throw new IllegalStateException("Migration didn't properly handle stroj(barcic.funpark.model.Stroj).\n"
                  + " Expected:\n" + _infoStroj + "\n"
                  + " Found:\n" + _existingStroj);
        }
      }
    }, "4a7750a806864c6878ce282a654c0949", "4c60fee8a9315f1f4f652dde7be6a81f");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "stroj");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `stroj`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public StrojDAO StrojDAO() {
    if (_strojDAO != null) {
      return _strojDAO;
    } else {
      synchronized(this) {
        if(_strojDAO == null) {
          _strojDAO = new StrojDAO_Impl(this);
        }
        return _strojDAO;
      }
    }
  }
}
