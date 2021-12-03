package com.healez.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healez.product.entity.UserDetailEntity;

@Repository
public interface UserDetailRepo  extends JpaRepository<UserDetailEntity,String> {

}
