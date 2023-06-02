package org.velonation.core.datasource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.velonation.core.datasource.entity.info.ShopInfo;

@Repository
public interface ShopInfoRepository extends JpaRepository<ShopInfo, Integer> {
}
