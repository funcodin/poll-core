/**
 *
 */
package com.multi.enterprise.poll.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.enterprise.commons.service.BaseRecordService;
import com.multi.enterprise.poll.core.dao.FriendDao;
import com.multi.enterprise.types.friends.Friend;
import com.multi.enterprise.types.friends.FriendList;

/**
 * @author Robot
 *
 */
@Service
public class FriendsService extends BaseRecordService<Friend> {

	@Autowired
	FriendDao friendDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.types.service.RecordService#entityClass()
	 */
	@Override
	public Class<Friend> entityClass() {
		return Friend.class;
	}

	public FriendList getUserFriends(final String userId) {
		return this.friendDao.getFriendsByUserId(userId);

	}

}
