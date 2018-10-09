package com.namvn.shopping.pagination;


import org.hibernate.query.Query;
import org.hibernate.ScrollableResults;


import java.util.List;

public class PagingResult<E> {
    private List<E> list;

    public PagingResult(Query<E> query, int page, int limit) {
        int totalRecords = 0;
        int pageIndex = page - 1 < 0 ? 0 : page - 1;
        int firstIndexPage = pageIndex * limit;
        // int maxIndexPage = firstIndexPage + limit;

        ScrollableResults scrollableResults = query.scroll();
        scrollableResults.last();
        totalRecords = scrollableResults.getRowNumber() + 1;
        scrollableResults.close();

        if (firstIndexPage < totalRecords) {
            int totalPage = totalRecords / limit;
            int excessingRecord = totalRecords % limit;
            if (excessingRecord != 0) {
                // totalPage = totalPage + 1;
                query.setFirstResult(firstIndexPage);
                query.setMaxResults(excessingRecord);
                list = query.getResultList();
            } else {
                query.setFirstResult(firstIndexPage);
                query.setMaxResults(limit);
                list = query.getResultList();
            }
        }
    }



    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }
}
