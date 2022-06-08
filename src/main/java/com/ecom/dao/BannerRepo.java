package com.ecom.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.entity.Banner;

@Repository
public interface BannerRepo extends JpaRepository<Banner, Integer>{

}
