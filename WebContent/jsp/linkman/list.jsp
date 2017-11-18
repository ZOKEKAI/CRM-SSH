<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>联系人列表</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css rel=stylesheet>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>

<META content="MSHTML 6.00.2900.3492" name=GENERATOR>

<script type="text/javascript">

	$(function(){
		var url = "${pageContext.request.contextPath}/customer_findAllByAjax.action"
		$.post(url , function(result){
			$(result).each(function(i , n ){
				$("#customer").append("<option value='"+n.cust_id+"'>"+n.cust_name+"</option>");
				
			});
		} , "json")
	})
	
	function del(id){
		
		var flag = confirm("确定删除该联系人吗?");
		
		if(flag){
			location.href="${pageContext.request.contextPath}/linkMan_delete?lkm_id="+id;
		}
	}
</script>
</HEAD>
<BODY>
	<FORM id="customerForm" name="customerForm" action="${pageContext.request.contextPath }/linkMan_findByPage.action" method=post>		
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
								<TD class=manageHead>当前位置：联系人管理 &gt; 联系人列表</TD>
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
													<TD>联系人名称：</TD>
													<TD><INPUT class=textbox id=sChannel2 style="WIDTH: 80px" maxLength=50 name="lkm_name"></TD>
													
													<TD>性别：</TD>
													<TD>
														<select name="lkm_gender" class=textbox id="lkm_gender" style="WIDTH: 80px;height: 21px">
															<option value="">--请选择--</option>
															<option value="男">男</option>
															<option value="女">女</option>
														</select>
													</TD>
													<TD>所属客户：</TD>
													<TD>
														<select name="customer.cust_id" class=textbox id="customer" style="WIDTH: 120px;height: 21px">
															<option value="">--请选择--</option>
														</select>
													</TD>
													
													
													<TD>办公电话：</TD>
													<TD><INPUT class=textbox id=sChannel2 style="WIDTH: 80px" maxLength=50 name="lkm_phone"></TD>
													
													<TD>邮箱：</TD>
													<TD><INPUT class=textbox id=sChannel2 style="WIDTH: 80px" maxLength=50 name="lkm_email"></TD>
													
													<TD>电话：</TD>
													<TD><INPUT class=textbox id=sChannel2 style="WIDTH: 80px" maxLength=50 name="lkm_mobile"></TD>
													
													<TD>QQ：</TD>
													<TD><INPUT class=textbox id=sChannel2 style="WIDTH: 80px" maxLength=50 name="lkm_qq"></TD>
													
													
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
													<TD>联系人名称</TD>
													<TD>性别</TD>
													<TD>办公电话</TD>
													<TD>手机</TD>
													<TD>邮箱</TD>
													<TD>职位</TD>
													<TD>QQ</TD>
													<TD>备注</TD>
													<TD>所属客户</TD>
													<TD>操作</TD>
												</TR>
												<!-- pageBean  --- push  遍历出来的每一个成员就是一个linkMan-->
												<s:iterator value="list" >
													<TR
														style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
														<TD>${lkm_name }</TD>
														<TD>${lkm_gender }</TD>
														<TD>${lkm_phone }</TD>
														<TD>${lkm_mobile }</TD>
														<TD>${lkm_email }</TD>
														<TD>${lkm_position }</TD>
														<TD>${lkm_qq }</TD>
														<TD>${lkm_memo }</TD>
														<TD>${customer.cust_name}</TD>
														<TD>
														<a href="${pageContext.request.contextPath }/linkMan_edit?lkm_id=${lkm_id}">修改</a>
														&nbsp;&nbsp;
														<a href="javascript:del(${lkm_id })">删除</a>
														</TD>
													</TR>
												</s:iterator>
												<%-- <c:forEach items="${linkmans }" var="linkman">
													<TR
														style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
														<TD>${linkman.lkmName }</TD>
														<TD>${linkman.lkmGender }</TD>
														<TD>${linkman.lkmPhone }</TD>
														<TD>${linkman.lkmMobile }</TD>
														<TD>${linkman.lkmEmail }</TD>
														<TD>${linkman.lkmPosition }</TD>
														<TD>${linkman.lkmMemo }</TD>
														<TD>${linkman.lkmCustomer.custName}</TD>
														<TD>
														<a href="${pageContext.request.contextPath }/linkman/LinkManServlet?method=editLinkManUI&lkmId=${linkman.lkmId}">修改</a>
														&nbsp;&nbsp;
														<a href="${pageContext.request.contextPath }/linkman/LinkManServlet?method=deleteLinkMan&lkmId=${linkman.lkmId}">删除</a>
														</TD>
													</TR>
												</c:forEach> --%>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
								
								<%-- <%@ include file="" %> --%>
								<!-- 包含进来page页面，此处使用的是动态包含 ， 
									静态包含 ： 先包含，在编译、 这里会放置所有的标签内容 ， 完完整整的拷贝代码过来放置在这个地方。
									动态包含：  先编译、然后包含你的结果、然后和我这边也变编译后的结果一起输出
									 -->
								<jsp:include page="/jsp/page.jsp"></jsp:include>
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
					<TD align="center" width="100%" background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	</FORM>
	<s:debug></s:debug>
</BODY>
</HTML>
