package com.ibm.microservices.ReceiveOrderService;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiveOrderRepository extends CrudRepository<ReceiveOrder, Long>{

}
