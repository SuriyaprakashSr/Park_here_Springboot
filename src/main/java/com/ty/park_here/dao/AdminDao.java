package com.ty.park_here.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.park_here.dto.Admin;
import com.ty.park_here.repository.AdminRepository;

@Repository
public class AdminDao {

	@Autowired
	private AdminRepository adminRepository;
	
	public Admin saveAdmin(Admin admin) {
		return adminRepository.save(admin);
	}
	
	public Admin updateAdmin(Admin admin) {
		return adminRepository.save(admin);
	}
	
	public void deleteAdmin(Admin admin) {
		 adminRepository.delete(admin);
	}
	
	public Optional<Admin> getAdmin(int id){
		return adminRepository.findById(id);
	}
}
