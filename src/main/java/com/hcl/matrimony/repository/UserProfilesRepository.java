package com.hcl.matrimony.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.matrimony.entity.UserProfiles;


@Repository
public interface UserProfilesRepository extends JpaRepository<UserProfiles, Long> {
	

  public	List<UserProfiles> findByMobile(Long mobile);

}
