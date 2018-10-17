package com.namvn.shopping.util;

import com.namvn.shopping.persistence.model.ProductParam;


import java.util.HashMap;
import java.util.List;

public class PreprocessingInput {
    public HashMap<String, List<String>> filterPredicateProduct(ProductParam productParam) {
        HashMap<String,List<String>> map = new HashMap<String, List<String>>();
        List<String> colors = productParam.getColors();
        List<String> sizes = productParam.getSizes();
        List<String> manufacturers = productParam.getManufacturers();
        List<String> materials = productParam.getMaterials();
        List<String> madeIns = productParam.getMaterials();
        if (colors.size() > 0) map.put(ProductContants.COLOR, colors);
        if (sizes.size() > 0) map.put(ProductContants.SIZE, sizes);
        if (manufacturers.size() > 0) map.put(ProductContants.MANUFACTURER, manufacturers);
        if (materials.size() > 0) map.put(ProductContants.MATERIAL, materials);
        if (madeIns.size() > 0) map.put(ProductContants.MADEIN, madeIns);
        return map;
    }
}
