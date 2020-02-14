<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>

<style>
#uploadedList > div{
	float: left;
	margin: 20px;
}
</style>

<script src="/resources/js/myscript.js"></script>

<script>
$(document).ready(function () {
	
	// 게시글 수정 버튼 
	$("#btnModity").click(function() { 
// 		console.log("btnModity");
// 		location.href = "/board/modify";
		
	// 버튼을 클릭했을때 readonly해제 하도록
		$("#title").prop("readonly", false);
		$("#content").prop("readonly", false);
// 		$("#writer").prop("readonly", false);
		$(this).hide(600);
		$("button[type=submit]").show(600);
		
		// 첨부파일 수정
		$(".pull-right").fadeIn(1000);	// 첨부파일 삭제링크(x) 나타내기
		
		
		
	});
	
	// 게시글 삭제 버튼
	$("#btnDelete").click(function() {
// 		console.log("btnDelete");
// 		location.href = "/board/delete?bno=${boardVo.bno}";
		$("#frmList").attr("action", "/board/delete");
		$("#frmList").submit();
		
		
	});
	
	// 목록으로 돌아가기
	$("#btnListAll").click(function() { 
// 		console.log("btnListAll");
// 		location.href = "/board/listAll?page=${pagingDto.page}&perPage=${pagingDto.perPage}";
		$("#frmList").submit();
		
	});
	
	
	
	// 댓글 작성 완료 버튼
	$("#btnReply").click(function() {
		var bno = "${boardVo.bno}";					// 게시글 번호(댓글 번호 아님)
		var reply_text = $("#reply_text").val();	// 댓글내용
		var replyer = $("#replyer").val();			// 작성자

		// 보낼 데이터
		var sendData = {
				"bno"			: bno,
				"reply_text"	: reply_text,
				"replyer"		: replyer
		}; 
		
		var url = "/replies/register";
// 		$.post(url, JSON.stringify(sendData), function(rData) {
// 			console.log(rData);
// 		});

		$.ajax({
			"type" 		: "post",
			"url"	 	: url,
			"headers"	: {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "post"
			},
			"dataType"	: "text",
			"data"		: JSON.stringify(sendData),	// 보낼 데이터 스트링으로 읽기
			"success"	: function(rData) {			// 성공했을때 실행할 함수
				console.log(rData);
				replyList();
			}
		});
		
	});
	
	// 댓글 수정 버튼
	$("#replyList").on("click", ".btnReplyUpdate", function() {
		console.log("댓글 수정 버튼");
		
		var rno = $(this).attr("data-rno");
		var reply_text = $(this).attr("data-reply_text");
		var replyer = $(this).attr("data-replyer");
		
		$("#modal_rno").val(rno);
		$("#modal_reply_text").val(reply_text);
		$("#modal_replyer").val(replyer); 
		
		$("#modal-a").trigger("click");
		
	});
	
	
	// 댓글 삭제 버튼
	$("#replyList").on("click", ".btnReplyDelete", function() {
		console.log("댓글 삭제 버튼");
		
		var rno = $(this).attr("data-rno");
		var bno = $(this).attr("data-bno");
		
		var url = "/replies/delete/" + rno + "/" + bno;
		
		$.ajax({
			"type" 		: "delete",
			"url"	 	: url,
			"headers"	: {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "delete"
			},
			"success"	: function(rData) {			// 성공했을때 실행할 함수
				console.log(rData);
				replyList();
				$("#btnModalClose").trigger("click");
			}
		});
		
	});
	
	
	// 모달 창 완료 버튼
	$("#btnModalReply").click(function() {
		var rno = $("#modal_rno").val();
		var reply_text = $("#modal_reply_text").val();
		var replyer = $("#modal_replyer").val();
		
		//  보낼 데이터
		var sendData = {
				"rno"		 : rno,
				"reply_text" : reply_text,
				"replyer"	 : replyer
		};
		
		var url = "/replies/update";
		
		$.ajax({
			"type" 		: "put",
			"url"	 	: url,
			"headers"	: {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "put"
			},
			"dataType"	: "text",
			"data"		: JSON.stringify(sendData),	// 보낼 데이터 스트링으로 읽기
			"success"	: function(rData) {			// 성공했을때 실행할 함수
				console.log(rData);
				replyList();
				$("#btnModalClose").trigger("click");
			}
		});
		
	});
	
	
	// 댓글 목록 가져오기 - 정의
	function replyList() {
		$("#replyList").empty(); // 비우기
		
		var url = "/replies/all/${boardVo.bno}";
		$.getJSON(url, function(rData) {
			console.log(rData);
			
			var strHtml = "";
			
			// 받은 데이터 정리하기 (tbody안에 들어갈거)
			$(rData).each(function() {
				strHtml += "<tr>";
				
				strHtml += "<td>" + this.rno + "</td>";
				strHtml += "<td>" + this.reply_text + "</td>";
				strHtml += "<td>" + this.replyer + "</td>";
				strHtml += "<td>" + dateString(this.reg_date) + "</td>";	// 날짜 얻기
				
				// 댓글 수정
				strHtml += "<td><button type='button' class='btn-xs btn-warning btnReplyUpdate'";
				strHtml += "data-rno='" 		+ this.rno 			+ "'";
				strHtml += "data-reply_text='" 	+ this.reply_text 	+ "'";
				strHtml += "data-replyer='"	 	+ this.replyer 		+ "'>수정</button></td>";
				
				// 댓글 삭제
				strHtml += "<td><button type='button' class='btn-xs btn-danger btnReplyDelete'";
				strHtml += "data-rno='" + this.rno 	+ "'";
				strHtml += "data-bno='" + this.bno 	+ "'>삭제</button></td>";
				
				strHtml += "</tr>";
			});
			
			// <tbody>의 자식 엘리먼트로 html을 추가
			$("#replyList").append(strHtml);  
		});
	}

	// 첨부파일
	function getAttachList() {
		var url = "/board/getAttach/${boardVo.bno}"
		$.getJSON(url, function (list) {
			console.log("list: ", list);
			$(list).each(function() {
				var full_name = this;
				var underScoreIndex = full_name.indexOf("_");
				var file_name = full_name.substring(underScoreIndex + 1);
				var el = $("#attach_template").clone();
				el.removeAttr("id");
				el.find("span:first").text(file_name);
				
				var imgEl = el.find("img");
				if (checkImage(file_name) == true ){
					// 썸네일 이미지 얻기
					var thumbnailName = getThumbnailName(full_name); // myscript.js
					imgEl.attr("src", "/upload/displayFile?fileName=" + thumbnailName);
					
				} else	{	
					// 이미지가 아니라면
					imgEl.attr("src", "/resources/images/file_image.png");
				}
				
				el.attr("data-filename", full_name);
				el.find("a").attr("href", full_name);
				
				el.css("display", "block");
				$("#uploadedList").append(el);
			});
		});
	}

	// 첨부 파일 삭제 링크
	$("#uploadedList").on("click", ".attach-del", function (e) {
		var that = $(this);
		e.preventDefault();
		var fullName = that.attr("href");
		console.log("fullName: " + fullName);
		var url = "/upload/deleteFileAndData";
		var sendData = {"fileName" : fullName};
		$.get(url, sendData, function (rData) {
			console.log(rData);
			if (rData == "success"){
				that.parent().remove();
			}
		});
	}); // $("#uploadedList").on 첨부 파일 삭제 링크
	
	
	
	getAttachList();
	replyList(); // 기능 실행
});

</script>
${pagingDto}


<div class="container-fluid">

	<!-- 댓글 수정 모달 창 -->
	
	<div class="row">
		<div class="col-md-12">
			<a id="modal-a" href="#modal-container" role="button" class="btn" data-toggle="modal"
				style="display: none;">Launch demo modal</a>
			
			<!-- 실제 모달 창 안에 들어가는 내용 -->
			<div class="modal fade" id="modal-container" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
					
						<div class="modal-header">
							<h5 class="modal-title" id="myModalLabel">
								댓글 수정하기
							</h5> 
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">×</span>
							</button>
						</div>
						
						<div class="modal-body">
							<input type="hidden" id="modal_rno"/>
						
							<label for="modal_reply_text">댓글 내용</label>
							<input type="text" class="form-control"
									id="modal_reply_text"/>
							
							<label for="modal_replyer">작성자</label>
							<input type="text" class="form-control"
									 id="modal_replyer"/>
						</div>
						
						<div class="modal-footer">
							<button type="button" class="btn btn-primary" 
								id="btnModalReply">
								수정 완료
							</button> 
							<button type="button" class="btn btn-secondary" data-dismiss="modal"
								id="btnModalClose">
								닫기
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- //댓글 수정 모달 창 -->

	<div class="row">
		<div class="col-md-12">
			<!-- 검색어 유지 -->
			<form id="frmList" action="/board/listAll" method="get">
				<input type="hidden" name="bno" 		value="${boardVo.bno}"/>
				<input type="hidden" name="page" 		value="${pagingDto.page}"/>
				<input type="hidden" name="perPage" 	value="${pagingDto.perPage}"/>
				<input type="hidden" name="searchType" 	value="${pagingDto.searchType}" />
				<input type="hidden" name="keyword" 	value="${pagingDto.keyword}" />
			</form>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<!-- action을 생략하면 현재 경로 : /board/register -->
			<form id="myform" role="form" method="post" action="/board/modify">
				<input type="hidden" name="bno" 	value="${boardVo.bno}"/>
				<input type="hidden" name="page" 	value="${pagingDto.page}"/>
				<input type="hidden" name="perPage" value="${pagingDto.perPage}"/>
				
				<div class="form-group">
					<label for="title">Title</label>
					<input type="text" class="form-control" id="title" name="title" 
							value="${boardVo.title}" readonly/>
				</div>
				
				<div class="form-group">
					<label for="content">Content</label><br/>
					<textarea rows="5" cols="80" id="content" name=content readonly>${boardVo.content}</textarea>
				</div>
				
				<div class="form-group">
					<label for="writer">Writer</label>
					<input type="text" class="form-control" id="writer" 
							name="writer" value="${boardVo.writer}"
							readonly/>
				</div>
				
				
				<!-- 첨부 파일 목록 템플릿 : clone해서 사용 -->
				<div id="attach_template" 
					 style = "display: none;"
					 data-filename="">
					<img class="img-thumbnail">
					<br>
					<span></span>
					<a href="#" class="attach-del">
						<span class="pull-right" style="display: none;">x</span>
					</a>
				</div>
				
				
				<div  class="form-group">
					<label for="uploadedList">첨부파일</label>
					<div id="uploadedList">
						
					</div>
				</div>
				
				<div style="clear: both;">
					<button type="submit" class="btn btn-success" style="display: none;">Submit</button>
					
					<button type="button" class="btn btn-warning"
						id="btnModity">Modify 수정하기</button>
						
					<button type="button" class="btn btn-danger"
						id="btnDelete">Delete 삭제하기</button>
						
					<button type="button" class="btn btn-primary"
						id="btnListAll">ListAll 목록으로</button>
				</div>
			</form>
		</div>
	</div>
	
	<hr/>
	
	<!-- 댓글 작성 -->
	<div class="row">
		<div class="col-md-12">
			<div class="form-group">
				<label for="reply_text">댓글 내용</label>
				<input type="text" class="form-control" id="reply_text" />			
			</div>
			<div class="form-group">
				<label for="reply_text">작성자</label>
				<input type="text" class="form-control" id="replyer" />			
			</div>
			<div class="form-group">
				<button type="button" class="btn btn-success btn-sm" id="btnReply">작성 완료</button>
			</div>
		</div>
	</div>
	<!-- // 댓글 작성 -->
	
	<hr/>
	
	<!-- 댓글 목록 -->
	<div class="row">
		<div class="col-md-12">
			<table class="table">
				<thead>
					<tr>
						<th>번호</th>
						<th>댓글 내용</th>
						<th>작성자</th>
						<th>날짜</th>
						<th>수정</th>
						<th>삭제</th>
					</tr>
				</thead>
				<tbody id="replyList">
					
				</tbody>
			</table>
		</div>
	</div>
	<!-- //댓글 목록 -->
	
	
</div>



<%@ include file="../include/footer.jsp" %>