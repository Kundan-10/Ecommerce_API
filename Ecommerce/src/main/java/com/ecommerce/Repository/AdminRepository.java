package com.ecommerce.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.Admin;
import com.ecommerce.model.User;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{
	public List<Admin> findByAdminEmail(String adminEmail);
	public List<Admin> findByAdminName(String adminName);

}
