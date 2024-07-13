package com.manytomany.demom_m.Repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manytomany.demom_m.Entity.Address;

public interface AddressRepo extends JpaRepository<Address, Long>{

	public Address save(Set<Address> addressDb);

}
