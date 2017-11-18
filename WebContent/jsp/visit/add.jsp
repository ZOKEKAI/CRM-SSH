<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>添加客户拜访</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/Style.css"
	type="text/css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/Manage.css"
	type="text/css" rel="stylesheet" />
<meta content="MSHTML 6.00.2900.3492" name="GENERATOR">

<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery/jquery-1.4.2.js"></script>

<!-- 日期插件，使用jquery -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/jquery/jquery.datepick.css"
	type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery/jquery.datepick.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery/jquery.datepick-zh-CN.js"></script>

<script type="text/javascript">
	$(function() {
		//使用class属性处理  'yy-mm-dd' 设置格式"yyyy/mm/dd"
		$('#timeId').datepick({
			//dateFormat : 'yy-mm-dd'
			dateFormat : 'yy年mm月dd日'
		});
		$('#nextTimeId').datepick({
			dateFormat : 'yy年mm月dd日'
		});

	});
	
	$(function(){
		var url = "${pageContext.request.contextPath}/customer_findAllByAjax.action"
		$.post(url , function(result){
			$(result).each(function(i , n ){
				$("#customer").append("<option value='"+n.cust_id+"'>"+n.cust_name+"</option>");
				
			});
		} , "json")
	})
	
	function changeCustomer(){
		//根据选中的客户，获取到属于该客户的联系人
		var url = "${pageContext.request.contextPath}/linkMan_findAllByCid.action";
		var id = $("#customer").val();
		$.post(url , {cid:id} , function(result){
			
			//遍历之前清空：
			$("#linkman").html("<option value=''>-请选择-</option>");
			
			$(result).each(function(i , n ){
				$("#linkman").append("<option value='"+n.lkm_id+"'>"+n.lkm_name+"</option>");
			});
		}, "json");
	}
</script>

</head>
<body>
	<form id="form1" name="form1"
		action="${pageContext.request.contextPath}/saleVisit_save"
		method="post">

		<table cellspacing="0" cellpadding="0" width="98%" border="0">
			<tbody>
				<tr>
					<td width="15"><img
						src="${pageContext.request.contextPath}/images/new_019.jpg"
						border="0"></td>
					<td width="100%"
						background="${pageContext.request.contextPath}/images/new_020.jpg"
						height="20"></td>
					<td width="15"><img
						src="${pageContext.request.contextPath}/images/new_021.jpg"
						border="0"></td>
				</tr>
			</tbody>
		</table>
		<table cellspacing="0" cellpadding="0" width="98%" border="0">
			<tbody>
				<tr>
					<td width="15"
						background="${pageContext.request.contextPath}/images/new_022.jpg"><img
						src="${pageContext.request.contextPath}/images/new_022.jpg"
						border="0"></td>
					<td vAlign=top width="100%" bgcolor="#ffffff">
						<table cellspacing="0" cellpadding="5" width="100%" border="0">
							<tr>
								<td class="manageHead">当前位置：客户拜访管理 &gt; 添加客户拜访</td>
							</tr>
							<tr>
								<td height="2"></td>
							</tr>
						</table>
						<table cellspacing="0" cellpadding="5" border="0">
							<tr>
								<td>客户名称：</td>
								<td>
									<select onchange="changeCustomer()" name="customer.cust_id" id="customer" class="textbox"
										style="width: 180px; height: 21px;">
											<option value="">-请选择-</option>
									</select>

								</td>

								<td>拜访时间：</td>
								<td><s:textfield class="textbox" id="timeId"
										style="width: 180px" name="visit_time" readonly="readonly" /></td>

							</tr>

							<tr>
								<td>被拜访人：</td>
								<td>
									<select  name="linkMan.lkm_id" id="linkman" class="textbox"
										style="width: 180px; height: 21px;">
											<option value="">-请选择-</option>
									</select>
								</td>
								<td>拜访地点：</td>
								<td><s:textfield class="textbox" style="width: 180px"
										maxLength="50" name="visit_addr" /></td>
							</tr>

							<tr>
								<td>下次拜访时间 ：</td>
								<td><s:textfield class="textbox" id="nextTimeId"
										style="width: 180px" maxLength="50" name="visit_nexttime" /></td>

							</tr>

							<tr>
								<td>拜访详情 ：</td>
								<td><s:textarea rows="5" cols="26" name="visit_detail" />
								</td>
							</tr>

							<tr>
								<td></td>
								<td><s:actionerror /></td>
							</tr>

							<tr>
								<td rowspan="2"><input class="button" id="sButton2"
									type="submit" value="保存 " name="sButton2"></td>
							</tr>
						</table>


					</td>
					<td width="15"
						background="${pageContext.request.contextPath}/images/new_023.jpg">
						<img src="${pageContext.request.contextPath}/images/new_023.jpg"
						border="0">
					</td>
				</tr>
			</tbody>
		</table>
		<table cellspacing="0" cellpadding="0" width="98%" border="0">
			<tbody>
				<tr>
					<td width="15"><img
						src="${pageContext.request.contextPath}/images/new_024.jpg"
						border="0"></td>
					<td align="center" width="100%"
						background="${pageContext.request.contextPath}/images/new_025.jpg"
						height="15"></td>
					<td width="15"><img
						src="${pageContext.request.contextPath}/images/new_026.jpg"
						border="0"></td>
				</tr>
			</tbody>
		</table>
	</form>
	<s:debug></s:debug>
</body>
</html>
