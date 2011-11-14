package com.trgoofi.util;

import org.apache.commons.lang.StringUtils;

/**
 * @author trgoofi
 * 
 */
public class DefaultGet {
  private DefaultGet() {}
  
  public static String get(String source, String defaultValue) {
    return StringUtils.isBlank(source) ? defaultValue : source;
  }
}
