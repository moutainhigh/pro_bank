package com.rongdu.p2psys.user.executer;

import org.apache.log4j.Logger;

import com.rongdu.p2psys.core.constant.NoticeConstant;
import com.rongdu.p2psys.core.executer.BaseExecuter;
import com.rongdu.p2psys.core.service.NoticeService;
import com.rongdu.p2psys.core.sms.sendMsg.BaseMsg;
import com.rongdu.p2psys.core.util.BeanUtil;

public class NationalDayExecuter extends BaseExecuter {

	Logger logger = Logger.getLogger(NationalDayExecuter.class);
	
	protected NoticeService noticeService;
	
	@Override
	public String getAccountLogType() {
		return null;
	}

	@Override
	public void prepare() {
		noticeService = (NoticeService) BeanUtil.getBean("noticeService");
	}

	@Override
	public void handleAccount() {
		
	}

	@Override
	public void addAccountLog() {
		
	}

	@Override
	public void handleAccountSum() {
		
	}

	@Override
	public void handlePoints() {
		
	}

	@Override
	public void handleNotice() {
		BaseMsg msg = new BaseMsg(NoticeConstant.NATIONAL_DAY);
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
