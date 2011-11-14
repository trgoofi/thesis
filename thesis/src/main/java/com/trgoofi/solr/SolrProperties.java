package com.trgoofi.solr;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trgoofi.util.IOUtil;

/**
 * @author trgoofi
 * 
 */
public class SolrProperties {
  private static final Logger LOG = LoggerFactory
      .getLogger(SolrProperties.class);
  
  private static Properties properties = new Properties();
  
  static {
    InputStream in = SolrProperties.class
                      .getClassLoader().getResourceAsStream("solr.properties");
    try {
      properties.load(in);
    } catch (IOException e) {
      LOG.error("error! while loading file: solr.properties", e);
    } finally {
      IOUtil.closeQuietly(in);
    }
  }
  
  private static String getProperty(String key) {
    return properties.getProperty(key).trim();
  }

  public static String getSolrServerAddress() {
    return getProperty("solr.server.address");
  }
}
