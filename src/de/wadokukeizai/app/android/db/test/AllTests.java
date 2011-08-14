/**
 * Wadoku Keizai Android Application
 * Copyright (C) 2011 WadokuKeizai All Rights Reserved.
 * http://www.wadokukeizai.de/
 */
package de.wadokukeizai.app.android.db.test;

import junit.framework.Test;
import junit.framework.TestSuite;
import android.test.suitebuilder.TestSuiteBuilder;

public class AllTests extends TestSuite {
  public static Test suite() {
    return new TestSuiteBuilder(AllTests.class)
        .includeAllPackagesUnderHere()
        .build();
  }
}
