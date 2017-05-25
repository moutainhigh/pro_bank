package com.rongdu.p2psys.borrow.executer;

import com.rongdu.p2psys.core.constant.NoticeConstant;
import com.rongdu.p2psys.core.executer.BaseExecuter;
import com.rongdu.p2psys.core.service.NoticeService;
import com.rongdu.p2psys.core.sms.sendMsg.BaseMsg;
import com.rongdu.p2psys.core.util.BeanUtil;

/**
 * 借款标复审成功消息通知
 * @author sj
 * @since 2014年8月15日15:59:32
 *
 */
public class BorrowFullVerifySuccExecuter extends BaseExecuter {
	
	NoticeService noticeService;

	@Override
	public void prepare() {
		noticeService = (NoticeService) BeanUtil.getBean("noticeService");
	}

	@Override
	public void addAccountLog() {
		
	}

	@Override
	public void handleAccount() {
		
	}

	@Override
	public void handleAccountSum() {

	}

	@Override
	public void handlePoints() {

	}

	@Override
	public void handleNotice() {
        BaseMsg msg = new BaseMsg(NoticeConstant.BORROW_FULL_SUCC);
        msg.doEvent();
	}

	@Override
	public void addOperateLog() {

	}

	@Override
	public void handleInterface() {
		
	}

	@Override
	public void extend() {

	}
	
}
