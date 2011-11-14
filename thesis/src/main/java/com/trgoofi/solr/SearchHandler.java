package com.trgoofi.solr;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trgoofi.domin.Hit;
import com.trgoofi.domin.HitModel;

public class SearchHandler {
  private static final Logger LOG = LoggerFactory
      .getLogger(SearchHandler.class);

  private SolrServer solrServer = SolrServerFactory.getSolrServer();

  public HitModel search(String q, int start, int rows) {
    SolrQuery sq = new SolrQuery();
    configHighight(sq);
    sq.setQuery(q).setStart(start).setRows(rows);
    HitModel pageModel = new HitModel();
    try {
      QueryResponse rsp = solrServer.query(sq);
      SolrDocumentList docs = rsp.getResults();
      pageModel.setTotalHits(docs.getNumFound());

      List<Hit> hits = rsp.getBeans(Hit.class);
      postProcess(rsp, hits);
      pageModel.setHits(hits);
    } catch (SolrServerException e) {
      LOG.error("Solr search error", e);
    }
    return pageModel;
  }

  private void postProcess(QueryResponse rsp, List<Hit> hits) {
    for (Hit hit : hits) {
      List<String> titles = rsp.getHighlighting().get(hit.getId())
          .get("title");
      if (null != titles && titles.size() > 0) {
        hit.setTitle(titles.get(0));
      }

      List<String> contents = rsp.getHighlighting().get(hit.getId())
          .get("content");
      if (null != contents && contents.size() > 0) {
        hit.setContent(contents.get(0));
      } else {
        hit.setContent(hit.getContent().substring(0, 150));
      }

    }
  }

  private void configHighight(SolrQuery sq) {
    sq.setHighlight(true)
      .addHighlightField("title")
      .addHighlightField("content")
      .setHighlightSimplePre("<span style='color: red'>")
      .setHighlightSimplePost("</span>");
  }
}
