package com.springcloud.service;

import com.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {

    int create(Payment payment);

    Payment getById(@Param("id") Long id);
}
