package com.trgoofi.util;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author trgoofi
 *
 * @param <E>
 */
public class Pagination<E> {
  private static final int DEFAULT_START_ITEM = 0;
  
  public static final int DEFAULT_ITEMS_PER_PAGE        = 10;
  public static final int DEFAULT_PAGES_TO_CURRENT_PAGE = 10;
  
  
  private int itemsPerPage       = DEFAULT_ITEMS_PER_PAGE;
  private int pagesToCurrentPage = DEFAULT_PAGES_TO_CURRENT_PAGE;
  
  private int startItem = DEFAULT_START_ITEM;
  private int totalItems;
  
  private List<E> items = Collections.emptyList();
  
  
  /*
  private Pagination() {}
  
  public Pagination(List<E> items, int totalItems) {
    
    this(items, totalItems, DEFAULT_START_ITEM, DEFAULT_ITEMS_PER_PAGE,
        DEFAULT_PAGES_TO_CURRENT_PAGE);
  }
  
  public Pagination(List<E> items, int totalItems, int startItem) {
    
    this(items, totalItems, startItem, DEFAULT_ITEMS_PER_PAGE,
        DEFAULT_PAGES_TO_CURRENT_PAGE);
  }
  
  public Pagination(List<E> items, int totalItems, int startItem,
                    int itemsPerPage) {
    
    this(items, totalItems, startItem, itemsPerPage,
        DEFAULT_PAGES_TO_CURRENT_PAGE);
    
  }
  
  public Pagination(List<E> items, int totalItems, int startItem,
                    int itemsPerPage, int pagesToCurrentPage){
    
    setItems(items);
    setTotalItems(totalItems);
    setStartItem(startItem);
    setItemsPerPage(itemsPerPage);
    setPagesToCurrentPage(pagesToCurrentPage);
  }
  */
  
  public Pagination(PaginationMaker<E> maker) {
    this.itemsPerPage       = maker.itemsPerPage;
    this.pagesToCurrentPage = maker.pagesToCurrentPage;
    this.totalItems         = maker.totalItems;
    this.startItem          = maker.startItem;
    this.items              = maker.items;
  }
  
  
  public static class PaginationMaker<E> {
    private int startItem          = Pagination.DEFAULT_START_ITEM;
    private int pagesToCurrentPage = Pagination.DEFAULT_PAGES_TO_CURRENT_PAGE;
    private int itemsPerPage       = Pagination.DEFAULT_ITEMS_PER_PAGE;
    
    private int totalItems;
    private List<E> items;

    public PaginationMaker() {}
    
    public PaginationMaker<E> startItem(int startItem) {
      this.startItem = startItem;
      return this;
    }
    
    public PaginationMaker<E> pagesToCurrentPage(int pagesToCurrentPage) {
      this.pagesToCurrentPage = pagesToCurrentPage;
      return this;
    }
    
    public PaginationMaker<E> itemsPerPage(int itemsPerPage) {
      this.itemsPerPage = itemsPerPage;
      return this;
    }
    
    public PaginationMaker<E> totalItems(int totalItems) {
      this.totalItems = totalItems;
      return this;
    }
    
    public PaginationMaker<E> items(List<E> items) {
      this.items = items;
      return this;
    }
    
    public Pagination<E> make() {
      return new Pagination<E>(this);
    }
    
  }
  
     
  public static int calculateStartItemInPage(int page, int itemsPerPage) {
    page = (--page < 0) ? 0 : page;
    return page * itemsPerPage;
  }
  
  public static int calculateCurrentPage(int startItem, int itemsPerPage) {
    return startItem / itemsPerPage + 1;
  }
  
  public static int calculateTotalPages(int totalItems, int itemsPerPage) {
    int totalPages;
    if (totalItems > 0) {
      totalPages = totalItems / itemsPerPage;
      if (totalItems % itemsPerPage > 0) {
        ++totalPages;
      }
    } else {
      totalPages = 0;
    }
    return totalPages;
  }
  
    
  
  public int getStartPage() {
    int startPage = getCurrentPage() - getPagesToCurrentPage();
    return startPage < 0 ? 1 : startPage;
  }
  
  public int getEndPage() {
    int endPage = getCurrentPage() + getPagesToCurrentPage() - 1;
    return endPage > getTotalPages() ? getTotalPages() : endPage;
  }

  public boolean hasPrevPage(){
    return getCurrentPage() > 1;
  }
  
  public boolean hasNextPage() {
    return getCurrentPage() < getTotalPages();
  }

  public int getTotalPages() {
    return calculateTotalPages(getTotalItems(), getItemsPerPage());
  }
  public int getCurrentPage() {
    return calculateCurrentPage(getStartItem(), getItemsPerPage());
  }

  
  //>>>>>>>>>>>>> Getter Setter >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
  
  public int getItemsPerPage() {
    return itemsPerPage;
  }
  
  public void setItemsPerPage(int itemsPerPage) {
    this.itemsPerPage = itemsPerPage;
  }
  
  public int getPagesToCurrentPage() {
    return pagesToCurrentPage;
  }
  
  public void setPagesToCurrentPage(int pagesToCurrentPage) {
    this.pagesToCurrentPage = pagesToCurrentPage;
  }
  
  public int getTotalItems() {
    return totalItems;
  }

  public void setTotalItems(int totalItems) {
    this.totalItems = totalItems;
  }

  public int getStartItem() {
    return startItem;
  }

  public void setStartItem(int startItem) {
    this.startItem = startItem;    
  }

  public List<E> getItems() {
    return items;
  }

  public void setItems(List<E> items) {
    this.items = items;
  }
  
  
  
  public static void main(String[] args) {
    Pagination<String> page = new Pagination.PaginationMaker<String>()
                                            .totalItems(200)
                                            .startItem(11)
                                            .make();
    
    System.out.println("current page: " + page.getCurrentPage());
    System.out.println("start   page: " + page.getStartPage());
    System.out.println("end     page: " + page.getEndPage());
    System.out.println("total   page: " + page.getTotalPages());
  }
  
}
