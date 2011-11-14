/**
 *
 */
package com.trgoofi.domin;

import java.util.Collections;
import java.util.List;

/**
 * @author hui
 *
 */
public class HitModel {
  private long totalHits;
  private List<Hit> hits = Collections.emptyList();

  public long getTotalHits() {
    return totalHits;
  }

  public void setTotalHits(long totalHits) {
    this.totalHits = totalHits;
  }

  public List<Hit> getHits() {
    return hits;
  }

  public void setHits(List<Hit> hits) {
    this.hits = hits;
  }

}
