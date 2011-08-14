/**
 * Wadoku Keizai Android Application
 * Copyright (C) 2011 WadokuKeizai All Rights Reserved.
 * http://www.wadokukeizai.de/
 */
package de.wadokukeizai.app.android.db.test;

import java.io.IOException;

import org.apache.commons.logging.Log;

import de.wadokukeizai.app.android.libs.AndroidLog;
import de.wadokukeizai.app.db.WDKDBHelper;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

public class WDKDBHelperTest extends AndroidTestCase {

  static final String TABLENAME = "dictionary";

  private WDKDBHelper mDbHelper;
  private SQLiteDatabase db;
  private Context mContext;
  private Log log;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    mContext = getContext();
    mDbHelper = new WDKDBHelper(mContext);
    log = new AndroidLog(WDKDBHelperTest.class.getCanonicalName());
    mDbHelper.setLog(log);
    db = mDbHelper.openDatabase();
  }

  public WDKDBHelperTest() {
    new WDKDBHelper(mContext);
  }

  public void testWDKDBHelper() {
  }

  public void testCreateEmptyDataBase() {
    try {
      mDbHelper.createEmptyDataBase();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testCheckDataBaseExists() {
    boolean result = mDbHelper.checkDataBaseExists();
    assertTrue(result);
  }

  public void testOpenDatabase() {
    assertNotNull(mDbHelper.openDatabase());
  }

  public void testOnCreateSQLiteDatabase() {
    mDbHelper.onCreate(null);
  }

  public void testOnUpgradeSQLiteDatabaseIntInt() {
    mDbHelper.onUpgrade(db, 1, 1);
  }

  private static final String[] COLUMNS = { "genera", "de", "kanji",
      "hiragana", "romaji" };

  public void testFindData() {
    Cursor cursor =
        // db.query(TABLENAME, COLUMNS, "genera like '%e%'", null, "", "", "");
        db
            .rawQuery(
                "select * from dictionary where romaji like '%e%';",
                null);
    log.warn("cursor count: " + cursor.getCount());

    for (int i = 0; i < cursor.getCount(); i++) {
      cursor.moveToPosition(i);
      log.info("data: " + cursor.getString(0) + ", " + cursor.getString(1)
          + ", " + cursor.getString(2) + ", " + cursor.getString(3) + ", "
          + cursor.getString(4));
    }

    cursor.close();
  }

}
