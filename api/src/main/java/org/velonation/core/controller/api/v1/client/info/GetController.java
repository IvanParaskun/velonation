package org.velonation.core.controller.api.v1.client.info;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.velonation.core.datasource.entity.info.ShopInfo;
import org.velonation.core.service.implementation.ShopInfoService;

@RestController("clientShopInfoController")
@RequestMapping("/api/v1/info")
@RequiredArgsConstructor
public class GetController {
    private final ShopInfoService service;

    @GetMapping
    public ShopInfo shopInfo() {
        return service.shopInfo();
    }
}
