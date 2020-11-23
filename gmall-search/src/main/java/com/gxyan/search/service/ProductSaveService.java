package com.gxyan.search.service;

import com.gxyan.gmall.common.to.SkuEsModel;

import java.io.IOException;
import java.util.List;

/**
 * @author gxyan
 * @date 2020/11/23 23:21
 */
public interface ProductSaveService {
    boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException;
}
