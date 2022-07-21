package com.mysite.minipage.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class HompiUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	@Column(unique = true)
	private String username;			
	
	private String password;			
	
	@Column(unique = true)				
	private String email;
	
	@Column(columnDefinition = "TEXT")		//�Խ��� Ȩ�������� ��ϵ� �ڱ�Ұ�
	private String profile;
	
}
