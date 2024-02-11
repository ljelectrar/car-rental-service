package com.ljelectrar.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljelectrar.model.Status;
import com.ljelectrar.model.Vehicle;
import com.ljelectrar.repository.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleRepository repository;

	@Override
	public Vehicle create(Vehicle vehicle) {
		vehicle.setStatus(Status.AVAILABLE);
		vehicle.setOwner(null);
		vehicle.setAssociationDate(null);
		return repository.save(vehicle);
	}

	@Override
	public void validateVehicle(String vehicleId) {
		repository.findById(Long.valueOf(vehicleId)).orElseThrow();
	}

	@Override
	public void associate(String vehicleId, String userId) {
		var vehicle = repository.findById(Long.valueOf(vehicleId))
								.filter(v -> v.getStatus() == Status.AVAILABLE)
								.orElseThrow();
		vehicle.setOwner(userId);
		vehicle.setAssociationDate(new Date());
		vehicle.setStatus(Status.ASSOCIATED);
		
		repository.save(vehicle);
	}

	@Override
	public void delete(String vehicleId, String userId) {
		var vehicle = repository.findById(Long.valueOf(vehicleId))
				.filter(v -> v.getStatus() == Status.ASSOCIATED)
				.filter(v -> userId.equals(v.getOwner()))
				.orElseThrow();
		
		vehicle.setOwner(null);
		vehicle.setAssociationDate(null);
		vehicle.setStatus(Status.AVAILABLE);
		
		repository.save(vehicle);

	}

}
