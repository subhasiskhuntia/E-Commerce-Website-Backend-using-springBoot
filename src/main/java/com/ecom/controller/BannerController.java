package com.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.entity.Banner;
import com.ecom.serviceImpl.BannerServiceImpl;

@RestController
@RequestMapping(value = "api/banner")
@CrossOrigin
public class BannerController {
	@Autowired
	BannerServiceImpl bannerService;
	
	@PostMapping(value = "setBanner")
	public String setBanner(@RequestBody Banner banner) {
		System.out.println(banner);
		return bannerService.saveBanner(banner);
	}
	@GetMapping(value = "showBanner")
	public List<Banner> showBanner(){
		return bannerService.showBanner();
	}
	@DeleteMapping(value = "deleteBanner/{id}")
	public String deleteBanner(@PathVariable int id) {
		System.out.println(id);
		String message= bannerService.deleteBanner(id);
		return message;
	}
}
