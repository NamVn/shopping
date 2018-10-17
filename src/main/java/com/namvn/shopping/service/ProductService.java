package com.namvn.shopping.service;

import com.namvn.shopping.pagination.PagingResult;
import com.namvn.shopping.persistence.model.ProductInfo;
import com.namvn.shopping.persistence.model.ProductParam;

public interface ProductService {
    PagingResult<ProductInfo> getQueryByDetail(int page, int limit, ProductParam productParam);
}
