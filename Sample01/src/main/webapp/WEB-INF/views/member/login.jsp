<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>

<h1>LOGIN</h1>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<form role="form" action="/member/loginPost" method="post">
			
				<div class="form-group">
					<label for="userid">
						아이디
					</label>
					<input type="text" class="form-control" id="userid" name="userid" />
				</div>
				
				<div class="form-group">
					 
					<label for="userpw">
						패스워드
					</label>
					<input type="password" class="form-control" id="userpw" name="userpw" />
				</div>
				
				<button type="submit" class="btn btn-primary"> 로그인 </button>
				
			</form>
		</div>
	</div>
</div>

<hr/>

<%@ include file="../include/footer.jsp" %>
