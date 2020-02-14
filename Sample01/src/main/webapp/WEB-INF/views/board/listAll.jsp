<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../include/header.jsp" %>

<script>
$(document).ready(function() {
	var msg = "${msg}";
	if (msg == "delete_success") {
		alert("삭제 되었습니다.");
	}
	
	// 페이지 10개, 20개, .. 보기
	$("#selNline").change(function () {
		var val = $(this).val();
// 		console.log(val);
		
		location.href = "/board/listAll?perPage=" + val;
		
		
	});
	
	
	$(".page-link").click(function(e) {
		e.preventDefault(); // 브라우저의 기본 기능 막기
		var page = $(this).attr("data-page");
		$("input[name=page]").val(page);
		$("#frmPage").submit();
	});
	
// 	제목 클릭할때 페이지 유지
	$(".board-title").click(function (e) {
		e.preventDefault(); // 브라우저의 기본 기능 막기
		var bno = $(this).attr("data-bno");
		$("input[name=bno]").val(bno);
		$("#frmPage").attr("action", "/board/read");
		$("#frmPage").submit();
	});
	
	// 글쓰기
	$("#btnRegisetr").click(function() {
// 		location.href = "/board/register?page=${pagingDto.page}&perPage=${pagingDto.perPage}";
		$("#frmPage").attr("action", "/board/register").submit();
	
	});
});
</script>
${pagingDto}
<div class="container-fluid">
	<!-- 검색어 유지 -->
	<form id="frmPage" action="/board/listAll" method="get">
		<input type="hidden" name="bno" />
		<input type="hidden" name="page" 		value="${pagingDto.page}"/>
		<input type="hidden" name="perPage" 	value="${pagingDto.perPage}"/>
		<input type="hidden" name="searchType" 	value="${pagingDto.searchType}" />
		<input type="hidden" name="keyword" 	value="${pagingDto.keyword}" />
	</form>


	<!-- n줄씩 보기 -->
	<div class="row">
		<div class="col-md-1">
			<button type="button" id="btnRegisetr" 
					class="btn btn-danger">글쓰기</button>
			</div>
			<div class="col-md-1">		
			</div>
			<div class="col-md-1">	
			<form role="form" class="form-inline">
				<select class="form-control" id="selNline">
					<c:forEach begin="10" end="50" step="10" var="v">
						<option value="${v}"
						<c:if test="${pagingDto.perPage == v}">
							selected
						</c:if>
						>${v}줄씩 보기</option>
					</c:forEach>
				</select>	
			</form>
		</div>
		<div class="col-md-1"> 
		</div>
		<div class="col-md-8 text-center">	
			<form role="form" class="form-inline" action="/board/listAll">
				<input type="hidden" name="page" 	value="${pagingDto.page}"/>
				<input type="hidden" name="perPage" value="${pagingDto.perPage}"/>
				<select class="form-control" name="searchType">
					<option value="title"
						<c:if test="${pagingDto.searchType == 'title'}">
							selected
						</c:if>
					>제목</option>
					<option value="content"
						<c:if test="${pagingDto.searchType == 'content'}">
							selected
						</c:if>
					>내용</option>
					<option value="title_content"
						<c:if test="${pagingDto.searchType == 'title_content'}">
							selected
						</c:if>
					>제목 + 내용</option>
					<option value="writer"
						<c:if test="${pagingDto.searchType == 'writer'}">
							selected
						</c:if>
					>작성자</option>
				</select>
				<input type="text"  class="form-control" name="keyword"
						placeholder="검색어를 입력하세요." value="${pagingDto.keyword}" />
				<button type="submit" class="btn btn-warning form-control">검색</button>
			</form>
		</div>
	</div>
	
	<!-- 게시판 테이블 -->
	<div class="row">
		<div class="col-md-12">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>글번호</th>
						<th>글제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회수</th>
						
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="boardVo">
						<tr>
							<td>${boardVo.bno}</td>
							<td><a data-bno="${boardVo.bno}" class="board-title">
								${boardVo.title}&emsp;<span class="badge label-danger">${boardVo.reply_cnt}</span>
								</a>
							</td>
							<td>${boardVo.writer}</td>
							<td><fmt:formatDate value="${boardVo.regdate}"
												pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td><span class="badge label-success">${boardVo.viewcnt}</span></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<!-- //게시판 테이블 -->
	
	<!-- pagination -->
	<div class="row">
		<div class="col-md-12 text-center" >
			<nav>
				<ul class="pagination">
					<c:if test="${pagingDto.hasPrev == true}">
						<li class="page-item">
							<a class="page-link" data-page="${pagingDto.startPage - 1}">Previous</a>
						</li>
					</c:if>
					
					<c:forEach begin="${pagingDto.startPage}" end="${pagingDto.endPage}" var="v">
							<!-- 현재 페이지와 v가 같으면 class="page-item active" -->
							<!-- 현재 페이지와 v가 다르면 class="page-item" -->
						<li 
							<c:choose>
							
								<c:when test="${pagingDto.page == v}">
									class="page-item active"
								</c:when>
								
								<c:otherwise>
									class="page-item"
								</c:otherwise>
							</c:choose>
						>
							<a class="page-link" data-page="${v}">${v}</a>
						</li>
					</c:forEach>
					
					<c:if test="${pagingDto.hasNext == true}">
						<li class="page-item">
							<a class="page-link" data-page="${pagingDto.endPage + 1}">Next</a>
						</li>
					</c:if>
				</ul>
			</nav>
		</div>
	</div>
	<!-- // pagination -->
</div>

<%@ include file="../include/footer.jsp" %>