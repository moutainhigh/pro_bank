package com.rongdu.p2psys.core.quartz.task;

import org.apache.log4j.Logger;

import com.rongdu.common.exception.BussinessException;
import com.rongdu.p2psys.borrow.service.AutoBorrowService;
import com.rongdu.p2psys.borrow.service.BorrowTenderService;
import com.rongdu.p2psys.core.Global;
import com.rongdu.p2psys.core.concurrent.ValueEvent;
import com.rongdu.p2psys.core.quartz.AbstractLoanTask;
import com.rongdu.p2psys.core.quartz.JobQueue;
import com.rongdu.p2psys.core.util.BeanUtil;
import com.rongdu.p2psys.nb.borrow.service.BorrowService;
import com.rongdu.p2psys.tpp.TPPWay;
import com.rongdu.p2psys.tpp.chinapnr.service.ChinapnrService;
import com.rongdu.p2psys.tpp.ips.service.IpsService;

/**
 * 
 * @Title: TenderTask.java
 * @Package com.rongdu.p2psys.core.quartz.task
 * @Description: 投标业务处理类
 * @author Moon.Liu
 * @date 2014年12月2日 上午10:56:03
 * @version V2.0
 */
public class TenderTask extends AbstractLoanTask {
	private Logger logger = Logger.getLogger(TenderTask.class);

	public TenderTask() {
		super();
		task.setName("Tender.Task");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doLoan() {

		JobQueue<ValueEvent> queue = JobQueue.getTenderInstance();
        AutoBorrowService autoBorrowService = (AutoBorrowService) BeanUtil.getBean("autoBorrowService");
		BorrowTenderService borrowTenderService = (BorrowTenderService) BeanUtil.getBean("borrowTenderService");
		BorrowService borrowService = (BorrowService) BeanUtil.getBean("theBorrowService");
		ChinapnrService chinapnrService=(ChinapnrService)BeanUtil.getBean("chinapnrService");
		while (!queue.isEmpty()) {
			ValueEvent event = queue.peek();
			if (event != null) {
				String result = "success";
				try {
					if ("tender".equals(event.getOperate())) {// 正常投标
						if(borrowService.isSkipReview(event.getBorrow())){
							borrowTenderService.addVipTender(event.getBorrow(),event.getBorrowModel());
						}else{
							borrowTenderService.addTender(event.getBorrow(),event.getBorrowModel());
						}
					} else if ("autoTender".equals(event.getOperate())) {
		                autoBorrowService.autoDealTender(event.getBorrowModel());
					} else if ("doAddTender".equals(event.getOperate())) {//投标回调处理
		                // 易极付
		                if(TPPWay.API_CODE == TPPWay.API_CODE_YJF){
		                // 环讯
		                }else if(TPPWay.API_CODE == TPPWay.API_CODE_IPS){
		                    IpsService ipsService=(IpsService)BeanUtil.getBean("ipsService");
		                    ipsService.doAddTender(event.getBorrowModel());
		                // 汇付
		                }else if(TPPWay.API_CODE == TPPWay.API_CODE_PNR){
		                    chinapnrService.addTender(event.getBorrowModel());
		                }
		            }
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					if (e instanceof BussinessException) {// 业务异常，保存业务处理信息
						result = e.getMessage();
					} else {
						result = "系统异常，业务处理失败";
					}
				} finally {
				    queue.remove(event);
				}
				if (event.getResultFlag() != null) {// 在需要保存系统处理信息的地方直接保存进来
					Global.RESULT_MAP.put(event.getResultFlag(), result);
				}
			}
		}

	}

	@Override
	public Object getLock() {
		return TenderTask.TENDER_STATUS;
	}

}
