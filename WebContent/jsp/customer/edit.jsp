<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>编辑客户</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css rel=stylesheet>
<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	function loadDict(type_code , tag_id , oldValue){
		var url = "${pageContext.request.contextPath}/baseDict_findByAjax";
		//获取字典数据  -- 所属行业  - 001
		$.post(url ,{dict_type_code:type_code} , function(result){
			//遍历这个result ，这个result其实就是一个集合 、 数组 、 遍历一次，就得到一个字典对象
			$(result).each(function(i , n){ // 遍历一次，执行一次function,  i: 索引 ， n: 遍历得到的对象，字典对象
				//动态往标签添加内容。
				$(tag_id).append("<option value='"+n.dict_id+"'>"+n.dict_item_name+"</option>");
			
				/*
					<select id="cust_industry">
						<option value='1'>电子商务</option>
						<option value='2'>房地产</option>
						<option value='3' selected="selected">教育培训</option>
					</select>
				*/
			});
			//选中处理
			//var oldValue = "${cust_industry.dict_id}"; // 3
			$(tag_id).find("option[value='"+oldValue+"']").attr("selected","selected");
			
		} , "json");
	}
	
	$(function(){
		//alert("执行了入口函数")  set("customer" , customer)
		loadDict("001" , "#cust_industry" , "${customer.cust_industry.dict_id}"); //所属行业
		loadDict("002" , "#cust_source" , "${customer.cust_source.dict_id}");//客户来源
		loadDict("006" , "#cust_level" , "${customer.cust_level.dict_id}"); //客户级别
	})	
	
</script>
</HEAD>
<BODY>
	<FORM id=form1 name=form1 action="${pageContext.request.contextPath }/customer_update" method=post>
		<input type="hidden" name="cust_id" value="${customer.cust_id}">
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg" border=0></TD>
					<TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg" height=20></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_022.jpg">
						<IMG src="${pageContext.request.contextPath }/images/new_022.jpg" border=0>
					</TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：客户管理 &gt; 编辑客户</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>						
						<TABLE cellSpacing=0 cellPadding=5  border=0>
							<TR>
								<td>客户名称：</td>
								<td>
									<INPUT class=textbox id=sChannel2 style="WIDTH: 180px" maxLength=50 name="cust_name" value="${customer.cust_name}">
								</td>
								<td>所属行业 ：</td>
								<td>
									<select name="cust_industry.dict_id" class=textbox id="cust_industry" style="WIDTH: 180px;height:21px">
										<option value="">--请选择--</option>
									</select>
								</td>
							</TR>							
							<TR>	
								<td>信息来源 ：</td>
								<td>
									<select name="cust_source.dict_id" class=textbox id="cust_source" style="WIDTH: 180px;height:21px">
										<option value="">--请选择--</option>
									</select>
								</td>
								<td>客户级别：</td>
								<td>
									<select name="cust_level.dict_id" class=textbox id="cust_level" style="WIDTH: 180px;height:21px">
										<option value="">--请选择--</option>
									</select>								
								</td>
							</TR>
							<TR>
								<td>联系地址 ：</td>
								<td>
									<INPUT class=textbox id=sChannel2 style="WIDTH: 180px" maxLength=50 name="cust_address" value="${customer.cust_address}">
								</td>
								<td>联系电话 ：</td>
								<td>
									<INPUT class=textbox id=sChannel2 style="WIDTH: 180px" maxLength=50 name="cust_phone" value="${customer.cust_phone}">
								</td>
							</TR>
							<TR>
								<td>客户资质 ：</td>
								<td>
									<img src="${pageContext.request.contextPath }/${customer.cust_image}">
								</td>
							</TR>
							<tr>
								<td rowspan=2>
									<INPUT class=button id=sButton2 type=submit value=" 保存 " name=sButton2>
								</td>
							</tr>
						</TABLE>
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg">
						<IMG src="${pageContext.request.contextPath }/images/new_023.jpg" border=0>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg" border=0></TD>
					<TD align="center" width="100%" background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg"	border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	</FORM>
	
	<s:debug></s:debug>
</BODY>
</HTML>
