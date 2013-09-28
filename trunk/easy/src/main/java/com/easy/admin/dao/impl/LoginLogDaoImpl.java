/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.easy.admin.dao.LoginLogDao;
import com.easy.admin.entity.LoginLog;
import org.apache.ibatis.session.RowBounds;
import com.easy.core.common.Page;
import com.easy.core.dao.impl.MyBatisGenericDao;
/**
 * 登陆日志DaoImpl
 * 
 * @author wy
 * @version v 0.1 13-09-28 上午 00:49:47 wy Exp $
 */
@Repository
public class LoginLogDaoImpl extends MyBatisGenericDao implements LoginLogDao {

	/**
	 * @see com.easy.admin.dao.LoginLogDao#create(com.easy.admin.entity.LoginLog)
	 */
	@Override
	public LoginLog create(LoginLog loginLog) {
		save("com.easy.admin.dao.LoginLogDao.create", loginLog);
		return loginLog;
	}

	/**
	 * @see com.easy.admin.dao.LoginLogDao#update(com.easy.admin.entity.LoginLog)
	 */
	@Override
	public int update(LoginLog loginLog) {
		return update("com.easy.admin.dao.LoginLogDao.update", loginLog);
	}
	/**
	 * @see com.easy.admin.dao.LoginLogDao#deleteByPrimaryKeys(java.lang.Long[])
	 */
	@Override
	public int deleteByPrimaryKeys(Long...ids ) {
		return delete("com.easy.admin.dao.LoginLogDao.deleteByPrimaryKeys", ids );
	}

	/**
	 * @see com.easy.admin.dao.LoginLogDao#getByPrimaryKey(java.lang.Long)
	 */
	@Override
	public LoginLog getByPrimaryKey(Long id ) {
		return get("com.easy.admin.dao.LoginLogDao.getByPrimaryKey", id );
	}

	/**
	 * @see com.easy.admin.dao.LoginLogDao#selectByCriteria(com.easy.admin.entity.LoginLog)
	 */
	@Override
	public List<LoginLog> selectByCriteria(LoginLog loginLog) {
		return list("com.easy.admin.dao.LoginLogDao.selectByCriteria", loginLog);
	}

	/**
	 * @see com.easy.admin.dao.LoginLogDao#countByCriteria(com.easy.admin.entity.LoginLog)
	 */
	@Override
	public int countByCriteria(LoginLog loginLog) {
		return get("com.easy.admin.dao.LoginLogDao.countByCriteria", loginLog);
	}

	/**
	 * @see com.easy.admin.dao.LoginLogDao#page(com.easy.core.common.Page)
	 */
	@Override
	public void page(Page<LoginLog> page) {
		RowBounds rowBounds = new RowBounds(page.getOffset(),
				page.getPageSize());
		List<LoginLog> list = list("com.easy.admin.dao.LoginLogDao.selectByCriteria",
				page.getCriteria(), rowBounds);
		page.setList(list);
		page.setTotalCount(countByCriteria(page.getCriteria()));
	}
}
