package com.gxyan.gmall.product.feign;

import com.gxyan.gmall.common.to.SkuEsModel;
import com.gxyan.gmall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author gxyan
 * @date 2020/11/23 21:58
 */
@FeignClient("gmall-search")
public interface SearchFeignService {
    @PostMapping("/search/save/product")
    R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels);
}
