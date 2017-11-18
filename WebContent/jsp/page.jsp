<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
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
</script>	
<body>

	<TR>
		<TD><SPAN id=pagelink>
				<DIV style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
					共[<B>${totalSize}</B>]条记录,[<B>${totalPage}</B>]页 ,每页显示 <select
						name="pageSize" onchange="changePageSize()">
						<option value="5" <c:if test="${pageSize==5 }">selected</c:if>>5</option>
						<option value="10" <c:if test="${pageSize==10 }">selected</c:if>>10</option>
						<option value="15" <c:if test="${pageSize==15 }">selected</c:if>>15</option>
					</select> 条 [
					<s:if test="currentPage == 1">
														前一页
													</s:if>
					<s:else>
						<A href="javascript:to_page(${currentPage-1})">前一页</A>
					</s:else>

					] <B>${currentPage}</B> [

					<s:if test="currentPage == totalPage">
														后一页
													</s:if>
					<s:else>
						<A href="javascript:to_page(${currentPage+1})">后一页</A>
					</s:else>

					] 到 <input type="text" size="3" id="page" name="currentPage" /> 页

					<input type="button" value="Go" onclick="to_page()" />
				</DIV>
		</SPAN></TD>
	</TR>

</body>
</html>