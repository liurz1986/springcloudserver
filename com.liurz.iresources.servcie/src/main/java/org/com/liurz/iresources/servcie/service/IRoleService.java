package org.com.liurz.iresources.servcie.service;
import java.util.List;
import java.util.Map;
public interface IRoleService {
 
	/**
	 * 保存
	 * @param role
	 */
	public void saveRole(Map<String,Object> role);
	/**
	 * 批量保存
	 * @param files
	 */
	public void batchSaveRole(List<Map<String,Object>> roles);
	/**
	 * 查询所有数据
	 * @return
	 */
	public List<Map<String,Object>> findAll();
	
	/**
	 * 通过id获取角色对象
	 * @param id
	 * @return
	 */
	public Map<String,Object> findaById(int id);
	
}