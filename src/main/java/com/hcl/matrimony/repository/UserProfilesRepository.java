package com.hcl.matrimony.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.matrimony.entity.UserProfiles;


@Repository
public interface UserProfilesRepository extends JpaRepository<UserProfiles, Long> {
	
<<<<<<< HEAD
	UserProfiles findByMobile(Long mobileNo);
=======
  public	List<UserProfiles> findByMobile(Long mobile);
>>>>>>> 27613fc2ca60b3c8f5b4471240e4e41c2f127a05

}
