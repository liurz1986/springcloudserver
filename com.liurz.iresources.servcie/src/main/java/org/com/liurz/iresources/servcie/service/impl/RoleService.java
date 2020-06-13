package org.com.liurz.iresources.servcie.service.impl;

import java.util.List;
import java.util.Map;

import org.com.liurz.iresources.servcie.mapper.RoleMapper;
import org.com.liurz.iresources.servcie.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleService implements IRoleService {
	@Autowired
	private RoleMapper roleMapper;

	public void saveRole(Map<String, Object> role) {

		roleMapper.saveRole(role);

	}

	public void batchSaveRole(List<Map<String, Object>> roles) {

		roleMapper.batchSaveRole(roles);
	}

	public List<Map<String, Object>> findAll() {
		// TODO Auto-generated method stub
		return roleMapper.findAll();
	}

	public Map<String, Object> findaById(int id) {
		// TODO Auto-generated method stub
		return roleMapper.findaById(id);
	}

}
