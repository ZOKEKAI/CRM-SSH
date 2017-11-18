<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>客户列表</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
<SCRIPT language=javascript>
	function to_page(page){
		
		
		//如果有人带了参数过来，就给一个标签id是page赋值 。 赋的就是带过来的数据
		if(page){
			$("#page").val(page);
		}else{
			//没有带参数， 点击了go 要跳转具体页。
			
			var requestPage =$("#page").val() ;
			var totalPage =  "${totalPage}"; 
			
			if(Number(requestPage)  > Number(totalPage)){
				alert("超过了最大页数，拒绝跳转")
				//return ;
				
				//超过了最大页，就到达最后一页
				$("#page").val(totalPage);
			}
			
		}
		
		//提交表单
		document.customerForm.submit();	
	}
	
	function changePageSize(){
		//提交表单
		document.customerForm.submit();	
	}
	
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
		loadDict("001" , "#cust_industry" , "${cust_industry.dict_id}"); //所属行业
		loadDict("002" , "#cust_source" , "${cust_source.dict_id}");//客户来源
		loadDict("006" , "#cust_level" , "${cust_level.dict_id}"); //客户级别
	})	
	
	function del(id){
		var flag = confirm("确定删除该客户吗?");
		if(flag){
			location.href="${pageContext.request.contextPath }/customer_delete?cust_id="+id;
		}
	}
</SCRIPT>
<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>

<BODY>
	<FORM id="customerForm" name="customerForm" action="${pageContext.request.contextPath }/customer_findByPage.action" method=post>	
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
								<TD class=manageHead>当前位置：客户管理 &gt; 客户列表</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE borderColor=#cccccc cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
							<TBODY>
								<TR>
									<TD height=25>
										<TABLE cellSpacing=0 cellPadding=2 border=0>
											<TBODY>
												<TR>
													<TD>客户名称：</TD>
													<TD>
														<s:textfield class="textbox" id="sChannel2" style="WIDTH: 80px" maxLength="50" name="cust_name"></s:textfield>
													
													
													<td>客户级别：</td>
													<td>
														<select name="cust_level.dict_id" class="textbox" id="cust_level" style="WIDTH: 80px;height: 21px">
															<option value="">--请选择--</option>
														</select>								
													</td>
													
													<td>所属行业 ：</td>
													<td>
														<select name="cust_industry.dict_id" class=textbox id="cust_industry" style="WIDTH: 80px;height: 21px">
															<option value="">--请选择--</option>
														</select>
													</td>
													
													<td>客户来源 ：</td>
													<td>
														<select name="cust_source.dict_id" class=textbox id="cust_source" style="WIDTH: 80px;height: 21px">
															<option value="">--请选择--</option>
														</select>
													</td>
													
													
													<TD>电话：</TD>
													<TD>
														<s:textfield  class="textbox" id="sChannel2" style="WIDTH: 80px" maxLength="50" name="cust_phone"></s:textfield>
													
													<TD>客户地址：</TD>
													<TD>
														<s:textfield  class="textbox" id="sChannel2" style="WIDTH: 80px" maxLength="50" name="cust_address"></s:textfield>
													
													
													
													<TD><INPUT class=button id=sButton2 type=submit value=" 筛选 " name=sButton2></TD>
												</TR>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
								<TR>
									<TD>
										<TABLE id=grid style="BORDER-TOP-WIDTH: 0px; FONT-WEIGHT: normal; BORDER-LEFT-WIDTH: 0px; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 0px; BORDER-BOTTOM-COLOR: #cccccc; WIDTH: 100%; BORDER-TOP-COLOR: #cccccc; FONT-STYLE: normal; BACKGROUND-COLOR: #cccccc; BORDER-RIGHT-WIDTH: 0px; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #cccccc" cellSpacing=1 cellPadding=2 rules=all border=0>
											<TBODY>
												<TR style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
													<TD>客户名称</TD>
													<TD>客户级别</TD>
													<TD>客户来源</TD>
													<TD>所属行业</TD>
													<TD>联系地址</TD>
													<TD>联系电话</TD>
													<TD>操作</TD>
												</TR>
												<c:forEach items="${list}" var="customer">
													<TR style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
														<TD>${customer.cust_name }</TD>
														<TD>${customer.cust_level.dict_item_name }</TD>
														<TD>${customer.cust_source.dict_item_name }</TD>
														<TD>${customer.cust_industry.dict_item_name }</TD>
														<TD>${customer.cust_address }</TD>
														<TD>${customer.cust_phone }</TD>
														<TD>
														<a href="${pageContext.request.contextPath }/customer_edit?cust_id=${customer.cust_id}">修改</a>
														&nbsp;&nbsp;
														<%-- <a href="${pageContext.request.contextPath }/customer_delete?cust_id=${customer.cust_id}">删除</a> --%>
														<a href="javascript:del(${customer.cust_id})">删除</a>
														</TD>
													</TR>	
												</c:forEach> 
											</TBODY>
										</TABLE>
									</TD>
								</TR>
								<TR>
									<TD>
										<SPAN id=pagelink>
											<DIV style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
												共[<B>${totalSize}</B>]条记录,[<B>${totalPage}</B>]页
												,每页显示
												<select name="pageSize" onchange="changePageSize()">
													<option value="5" <c:if test="${pageSize==5 }">selected</c:if>>5</option>
													<option value="10" <c:if test="${pageSize==10 }">selected</c:if>>10</option>
													<option value="15" <c:if test="${pageSize==15 }">selected</c:if>>15</option>
												</select>
												条
												[
													<s:if test="currentPage == 1">
														前一页
													</s:if>
													<s:else>
														<A href="javascript:to_page(${currentPage-1})">前一页</A>
													</s:else>
												
												]
												<B>${currentPage}</B>
												[
												
													<s:if test="currentPage == totalPage">
														后一页
													</s:if>
													<s:else>
														<A href="javascript:to_page(${currentPage+1})">后一页</A>
													</s:else>
												
												] 
												到
												<input type="text" size="3" id="page" name="currentPage" />
												页
												
												<input type="button" value="Go" onclick="to_page()"/>
											</DIV>
										</SPAN>
									</TD>
								</TR>
							</TBODY>
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
					<TD align=middle width="100%" background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	</FORM>
	
	<s:debug></s:debug>
</BODY>
</HTML>
