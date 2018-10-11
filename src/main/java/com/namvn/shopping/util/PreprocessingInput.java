package com.namvn.shopping.util;

import com.namvn.shopping.persistence.model.ProductInfo;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PreprocessingInput {
    public HashMap<String, List<String>> filterPredicate(ProductInfo productInfo) {
        HashMap<String,List<String>> map = new HashMap<String, List<String>>();
        List<String> colors = productInfo.getColors();
        List<String> sizes = productInfo.getSizes();
        List<String> manufacturers = productInfo.getManufacturers();
        List<String> materials = productInfo.getMaterials();
        List<String> madeIns = productInfo.getMaterials();
        if (colors.size() > 0) map.put(ProductContants.COLOR, colors);
        if (sizes.size() > 0) map.put(ProductContants.SIZE, sizes);
        if (manufacturers.size() > 0) map.put(ProductContants.MANUFACTURER, manufacturers);
        if (materials.size() > 0) map.put(ProductContants.MATERIAL, materials);
        if (madeIns.size() > 0) map.put(ProductContants.MADEIN, madeIns);
        return map;
    }
}
