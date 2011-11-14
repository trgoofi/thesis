/**
 *
 */
package com.trgoofi.solr;

import java.net.MalformedURLException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hui
 *
 */
public class SolrServerFactory {
  private static final Logger LOG = LoggerFactory
      .getLogger(SolrServerFactory.class);
  
  private static SolrServer solrServer;

  static {
    try {
      String url = SolrProperties.getSolrServerAddress();
      solrServer = new CommonsHttpSolrServer(url);
    } catch (MalformedURLException e) {
      String errMsg = "instantiate solr server failure!";
      LOG.error(errMsg, e);
      throw new RuntimeException(errMsg, e);
    }
  }

  public static SolrServer getSolrServer() {
    return solrServer;
  }
}
