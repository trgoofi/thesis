package com.trgoofi.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author trgoofi
 * 
 */
public class IOUtil {
  private IOUtil() {}
  
  public static void closeQuietly(Closeable... closeables) {
    if (null == closeables) return;
    
    for (Closeable closeable : closeables) {
      try {
        if(null != closeable) closeable.close();
      } catch (IOException ignore) {}
    }
    
  }
}
