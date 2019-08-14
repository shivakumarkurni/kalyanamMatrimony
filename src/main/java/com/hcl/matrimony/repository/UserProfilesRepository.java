package com.hcl.matrimony.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.matrimony.entity.UserProfiles;

public interface UserProfilesRepository extends JpaRepository<UserProfiles, Long> {
	
	UserProfiles findByMobile(Long mobileNo);

}
