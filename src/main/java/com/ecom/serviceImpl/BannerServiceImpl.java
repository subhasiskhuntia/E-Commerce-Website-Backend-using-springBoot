package com.ecom.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dao.BannerRepo;
import com.ecom.entity.Banner;

@Service
public class BannerServiceImpl {
	@Autowired
	BannerRepo bannerRepo;
	public String saveBanner(Banner banner) {
		if(bannerRepo.save(banner)!=null) {
			return "Banner Saved Successfully";
		};
		return "Unable to save the banner";
	}
	public List<Banner> showBanner(){
		return bannerRepo.findAll();
	}
	public String deleteBanner(int id) {
		Optional<Banner> banner=bannerRepo.findById(id);
		if(banner.isPresent()) {
			bannerRepo.deleteById(id);
			return "deleted successfully";
		}
		return "id doesn't exist";
	}

}
