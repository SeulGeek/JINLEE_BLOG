<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>JINLEE</title>
<!-- Custom fonts for this template-->
<link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<!-- Custom styles for this template-->
<link href="../css/sb-admin-2.min.css" rel="stylesheet">
<link href="../css/toggle-switch.css" rel="stylesheet">
<!-- 네이버 스마트 에디터 -->
<script type="text/javascript" src="../SE2.3/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="../SE2.3/photo_uploader/plugin/hp_SE2M_AttachQuickPhoto.js" charset="utf-8"></script>
<!-- Bootstrap core JavaScript-->
<script src="../vendor/jquery/jquery.min.js"></script>
<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="../js/toggle-switch.js"></script>

<!-- 스마트 에디터 -->
<script type="text/javascript">
  var oEditors = [];
  $(function() {
    nhn.husky.EZCreator.createInIFrame({
      oAppRef : oEditors,
      elPlaceHolder : "postContent", //textarea에서 지정한 id와 일치해야 합니다. 
      //SmartEditor2Skin.html 파일이 존재하는 경로
      sSkinURI : "../SE2.3/SmartEditor2Skin.html",
      htParams : {
        // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
        bUseToolbar : true,
        // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
        bUseVerticalResizer : true,
        // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
        bUseModeChanger : true,
        fOnBeforeUnload : function() {
        }
      },
      fOnAppLoad : function() {
        //기존 저장된 내용의 text 내용을 에디터상에 뿌려주고자 할때 사용
        oEditors.getById["postContent"].exec("PASTE_HTML", [""]);
      },
      fCreator : "createSEditor2"
    });
    //저장버튼 클릭시 form 전송
    $("#button").click(function() {
      oEditors.getById["postContent"].exec("UPDATE_CONTENTS_FIELD", []);
      $("#content").submit();
    });
  });
  
  // textArea에 이미지 첨부
  function pasteHTML(filepath){
      var sHTML = '<img src="<%=request.getContextPath()%>/upload' + filepath
				+ '">';
		console.log("textArea에 이미지 첨부_sHTML:" + sHTML);
		oEditors.getById["postContent"].exec("PASTE_HTML", [ sHTML ]);
	}
</script>
</head>


<body id="page-top">

	<!-- Page Wrapper 시작 -->
	<div id="wrapper">

		<jsp:include page="adminMenu.jsp"></jsp:include>
		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<jsp:include page="adminHeader.jsp"></jsp:include>

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">게시판 관리>게시글 등록</h6>
						</div>
						<div class="card-body">
							<form id="content" class="form-horizontal" action="adminRegister.do" method="post" enctype="multipart/form-data">
								<input type="hidden" name="no" value="${param.no}">
								<table class="table table" id="dataTable" width="100%" cellspacing="0">
									<tbody>
										<tr>
											<td width="10%">게시판 명</td>
											<td>
												<select id="boardId" name="boardId">
													<c:forEach var="board" items="${boardList}">
														<option value="${board.boardId}">${board.boardName}</option>
													</c:forEach>
												</select>
											</td>
										</tr>
										<tr>
											<td>카테고리</td>
											<td>

												<select id="cateId" name="cateId">
												  <option value="">선택</option>
													<c:forEach var="boardCate" items="${boardCate[2].cateList}">
															<option value="${boardCate.cateId}">${boardCate.cateName}</option>
													</c:forEach>
												</select>
											</td>
										</tr>
										<tr>
											<td width="10%">제목</td>
											<td>
												<input name="postTitle" id="postTitle" value="">
											</td>

										</tr>
										<tr>
											<td>내용</td>
											<td>
												<textarea class="form-control" rows="5" id="postContent" name="postContent"></textarea>
											</td>
										</tr>
										<tr>
											<td>태그</td>
											<td>
												<input name="postTag" id="postTag" value="">
											</td>
										</tr>
										<tr>
											<td>공개여부</td>
											<td>
												<label class="switch"> <input type="checkbox" id="postVisibility" name="postVisibility"> <span class="slider round"> </span>
												</label>
											</td>
										</tr>
										<tr>
											<td>대표이미지</td>
											<td>
												<input type="file" onchange="previewFile()" accept="image/gif, image/jpeg, image/png" id="postImage" name="postImage" value="">
												<!-- 프로필 사진 미리보기 -->
												<br> <br> <img id="preview" src="" onerror="this.style.display='none'" style="width: 100px; height: 100px;" />
											</td>
										</tr>

									</tbody>
								</table>
								<input type="hidden" name="cateName" value="mysql"><input type="hidden" name="boardName" value="개발노트">

								<div class="col-sm-offset-2 col-sm-10">
									<input id="button" type="submit" class="btn btn-warning" onclick="submitContents(this)" value="등록">
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 페이지를 로드될 때, 자바스크립트로 게시판과 카테고리 리스트를 json형식으로 만든다 -->
	<!-- 	<script type="text/javascript">
	 
	</script>
	
	<!-- 선택된 게시판의 id,name 세팅하기 -->

	<script type="text/javascript">

  <c:forEach var="boardCate" items="${boardCate}">
  var cateId ${boardCate.boardId} = new Array("선택"<c:forEach var="cateList" items="${boardCate.cateList}">, "${cateList.cateId}"</c:forEach>);
  var cateName ${boardCate.boardId} = new Array("선택"<c:forEach var="cateList" items="${boardCate.cateList}">, "${cateList.cateName}"</c:forEach>);
  </c:forEach>

function catechange(item){
    var temp, i=0, j=0;
    var ccount, cselect;

    temp =document.getElementById("cateId");

    for (i=(temp.options.length-1) ; i>0 ; i--){ temp.options[i] = null; }
    eval('count = cateId' + item + '.length');
    for (j=0 ; j<count ; j++) {
    	// eval : 문자열을 코드로 인식하게 한다.
        eval('selectId = cateId' + item + '[' + j + '];');
        eval('selectName = cateName' + item + '[' + j + '];');
        temp.options[j]= new Option(selectName,selectId); 
    }
    temp.options[0].selected=true;
    return true;
}
</script>
	<!-- 선택된 카테고리의 name 세팅하기 -->
	<script type="text/javascript">
    $("#cateId").on("change keyup paste", function() {
      var target = document.getElementById("cateId");
      var cateName = target.options[target.selectedIndex].text;
      var cateId = target.options[target.selectedIndex].value;
       $('input[name=cateName]').attr('value', cateName);
     /*  var target2 = $('input[name=boardName]').val(); */
     /* alert("name: "+cateName+", id : "+cateId); */
    });
  </script>

	<script type="text/javascript">
    $("#boardId").on("change keyup paste", function() {
      var target = document.getElementById("boardId");
      /* var cateName = $("#cateId option:selected").text(); */
      var boardName = target.options[target.selectedIndex].text;
      $('input[name=boardName]').attr('value', boardName);
      /* var target2 = $('input[name=boardName]').val(); */
      
      /*  alert("!!"+target.options[target.selectedIndex].value+"!!");  */
      catechange(target.options[target.selectedIndex].value);
      
      
      
    });
  </script>

	<!-- 프로필 사진 미리보기 -->
	<script type="text/javascript">
		function previewFile() {
			var preview = document.querySelector('img');
			var file = document.querySelector('input[type=file]').files[0];
			var reader = new FileReader();
			reader.addEventListener("load", function() {
				preview.src = reader.result;
			}, false);
			if (file) {
				reader.readAsDataURL(file);
			}
			preview.style.display = '';
		}
	</script>

	<!-- 토글 스위치(공개 여부) -->
	<script src="../js/toggle-switch.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="../vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="../js/sb-admin-2.min.js"></script>

	<!-- Page level plugins -->
	<script src="../vendor/datatables/jquery.dataTables.min.js"></script>
	<script src="../vendor/datatables/dataTables.bootstrap4.min.js"></script>

	<!-- Page level custom scripts -->
	<script src="../js/demo/datatables-demo.js"></script>
</body>

</html>