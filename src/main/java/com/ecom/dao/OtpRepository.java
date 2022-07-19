package com.ecom.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecom.entity.Otp;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long>{
	@Query(value = "select otp from otp "
			+ "where ADDTIME(created_at, '500')>=now() and "
			+ " username=:username "
			+ "order by created_at desc "
			+ "limit 1;",
			nativeQuery = true)
	public String checkOtp(@RequestParam("username") String username);

}
