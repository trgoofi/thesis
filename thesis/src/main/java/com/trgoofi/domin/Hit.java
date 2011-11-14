/**
 *
 */
package com.trgoofi.domin;

import org.apache.solr.client.solrj.beans.Field;

/**
 * @author hui
 *
 */
public class Hit {
  @Field
  private String id;
  @Field
  private String title;
  @Field
  private String content;
  @Field
  private String url;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

}
