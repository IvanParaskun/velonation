package org.velonation.core.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.velonation.core.datasource.entity.info.ShopInfo;
import org.velonation.core.datasource.repository.ShopInfoRepository;

@Service
@RequiredArgsConstructor
public class ShopInfoService {
    private final ShopInfoRepository repository;

    public ShopInfo shopInfo() {
        return repository.findAll().stream().findFirst().orElse(null);
    }

    public ShopInfo update(ShopInfo shopInfo) {
        return repository.save(shopInfo);
    }
}
