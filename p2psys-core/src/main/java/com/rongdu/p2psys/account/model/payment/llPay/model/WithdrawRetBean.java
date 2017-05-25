package com.rongdu.p2psys.account.model.payment.llPay.model;

import java.io.Serializable;

/***
 * 银行卡代付返回参数列表
 * @author ChenGangwei
 * 2015-05-22
 */
public class WithdrawRetBean implements Serializable{
    private static final long serialVersionUID = 1L;
    private String 	 ret_code;   	//交易结果代码
    private String 	 ret_msg;   	//交易结果描述
    private String 	 sign_type;   	//签名方式
    private String 	 sign;   		//签名
    
    /**
     * 商户接到通知后返回参数列表
     * @param ret_code
     * @param ret_msg
     */
    public WithdrawRetBean(String ret_code,String ret_msg){
    	this.ret_code = ret_code;
    	this.ret_msg = ret_msg;
    }
    
    /**
     * 银行卡代付返回参数列表
     * @param ret_code
     * @param ret_msg
     */
    public WithdrawRetBean(String ret_code,String ret_msg,String sign_type,String sign){
    	this.ret_code = ret_code;
    	this.ret_msg = ret_msg;
    	this.sign_type = sign_type;
    	this.sign = sign;
    }
    
	public String getRet_code() {
		return ret_code;
	}
	public void setRet_code(String ret_code) {
		this.ret_code = ret_code;
	}
	public String getRet_msg() {
		return ret_msg;
	}
	public void setRet_msg(String ret_msg) {
		this.ret_msg = ret_msg;
	}
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}

}
