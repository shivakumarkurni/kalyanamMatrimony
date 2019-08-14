package com.hcl.matrimony.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.matrimony.entity.InterestShown;


@Repository
public interface InterestShownRepository extends JpaRepository<InterestShown, Long> {

	public List<InterestShown> findByFromMobileAndTargetMobile(Long fromMobile, Long targetMobile);
	

}
