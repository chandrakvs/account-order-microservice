package com.ibm.microservices.ReceiveOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceiveOrderController {
	
	@Autowired
	private ReceiveOrderRepository receiveOrderRepository;
	
	@PostMapping("/receive-order")
	public String receiveOrder(@RequestBody ReceiveOrder receiveOrder) {
		receiveOrderRepository.save(receiveOrder);
		return "Order placed successfully";
	}
	

}
