package com.namvn.shopping.web.controller;

import com.namvn.shopping.pagination.PagingResult;
import com.namvn.shopping.persistence.model.ProductInfo;
import com.namvn.shopping.persistence.model.ProductParam;
import com.namvn.shopping.service.ProductService;
import com.namvn.shopping.web.url.UrlAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(UrlAddress.PRODUCT_GET)
    public ModelAndView getAllProducts(@RequestParam int page,
                                       @RequestParam(value = "sortType", required = false) String sortType,
                                       @RequestParam(value = "color", required = false) String color,
                                       @RequestParam(value = "size", required = false) String size,
                                       @RequestParam(value = "manufacturer", required = false) String manufacturer,
                                       @RequestParam(value = "material", required = false) String material,
                                       @RequestParam(value = "madeIn", required = false) String madeIn,
                                       @RequestParam(value = "minPrice", required = false) String minPrice,
                                       @RequestParam(value = "maxPrice", required = false) String maxPrice) {
        PagingResult<ProductInfo> products = productService.getQueryByDetail(page, 30, new ProductParam());
        return new
                ModelAndView("productList", "products", products);
    }
}
