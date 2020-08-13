package com.ajax.test.dao;

import java.util.List;
import java.util.Map;

public interface UserInfoDAO {

	int insertUserInfo(Map<String,Object> uMap);
	int updateUserInfo(Map<String,Object> uMap);
	int deleteUserInfo(Map<String,Object> uMap);
	Map<String,Object> selectUserInfo(Map<String,Object> uMap);
	Map<String,Object> selectUserInfoByUiId(String uiId);
	List<Map<String,Object>> selectUserInfoList(Map<String,Object> uMap);
}
