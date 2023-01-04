package com.ty.park_here.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.park_here.dto.Manager;
import com.ty.park_here.repository.ManagerRepository;

@Repository
public class ManagerDao {

	@Autowired
	private ManagerRepository managerRepository;

	public Manager saveManager(Manager manager) {
		return managerRepository.save(manager);
	}

	public Manager updateManager(Manager manager) {
		return managerRepository.save(manager);
	}

	public Optional<Manager> getMangerById(int id) {
		return managerRepository.findById(id);
	}

	public void deleteManager(Manager manager) {
		managerRepository.delete(manager);
	}
}
