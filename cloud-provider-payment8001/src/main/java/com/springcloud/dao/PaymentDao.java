package com.springcloud.dao;

import com.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author: Jiang
 * @create: 2021-07-10 19:43
 */
@Mapper
public interface PaymentDao {

    int create(Payment payment);

    Payment getById(Long id);
}
