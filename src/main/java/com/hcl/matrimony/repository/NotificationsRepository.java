package com.hcl.matrimony.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.matrimony.entity.InterestShown;

@Repository
public interface NotificationsRepository extends JpaRepository<InterestShown, Long> {
	
	List<InterestShown> findByTargetMobile(Long mobileNo);

}
