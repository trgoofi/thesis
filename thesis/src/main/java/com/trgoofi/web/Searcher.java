package com.trgoofi.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.trgoofi.domin.Hit;
import com.trgoofi.domin.HitModel;
import com.trgoofi.solr.SearchHandler;
import com.trgoofi.util.DefaultGet;
import com.trgoofi.util.Pagination;

public class Searcher extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String queryString = request.getParameter("q");
    
    if (StringUtils.isBlank(queryString)) {
      request.getRequestDispatcher("result.jsp").forward(request, response);
    }
    System.out.println("tesaferfa");
    String startString = request.getParameter("start");
    String rowsString = request.getParameter("rows");
    
    startString = DefaultGet.get(startString, "0");
    rowsString = DefaultGet.get(rowsString, "10");
    int start = Integer.valueOf(startString);
    int rows = Integer.valueOf(rowsString);
    
    SearchHandler searcher = new SearchHandler();
    HitModel hm = searcher.search(queryString, start, rows);
    Pagination<Hit> pageModel = new Pagination.PaginationMaker<Hit>()
                                          .items(hm.getHits())
                                          .startItem(start)
                                          .itemsPerPage(2)
                                          .pagesToCurrentPage(10)
                                          .totalItems((int) hm.getTotalHits())
                                          .make();
    
    request.setAttribute("search", "search");
    request.setAttribute("q", queryString);
    request.setAttribute("pageModel", pageModel);
    request.getRequestDispatcher("result.jsp").forward(request, response);
  }

}
