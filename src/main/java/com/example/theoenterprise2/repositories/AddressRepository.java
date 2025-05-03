package com.example.theoenterprise2.repositories;

import com.example.theoenterprise2.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
