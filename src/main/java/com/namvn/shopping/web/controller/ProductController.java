package com.namvn.shopping.web.controller;

import com.namvn.shopping.pagination.PagingResult;
import com.namvn.shopping.persistence.entity.Product;
import com.namvn.shopping.persistence.model.ProductInfo;
import com.namvn.shopping.persistence.model.ProductParam;
import com.namvn.shopping.service.ProductService;
import com.namvn.shopping.util.IO;
import com.namvn.shopping.web.url.UrlAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(10240000);
        return multipartResolver;
    }

    @RequestMapping(UrlAddress.PRODUCT_GET)
    public ModelAndView getAllProducts(@RequestParam int page,
                                       @RequestParam(value = "sortType", required = false) String sortType,
                                       @RequestParam(value = "color", required = false) String color,
                                       @RequestParam(value = "size", required = false) String size,
                                       @RequestParam(value = "manufacturer", required = false) String manufacturer,
                                       @RequestParam(value = "material", required = false) String material,
                                       @RequestParam(value = "madeIn", required = false) String madeIn,
                                       @RequestParam(value = "minPrice", required = false) float minPrice,
                                       @RequestParam(value = "maxPrice", required = false) float maxPrice) {
        IO io = new IO();
        ProductParam productParam = new ProductParam(sortType,
                minPrice,
                maxPrice,
                io.cutWhiteSpaces(color),
                io.cutWhiteSpaces(size),
                io.cutWhiteSpaces(manufacturer),
                io.cutWhiteSpaces(material),
                io.cutWhiteSpaces(madeIn));
        PagingResult<ProductInfo> products = productService.getQueryByDetail(page, 30, productParam);
        return new
                ModelAndView("productList", "products", products.getList());
    }

    @RequestMapping(UrlAddress.PRODUCT_GET_ID)
    public ModelAndView getProductById(@PathVariable(value = "productId") String productId) {
        ProductInfo product = productService.getProductById(productId);
        return new ModelAndView("productPage", "productObj", product);
    }

    @RequestMapping(value = UrlAddress.PRODUCT_ADD, method = RequestMethod.POST)
    public String addProduct(@Valid @ModelAttribute(value = "productFormObj") Product product, BindingResult result) {
        // Binding Result is used if the form that has any error then it will
        // redirect to the same page without performing any functions
        if (result.hasErrors())
            return "addProduct";
        productService.addProduct(product);
        return "redirect:/getAllProducts";
    }

    @RequestMapping(value = UrlAddress.PRODUCT_EDIT, method = RequestMethod.POST)
    public String editProduct(@ModelAttribute(value = "editProductObj") Product product) {
        productService.editProduct(product);
        return "redirect:/getAllProducts";
    }

    @RequestMapping(UrlAddress.PRODUCT_DELETE)
    public String deleteProduct(@PathVariable(value = "productId") String productId) {
        productService.deleteProduct(productId);
        // http://localhost:8080/shoppingCart/getAllProducts
        return "redirect:/getAllProducts";
    }

}
