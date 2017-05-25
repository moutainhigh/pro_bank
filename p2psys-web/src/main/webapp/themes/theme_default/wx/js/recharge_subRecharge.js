define(function(require,exports,module){
	require('jquery');
	$.ajax({
		url:"/member/cash/myBank.html?random="+Math.random(),
		type:"get",
		dataType:"json",
		success:function(data){
				var str = "";  
				var target=document.getElementById("otherbankTr");
				var cardlist=document.getElementById("cardlist");
			    if(data.ret_code!='0000'||data.count == '0')
			    {
//			      str += "暂无银行卡"; 
			      cardlist.style.display="none";
			      target.style.display="block";
			    }else{
			      var cardtype;
			      var length = data.agreement_list.length;
//			        for(var i=0; i<length; i++)
			      	if(length>0){
			                 if(data.agreement_list[0].card_type=='2'){
			                    cardtype="借记卡";
			                 }else{
			                    cardtype="信用卡";1
			                 }
//			                str += "<input type=\"radio\" name=\"no_agree\" value=\""+data.agreement_list[i].no_agree+"\"/>"
//			                +data.agreement_list[i].bank_name+"&nbsp;"
//			                +cardtype+"&nbsp;"
//			                +data.agreement_list[i].card_no
//			                +"&nbsp;&nbsp;&nbsp;<input type=\"button\" value=\"解绑银行卡\" onclick=\"unllbank('"+data.agreement_list[i].no_agree+"')\">"
//			                +"<br/>";
			                
//			                str += "<li><div class=\"bank_radio bank_radio_hover\">"
//                        	+"<b></b><label><i>"
//                        	+data.agreement_list[i].bank_name+"</i><em>尾号：******"+data.agreement_list[i].card_no+"</em></label>"
//                        	+"<input type=\"radio\" name=\"no_agree\" value=\""+data.agreement_list[i].no_agree+"\" data-value=\"<i>"+data.agreement_list[i].bank_name+"</i><em>尾号："+data.agreement_list[i].bank_name+"</em>\" style=\"display:none;\" checked=\"checked\"/>"
//                        	+"</div></li>";
                        	
                        	str+="<div class=\"bankCard\">"
                        	+"<input type=\"radio\" name=\"no_agree\" value=\""+data.agreement_list[0].no_agree+"\" style=\"display:none;\" checked=\"checked\" />"
                        	+"<img class=\"bank_icon\" src=\"/themes/theme_default/wx/img/setCard.png\">"
                        	+"<div class=\"bank_name\">"+data.agreement_list[0].bank_name
			        		+"<span class=\"card_type\">"+cardtype+"</span></div>"
			        		+"<div class=\"card_num\">******"+data.agreement_list[0].card_no+"</div>";
                        	
			          }
			        target.style.display="none";
			    }
			    cardlist.innerHTML=str;
		}
	})
	
	//判断是否充值成功

	require.async('jquery.form',function(){
		$("#recPayBtn").click(function(){
			 binBank();
		 $("#payForm").ajaxSubmit({
		     type: "post",
		     url: "/member/recharge/dollPayRecharge.html",
		     dataType: "json",
		     success: function(data){
		    	 if(data.result==false){
						require.async(['/plugins/layer-v1.8.4/skin/layer.css','/plugins/layer-v1.8.4/layer.min'],function(){
							//构造确认框DOM
							$.layer({
								type: 1,
								closeBtn: true,
				                title: "&nbsp;",
							    area: ['450px', '190px'],
							    border: [1, 1, '#cecfd0'],
							    page: {
							        html: "<div class='tipsWrap w450'>"+"<div class='tipsTxt'>"+"<i class='iconfont tipIco'>"+"&#xe63c;"+"</i><span>"+data.msg+"</span></div>"+"<div class='tipsBtnBar'>"+"<a href='/member/recharge/subRecharge.html?payType=1' class='okBtn'>"+"重新充值"+"</a></div></div>"
							    }
							});
						});
					}else{
						require.async(['/plugins/layer-v1.8.4/skin/layer.css','/plugins/layer-v1.8.4/layer.min'],function(){
							//构造确认框DOM
							$.layer({
								type: 1,
								closeBtn: true,
				                title: "&nbsp;",
							    area: ['450px', '190px'],
							    border: [1, 1, '#cecfd0'],
							    page: {
							        html: "<div class='tipsWrap w450'>"+"<div class='tipsTxt'>"+"<i class='iconfont tipIco'>"+"&#xe63c;"+"</i><span>"+"恭喜您，充值成功！"+"</span></div>"+"<div class='tipsBtnBar'>"+"<a href='/wx/account/main.html' class='okBtn'>"+"确定"+"</a></div></div>"
							    }
							});
						});
					}
		     }
		 });
		})

	})
		

	
	
	require.async('radio',function(){
		$('.underline-bank input[type="radio"]').minRadio({
			checkedClass:"bank_radio_hover", 
			Element:"bank_radio",	
			showTxt:true 
		});	
		$('.payList input[type="radio"]').minRadio({
			checkedClass:"bank_radio_hover", 
			Element:"bank_radio",	
			showTxt:false
		});	
		$('.bank_list input[type="radio"]').minRadio({
			checkedClass:"bank_radio_hover", 
			Element:"bank_radio",	
			showTxt:false
		});
	});
	
	//设置选中效果
	
	
	//设置选中效果
	//$(".on_off:checked").siblings().find(".switchBtn").addClass("on");
	
	//TAB切换
	require.async('common1',function(){
		$(".user_right_main").tabChange({
			isClick:true,
            isHover:false,
			childLi:".cz-fs span",//tab选项卡
			childContent:".user_cash_box",//tab内容
			hoverClassName:"active",//选中当前选项卡的样式
			callBack:false
		});
	});
	//
	$(".cz-fs span").on("click",function(){
		$("#payForm").each(function() {   
		   this.reset();
		   $(".bank_radio").removeClass("bank_radio_hover");
		}); 
		$(".underline-bank .bank_radio").eq(0).addClass("bank_radio_hover");
		var statusVal =$(this).attr("data-value");
		$('.paymentStatus').val(statusVal)
		if(statusVal==3){
			$("#payForm").attr("action","/member/recharge/doOfflineRecharge.html");
		}else{
			$("#payForm").attr("action","/member/recharge/doRecharge.html");
		}
	})
	
    
    	//表单验证
	require.async("/plugins/jquery-validation-1.13.0/jquery.validate",function(){
		require.async('/plugins/jquery-validation-1.13.0/additional-methods',function(){
		$("#payForm").validate({
			rules:{
				accName:{
			   		required: true,
			   		realName:true
				},
				accId:{
			   		required: true,
			   		isIdCardNo:true,
				},
				comfirmAccount:{
					required:true,
					equalTo:"#infoyhzh"
				},
				code:{
					required:true
				}
			},
			messages:{
				accName:{
				   	required:"请填写您的真实姓名",
				   	realName:"真实姓名不正确"
						},
				accId:{
				   	required:"请填写您的身份证号码",
				},
				card_no:{
					required:"确认银行账号",
				},
				mobile:{
					required:"请输入验证码",
				},
			},
			errorElement:"em",
			errorPlacement:function(error,element){
				error.appendTo(element.parent().find("span"));
			}
		});
		});
	})
    
//    $("#recPayBtn2").click(function(){
//    	var obj = $("input[name='no_agree']:checked").val();
//    	var cardno = document.getElementById("card_no").value;
//    	var accName = document.getElementById("accName").value;
//    	var mobile = document.getElementById("mobile").value;
//    	var accId = document.getElementById("accId").value;
//    	alert(obj);
//    	if (obj!=undefined) {
//            document.payForm.submit();
//            require.async(['/plugins/layer-v1.8.4/skin/layer.css','/plugins/layer-v1.8.4/layer.min'],function(){
//				 $.layer({
//                      type: 1,
//                      closeBtn: [0,true],
//                      title: "&nbsp;",
//                      area: ['460px', '194px'],
//                      border: [1, 1, '#cecfd0'],
//                      page: {
//                          html: '<div class="tipsWrap"><dl><dt>充值完成前，请不要关闭本窗口;</dt><dd>充值完成后，请根据您的充值结果点击下面按钮。</dd></dl><div class="tipsBtnBar"><a href="javascript:;" class="okBtn">充值成功</a><a href="javascript:;" class="cancleBtn">充值失败</a></div></div>'
//                      }
//                  });
//					$(".okBtn").click(function(){
//						window.location.href = "/member/recharge/log.html";
//					});
//					$(".cancleBtn").click(function(){
//						layer.closeAll();
//						$(".valicode_img").each(function(){
//							$(this).attr("src",'/validimg.html?t=' + Math.random());
//		        		})
//					});
//		    	});
//            return;
//        }else if(accName==''){
//	        alert("请输入您的真实姓名！");
//	        return;
//	    }else if(mobile==''){
//	        alert("请输您预留的手机号码！");
//	        return;
//		}else if(accId==''){
//	        alert("请输入您的身份证号！");
//	        return;
//	    }else if(cardno==''){
//	        alert("请输入您的银行卡号！");
//	        return;
//		}else{ 	 
//	    	 $.ajax({
//	    		 url:"/member/cash/binBank.html?card_no="+cardno+"&random="+Math.random(),
//	    		 data:{card_no:cardno},
//	    		 type:"get",
//	    		 dataType:"json",
//	    		 success:function(data){
////	    			 alert(data.ret_code);
////	    			 alert(data.bank_name);
//	    			 if(data.ret_code=='0000'){
//	    				 document.getElementById("cardtip").innerHTML=data.bank_name;
//	    				 document.payForm.submit();
//	    			 }else{
//	    				 document.getElementById("cardtip").innerHTML="卡号有误，请检查！";
//	    			 }
//	    		 }
//	    	 })
//	     }
//	})
	
	$("#otherBanks").click(function(){
    	var target=document.getElementById("otherbankTr");
	    if (target.style.display=="block"){
	          target.style.display="none";
	    } else {
	          target.style.display="block";
	    }
	})
	
	function show_recharge(){
		$(".J_valicode_img").each(function(){
			$(this).attr("src",'/validimg.html?t=' + Math.random());
		})
		jQuery( "#modal_dialog" ).dialog({ autoOpen: false , modal: true ,height: 160,width:360 });
		jQuery( "#modal_dialog" ).dialog( "open" );
	}
	
	function isSelect(){
	    var isSelect = false;
	    var no_agrees = document.getElementsByName('no_agree');
	    for(var i=0;no_agrees.length;i++){
		    if(no_agrees[i].checked)
		    {
		       isSelect=true; 
		       break;
		    }
	    }
	     return isSelect;
	}
	document.getElementById("card_no").onfocus=function(){
	    var no_agrees = document.getElementsByName('no_agree');
		for(var i=0;no_agrees.length;i++){
		if(no_agrees[i].checked)
		{
//		   no_agrees[i].checked=false; //不选中
		}
	} 
	}
	
});