package com.rongdu.p2psys.account.model.payment.llPay.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.login.AccountException;

import com.rongdu.common.util.DateUtil;
import com.rongdu.common.util.StringUtil;
import com.rongdu.p2psys.core.util.OrderNoUtils;

/**
 * 连连支付
 * 
 * @author cgw
 * @version 1.0
 * @Date 2015年5月24日
 */
public class LianlPay {
//	private static Logger logger = Logger.getLogger(UnionPay.class);
	
	private String version;
	private String charsetName;
	private String oidPartner;
	private String platform;
	private String userId;
	private String signType;
	private String busiPartner;
	private String dtOrder;
	private String nameGoods;
	private String infoOrder;
	private String moneyOrder;
	private String notifyUrl;
	private String notifyCUrl;
	private String urlReturn;
	private String userreqIp;
	private String urlOrder;
	private String validOrder;
	private String noAgree;
	private String shareingData;
	private String riskItem;
	private String idType;
	private String idNo;
	private String backUrl;
	private String addTime;

	/**
	 * 商户接入号
	 */
	 private String merId = "1000000296";//"1000000475";//Global.getString("tpp_base_account");
	 
	 /**
	  * 密钥
	  */
	 private String key = "GjpWGE7mmndzB5nMEird6drtKrhd82CG";//"3daytaw8CPAM8QYsAKA3jjryAeD28AhE";//Global.getString("tpp_key");

	/**
	 * 订单号
	 */
	private String orderNo = OrderNoUtils.getSerialNumber();

	/**
	 * 银行卡号、银行账户（绑卡接口/获取银行卡信息接口/对公付款接口）
	 */
	private String cardNo;

	/**
	 * 银行卡绑定号（解绑银行卡接口/查询绑定信息接口/扣款接口/付款接口/）
	 */
	private String bindId;

	/**
	 * 姓名、银行账户帐号（绑卡接口/对公付款接口）
	 */
	private String accName;
	
	/**
	 * 开户行名称（对公付款接口）
	 */
	private String bankName;
	
	/**
	 * 开户行代码（对公付款接口）
	 */
	private String bankCode;

	/**
	 * 身份证（绑卡接口）
	 */
	private String accId;

	/**
	 * 手机号码（绑卡接口/发送验证码接口）
	 */
	private String mobile;

	/**
	 * CVN安全码 信用卡背面签名栏末三位（绑卡接口）
	 */
	private String cvn;

	/**
	 * 有效期 信用卡有效期（yyMM）格式，例如10月19年即为1910（绑卡接口）
	 */
	private String expire;

	/**
	 * 交易金额，整数且以分为单位（扣款接口/付款接口/对公付款接口）
	 */
	private long amount;

	/**
	 * 付款类型 1为非实时付款，2为实时付款（付款接口/对公付款接口）
	 */
	private int payType;

	/**
	 * 时间戳
	 */
	private String timestamp = System.currentTimeMillis() + "";

	/**
	 * 交易时间 格式：yyyyMMddHHmmss,例如2015年3月7日9时1分5秒即为20150307090105
	 */
	private String tranDateTime = DateUtil.dateStr3(new Date());

	/**
	 * 验证码（绑卡接口）
	 */
	private String code;

	/**
	 * 文件类型（对账接口），对账文件类型，1为清算文件，2为当日或历史订单
	 */
	private int fileType;

	/**
	 * 交易类型（对账接口），查询交易类型，fileType为2时有效，1为扣款，2为付款
	 */
	private int tranType;

	/**
	 * 请求交易日期（对账接口），查询的交易日期，格式：yyyyMMdd，例如2015年3月7日即为20150307
	 */
	private String tranDate;

	/**
	 * 商户类型（备付金查询接口），1备付方式,2虚拟户方式,3专用通道方式
	 */
	private int merType;
	
	/**
	 * 开户行省（对公付款接口）
	 */
	private String province;
	
	/**
	 * 开户支行名称
	 */
	private String brabank;
	
	/**
	 * 开户行市（对公付款接口）
	 */
	private String city;

	/**
	 * 签名
	 */
	private String sign;

	/**
	 * 备注（扣款接口/付款接口）
	 */
	private String remarks;

	public LianlPay() {
		super();
	}
	
	/**
	 * 	提现参数
	 * @param cardNo 银行卡号
	 * @param accName 姓名
	 * @param oidPartner 商户号
	 * @param signType 加密类型
	 * @param bankCode 银行编号
	 * @param notifyCUrl 异步地址
	 * @param province 开户行省
	 * @param city  开户行市
	 * @param brabank 开户支行名称
	 */
	public LianlPay(String cardNo, String accName,String oidPartner,String signType,String bankCode,String notifyCUrl,String province,String city,String brabank){
		this.cardNo = cardNo;
		this.accName = accName;
		this.oidPartner = oidPartner;
		this.signType = signType;
		this.bankCode = bankCode;
		this.notifyCUrl = notifyCUrl;
		this.province = province;
		this.city = city;
		this.brabank = brabank;
	}

	/**
	 * 绑定银行卡（卡号+姓名+身份证+预留手机号码+验证码验证）
	 * 
	 * @param cardNo 银行卡号
	 * @param accName 姓名
	 * @param accId 身份证
	 * @param mobile 手机号码
	 * @param code 验证码
	 */
	public LianlPay(String cardNo, String accName, String accId, String mobile, String code) {
		super();
		this.cardNo = cardNo;
		this.accName = accName;
		this.accId = accId;
		this.mobile = mobile;
		this.code = code;
	}
	
	
	/**
	 * 绑定银行卡（卡号+姓名+身份证验证）
	 * 
	 * @param cardNo 银行卡号
	 * @param accName 姓名
	 * @param accId 身份证
	 */
	public LianlPay(String cardNo, String accName, String accId) {
		super();
		this.cardNo = cardNo;
		this.accName = accName;
		this.accId = accId;
	}

	/**
	 * 单笔支付（充值）
	 * 
	 * @param bindId 绑定号
	 * @param amount 交易金额
	 * @param remarks 备注
	 */
	public LianlPay(String bindId, long amount, String remarks) {
		super();
		this.bindId = bindId;
		this.amount = amount;
		this.remarks = remarks;
	}

	/**
	 * 付款（提现，需绑定号）
	 * 
	 * @param bindId 绑定号
	 * @param amount 交易金额
	 * @param payType 付款类型
	 * @param remarks 备注
	 */
	public LianlPay(String bindId, long amount, int payType, String remarks) {
		super();
		this.bindId = bindId;
		this.amount = amount;
		this.payType = payType;
		this.remarks = remarks;
	}
	
	/**
	 * 付款（提现，无绑定号）
	 * 
	 * @param cardNo 提现卡号
	 * @param accName 持卡人姓名
	 * @param accId 持卡人身份证号码
	 * @param mobile 预留手机号
	 * @param amount 交易金额
	 * @param payType 付款类型
	 * @param remarks 备注
	 */
	public LianlPay(String cardNo, String accName, String accId, String mobile,
			long amount, int payType, String remarks) {
		super();
		this.cardNo = cardNo;
		this.accName = accName;
		this.accId = accId;
		this.mobile = mobile;
		this.amount = amount;
		this.payType = payType;
		this.remarks = remarks;
	}

	/**
	 * 对公付款
	 * 
	 * @param cardNo 银行账户
	 * @param accName 银行账户名称
	 * @param bankName 开户行名称
	 * @param bankCode 开户行代码
	 * @param amount 交易金额
	 * @param payType 付款类型
	 * @param province 开户行省
	 * @param city 开户行市
	 * @param remarks 备注
	 */
	public LianlPay(String cardNo, String accName, String bankName,
			String bankCode, long amount, int payType, String province, 
			String city, String remarks) {
		super();
		this.cardNo = cardNo;
		this.accName = accName;
		this.bankName = bankName;
		this.bankCode = bankCode;
		this.amount = amount;
		this.payType = payType;
		this.province = province;
		this.city = city;
		this.remarks = remarks;
	}

	/**
	 * 对账查询
	 * 
	 * @param fileType 文件类型
	 * @param tranType 交易类型
	 * @param tranDate 请求交易日期
	 */
	public LianlPay(int fileType, int tranType, String tranDate) {
		super();
		this.fileType = fileType;
		this.tranType = tranType;
		this.tranDate = tranDate;
	}

	

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getBindId() {
		return bindId;
	}

	public void setBindId(String bindId) {
		this.bindId = bindId;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getAccId() {
		return accId;
	}

	public void setAccId(String accId) {
		this.accId = accId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCvn() {
		return cvn;
	}

	public void setCvn(String cvn) {
		this.cvn = cvn;
	}

	public String getExpire() {
		return expire;
	}

	public void setExpire(String expire) {
		this.expire = expire;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getBrabank() {
		return brabank;
	}

	public void setBrabank(String brabank) {
		this.brabank = brabank;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getTranDateTime() {
		return tranDateTime;
	}

	public void setTranDateTime(String tranDateTime) {
		this.tranDateTime = tranDateTime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getFileType() {
		return fileType;
	}

	public void setFileType(int fileType) {
		this.fileType = fileType;
	}

	public int getTranType() {
		return tranType;
	}

	public void setTranType(int tranType) {
		this.tranType = tranType;
	}

	public String getTranDate() {
		return tranDate;
	}

	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}

	public int getMerType() {
		return merType;
	}

	public void setMerType(int merType) {
		this.merType = merType;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@SuppressWarnings("static-access")
	public String getSign() throws UnsupportedEncodingException {
		// 待加密串
		String waitSign = this.sortParamsToSign(this.getParams()) + key;
		System.out.println("待加密串：" + waitSign);
		this.sign = this.MD5LowerCase(waitSign);
		System.out.println("获取签名：" + sign);
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Map<String, String> getParams() {
		Map<String, String> params = new HashMap<String, String>();
		if (StringUtil.isNotBlank(this.getMerId())) {
			params.put("merId", this.getMerId());
		}
		if (StringUtil.isNotBlank(this.getOrderNo())) {
			params.put("orderNo", this.getOrderNo());
		}
		if (StringUtil.isNotBlank(this.getCardNo())) {
			params.put("cardNo", this.getCardNo());
		}
		if (StringUtil.isNotBlank(this.getAccName())) {
			params.put("accName", this.getAccName());
		}
		if (StringUtil.isNotBlank(this.getBankName())) {
			params.put("bankName", this.getBankName());
		}
		if (StringUtil.isNotBlank(this.getBankCode())) {
			params.put("bankCode", bankCode);
		}
		if (StringUtil.isNotBlank(this.getAccId())) {
			params.put("accId", this.getAccId());
		}
		if (StringUtil.isNotBlank(this.getMobile())) {
			params.put("mobile", this.getMobile());
		}
		if (StringUtil.isNotBlank(this.getCode())) {
			params.put("code", this.getCode());
		}
		if (StringUtil.isNotBlank(this.getTimestamp())) {
			params.put("timestamp", this.getTimestamp());
		}
		if (StringUtil.isNotBlank(this.getTranDateTime())) {
			params.put("tranDateTime", this.getTranDateTime());
		}
		if (StringUtil.isNotBlank(this.getBindId())) {
			params.put("bindId", this.getBindId());
		}
		if (StringUtil.isNotBlank(this.getRemarks())) {
			params.put("remarks", this.getRemarks());
		}
		if (this.getAmount() > 0) {
			params.put("amount", this.getAmount() + "");
		}
		if (this.getPayType() > 0) {
			params.put("payType", this.getPayType() + "");
		}
		if (StringUtil.isNotBlank(this.getProvince())) {
			params.put("province", this.getProvince());
		}
		if (StringUtil.isNotBlank(this.getCity())) {
			params.put("city", this.getCity());
		}
		if (this.getFileType() > 0) {
			params.put("fileType", this.getFileType() + "");
		}
		if (this.getTranType() > 0) {
			params.put("tranType", this.getTranType() + "");
		}
		if (this.getMerType() > 0) {
			params.put("merType", this.getMerType() + "");
		}
		if (StringUtil.isNotBlank(this.getTranDate())) {
			params.put("tranDate", tranDate);
		}
		return params;
	}

	/**
	 * MD5加密处理
	 * 
	 * @param src
	 * @return
	 */
	private final static String MD5LowerCase(String src) {
		StringBuffer buf = new StringBuffer("");
		try {
			// 获取MD5摘要算法对象
			MessageDigest digest = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			digest.update(src.getBytes("UTF-8"));
			// 获取密文
			byte[] b = digest.digest();
			// 将密文转换成16进制的字符串形式
			int i = 0;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return buf.toString();
	}

	/**
	 * 将所有参数值按升序排序
	 * 
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private static String sortParamsToSign(Map<String, String> params) throws UnsupportedEncodingException {
		// 按参数名字典排序
		List<String> valList = Arrays.asList(params.keySet().toArray(
				new String[params.size()]));
		Collections.sort(valList);
		StringBuilder sb = new StringBuilder();
		for (String k : valList) {
			// 跳过 不被签名参数
			if (k.equals("sign")) {
				continue;
			}
			sb.append(k).append("=").append(URLEncoder.encode(params.get(k) ,"UTF-8")).append("&");
		}
		if (params.size() > 1)
			sb.delete(sb.length() - 1, sb.length()); // 去掉最后一个字符
		return sb.toString();
	}

	/**
	 * 检查绑卡参数
	 * @throws AccountException 
	 */
	public void checkAddBank() throws AccountException {
		if(StringUtil.isBlank(this.getCardNo())){
			throw new AccountException("银行卡号不能为空！");
		}
		if(StringUtil.isBlank(this.getAccName())){
			throw new AccountException("真实姓名不能为空！");
		}
//		if(StringUtil.isBlank(this.getMobile())){
//			throw new AccountException("手机号码不能为空！", 2);
//		}
//		if(StringUtil.isBlank(this.getCode())){
//			throw new AccountException("验证码不能为空！", 2);
//		}
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCharsetName() {
		return charsetName;
	}

	public void setCharsetName(String charsetName) {
		this.charsetName = charsetName;
	}

	public String getOidPartner() {
		return oidPartner;
	}

	public void setOidPartner(String oidPartner) {
		this.oidPartner = oidPartner;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getBusiPartner() {
		return busiPartner;
	}

	public void setBusiPartner(String busiPartner) {
		this.busiPartner = busiPartner;
	}

	public String getDtOrder() {
		return dtOrder;
	}

	public void setDtOrder(String dtOrder) {
		this.dtOrder = dtOrder;
	}

	public String getNameGoods() {
		return nameGoods;
	}

	public void setNameGoods(String nameGoods) {
		this.nameGoods = nameGoods;
	}

	public String getInfoOrder() {
		return infoOrder;
	}

	public void setInfoOrder(String infoOrder) {
		this.infoOrder = infoOrder;
	}

	public String getMoneyOrder() {
		return moneyOrder;
	}

	public void setMoneyOrder(String moneyOrder) {
		this.moneyOrder = moneyOrder;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getNotifyCUrl() {
		return notifyCUrl;
	}

	public void setNotifyCUrl(String notifyCUrl) {
		this.notifyCUrl = notifyCUrl;
	}

	public String getUrlReturn() {
		return urlReturn;
	}

	public void setUrlReturn(String urlReturn) {
		this.urlReturn = urlReturn;
	}

	public String getUserreqIp() {
		return userreqIp;
	}

	public void setUserreqIp(String userreqIp) {
		this.userreqIp = userreqIp;
	}

	public String getUrlOrder() {
		return urlOrder;
	}

	public void setUrlOrder(String urlOrder) {
		this.urlOrder = urlOrder;
	}

	public String getValidOrder() {
		return validOrder;
	}

	public void setValidOrder(String validOrder) {
		this.validOrder = validOrder;
	}

	public String getNoAgree() {
		return noAgree;
	}

	public void setNoAgree(String noAgree) {
		this.noAgree = noAgree;
	}

	public String getShareingData() {
		return shareingData;
	}

	public void setShareingData(String shareingData) {
		this.shareingData = shareingData;
	}

	public String getRiskItem() {
		return riskItem;
	}

	public void setRiskItem(String riskItem) {
		this.riskItem = riskItem;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getBackUrl() {
		return backUrl;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
}
