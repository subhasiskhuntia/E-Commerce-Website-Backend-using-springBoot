package com.ecom.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dao.OrderItemDao;

@Service
public class OrderItemServiceImpl {
	@Autowired
	OrderItemDao orderItemRepo;
	public List<Map<String, Object>> getSalesDate(LocalDate from,LocalDate till){
		return this.orderItemRepo.getSalesData(from,till);
	}
}
