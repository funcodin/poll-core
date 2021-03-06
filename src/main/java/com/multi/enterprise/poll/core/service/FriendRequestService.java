/**
 * 
 */
package com.multi.enterprise.poll.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.enterprise.commons.service.BaseRecordService;
import com.multi.enterprise.poll.core.dao.FriendDao;
import com.multi.enterprise.poll.core.dao.FriendRequestDao;
import com.multi.enterprise.types.exception.ServiceException;
import com.multi.enterprise.types.friends.FriendList;
import com.multi.enterprise.types.friends.FriendRequest;
import com.multi.enterprise.types.friends.FriendRequestList;

/**
 * @author Robot
 *
 */
@Service
public class FriendRequestService extends BaseRecordService<FriendRequest> {

	@Autowired
	FriendRequestDao friendRequestDao;

	@Autowired
	FriendDao friendDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.types.service.RecordService#entityClass()
	 */
	@Override
	public Class<FriendRequest> entityClass() {
		return FriendRequest.class;
	}

	public FriendRequestList getPendingFriendRequestByUserId(final String userId) {
		final List<FriendRequest> friendRequests = this.friendRequestDao.getPendingFriendRequestByUserId(userId);
		final FriendRequestList friendRequestList = new FriendRequestList();
		friendRequestList.setPendingFriendRequest(friendRequests);
		return friendRequestList;
	}

	public FriendList acceptFriendRequest(final FriendRequest friendRequest) throws ServiceException {
		friendRequestDao.acceptFriendRequest(friendRequest.getAccepterUserId(), friendRequest.getRequesterUserId());
		return this.friendDao.getFriendsByUserId(friendRequest.getAccepterUserId());
	}

}
