package com.mysite.minipage.user;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HompiUserCreateForm {

	@Size(min=3, max=25)
	@NotEmpty(message="����� ���̵�� �ʼ� �׸��Դϴ�.")
	private String username;
	
	@NotEmpty(message="��й�ȣ�� �ʼ� �׸��Դϴ�.")
	private String password1;	//��й�ȣ
	
	@NotEmpty(message="��й�ȣ Ȯ���� �ʼ� �׸��Դϴ�.")
	private String password2;	//��й�ȣȮ��

	@NotEmpty(message="�̸����� �ʼ� �׸��Դϴ�.")
	@Email
	private String email;
	
	@Column(columnDefinition = "TEXT")		//�Խ��� Ȩ�������� ��ϵ� �ڱ�Ұ�
	private String profile;
}
