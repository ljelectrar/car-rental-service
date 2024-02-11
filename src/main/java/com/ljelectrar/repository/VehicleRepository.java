package com.ljelectrar.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ljelectrar.model.Vehicle;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

}
