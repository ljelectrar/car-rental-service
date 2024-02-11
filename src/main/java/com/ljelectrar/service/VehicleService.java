package com.ljelectrar.service;

import com.ljelectrar.model.Vehicle;

public interface VehicleService {
	
	public Vehicle create(Vehicle vehicle);
	public void validateVehicle(String vehicleId);
	public void associate(String vehicleId, String userId);
	public void delete(String vehicleId, String userId);
}
