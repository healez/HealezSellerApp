package com.healez.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.healez.product.entity.AddressEntity;

public interface AddressRepo  extends JpaRepository<AddressEntity,String> {

}
