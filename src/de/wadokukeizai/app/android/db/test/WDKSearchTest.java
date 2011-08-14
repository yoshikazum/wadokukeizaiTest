/**
 * Wadoku Keizai Android Application
 * Copyright (C) 2011 WadokuKeizai All Rights Reserved.
 * http://www.wadokukeizai.de/
 */
package de.wadokukeizai.app.android.db.test;

import org.apache.commons.logging.Log;

import de.wadokukeizai.app.android.WadokukeizaiActivity;
import de.wadokukeizai.app.android.libs.AndroidLog;
import de.wadokukeizai.app.db.WDKDBHelper;
import de.wadokukeizai.app.db.WDKSearch;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

public class WDKSearchTest extends AndroidTestCase {
  private WDKDBHelper mDbHelper;
  private WDKSearch search;
  private Context mContext;
  private SQLiteDatabase db;
  private Log log;

  public WDKSearchTest() {
    super();
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    mContext = getContext();
    mDbHelper = new WDKDBHelper(mContext);
    log = new AndroidLog(WadokukeizaiActivity.class.getSimpleName());
    mDbHelper.setLog(log);
    db = mDbHelper.openDatabase();
    search = new WDKSearch(db);
  }

  public void testSearch() {
    Cursor cursor = search.search("eb");

    log.info("cursor count: " + cursor.getCount());
    cursor.moveToFirst();
    for (int i = 0; i < cursor.getCount(); i++) {
      log.debug("data: " + cursor.getString(0) + ", " + cursor.getString(1)
          + ", " + cursor.getString(2) + ", " + cursor.getString(3) + ", "
          + cursor.getString(4));
      cursor.moveToNext();
    }
  }
}
