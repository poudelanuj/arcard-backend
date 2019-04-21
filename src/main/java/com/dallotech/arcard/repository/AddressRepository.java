package com.dallotech.arcard.repository;

import com.dallotech.arcard.model.db.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findByUser_Id(Long id);

}
