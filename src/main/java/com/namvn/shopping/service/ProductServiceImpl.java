package com.namvn.shopping.service;

import com.namvn.shopping.pagination.PagingResult;
import com.namvn.shopping.persistence.dao.ProductDao;
import com.namvn.shopping.persistence.model.ProductInfo;
import com.namvn.shopping.persistence.model.ProductParam;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductDao productDao;
    @Override
    public PagingResult<ProductInfo> getQueryByDetail(int page, int limit, ProductParam productParam) {
        return productDao.getQueryByDetail(page,limit,productParam);
    }
}
