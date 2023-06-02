package org.velonation.core.controller.api.v1.admin.info;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.velonation.core.datasource.entity.info.ShopInfo;
import org.velonation.core.service.implementation.ShopInfoService;


@RestController
@RequestMapping("/api/v1/admin/info")
@RequiredArgsConstructor
public class ShopInfoController {
    private final ShopInfoService service;

    @PostMapping
    public ShopInfo shopInfo(@RequestBody ShopInfo shopInfo) {
        return service.update(shopInfo);
    }
}
