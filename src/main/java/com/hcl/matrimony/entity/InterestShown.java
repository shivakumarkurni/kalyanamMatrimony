package com.hcl.matrimony.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "INTRESTSHOWN")
@Setter
@Getter
public class InterestShown {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long interestId;
<<<<<<< HEAD

=======
>>>>>>> 27613fc2ca60b3c8f5b4471240e4e41c2f127a05
	private Long fromMobile;
	private Long targetMobile;
	private LocalDateTime date;
	private String status;

}
