package org.velonation.core.controller.api.v1.client.products;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.velonation.core.datasource.entity.product.Bike;
import org.velonation.core.service.BikeService;
import org.velonation.core.service.ScooterService;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/api/v1/products/info")
@RequiredArgsConstructor
public class InfoController {
	private final BikeService bikeService;
	private final ScooterService scooterService;

	@GetMapping("/bikes")
	public BikeInfo bikeInfo() {
		return new BikeInfo(
				bikeService.brands(),
				bikeService.modelYears(),
				bikeService.findBannered(),
				bikeService.findCarded()
		);
	}

	@GetMapping("/scooters")
	public ScooterInfo scooterInfo() {
		return new ScooterInfo(
				scooterService.modelYears(),
				scooterService.heights(),
				scooterService.maxWeights()
		);
	}

	public record BikeInfo(
			Set<String> brands,
			Set<Integer> modelYears,
			Bike bannered,
			List<Bike> carded) { }

	public record ScooterInfo(
			Set<Integer> modelYears,
			Set<Integer> heights,
			Set<Integer> maxWeights) { }
}
