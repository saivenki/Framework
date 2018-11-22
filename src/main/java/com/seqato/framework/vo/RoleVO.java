package com.seqato.framework.vo;


import com.seqato.framework.utility.VONames;

public class RoleVO extends BaseVO{
	
	private Long id;
	
	private String roleDescription;

	private String status;
	/*@JsonIgnore
	private List<UserVO> usersVO;
	@JsonIgnore
	private List<RoleAccessListVO> roleAccessListsVO;*/
	
	public RoleVO(){
		voName=VONames.ROLE_VO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/*public List<UserVO> getUsersVO() {
		return usersVO;
	}

	public void setUsersVO(List<UserVO> usersVO) {
		this.usersVO = usersVO;
	}

	public List<RoleAccessListVO> getRoleAccessListsVO() {
		return roleAccessListsVO;
	}

	public void setRoleAccessListsVO(List<RoleAccessListVO> roleAccessListsVO) {
		this.roleAccessListsVO = roleAccessListsVO;
	}*/

}
