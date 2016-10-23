package com.vonzhou.learn.luceneinaction2.indexing;



import com.vonzhou.learn.luceneinaction2.common.TestUtil;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;

import junit.framework.TestCase;
import java.io.IOException;
import java.io.File;

// From chapter 2
public class LockTest extends TestCase {

  private Directory dir;
  private File indexDir;

  protected void setUp() throws IOException {
    indexDir = new File(
      System.getProperty("java.io.tmpdir", "tmp") +
      System.getProperty("file.separator") + "index");
    dir = FSDirectory.open(indexDir);
  }

  public void testWriteLock() throws IOException {

    IndexWriter writer1 = new IndexWriter(dir, new SimpleAnalyzer(),
                                          IndexWriter.MaxFieldLength.UNLIMITED);
    IndexWriter writer2 = null;
    try {
      writer2 = new IndexWriter(dir, new SimpleAnalyzer(),
                                IndexWriter.MaxFieldLength.UNLIMITED);
      fail("We should never reach this point");
    }
    catch (LockObtainFailedException e) {
      // e.printStackTrace();  // #A
    }
    finally {
      writer1.close();
      assertNull(writer2);
      TestUtil.rmDir(indexDir);
    }
  }
}

/*
#A Expected exception: only one IndexWriter allowed at once
*/
