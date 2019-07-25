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
<script type="text/javascript" src="../nse_files/js/HuskyEZCreator.js" charset="utf-8"></script>
<!-- Bootstrap core JavaScript-->
<script src="../vendor/jquery/jquery.min.js"></script>
<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
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
              <form class="form-horizontal" action="adminRegister.do" method="post">
                <table class="table table" id="dataTable" width="100%" cellspacing="0">
                  <tbody>
                    <tr>
                      <td width="10%">카테고리</td>
                      <td>
                        <%--                        <select id="cateName" name="cateName">
                          <c:forEach var="cate" items="${cateList}">
                            <option <c:out value="${cate.cateName}" />></option>
                            <input type="hidden" id="cateId" name="cateId" value="${cate.cateId}">

                            <option value="<c:out value="${cate.cateName}" />">
                              <c:out value="${cate.cateName}" />
                            </option>
                            <input type="hidden" id="cateId" name="cateId" value="${cate.cateId}">
                          </c:forEach>
                        </select> --%>

                        <select id="cateId" name="cateId">
                          <c:forEach var="cate" items="${cateList}">
                            <option value="<c:out value="${cate.cateId}" />">${cate.cateName}</option>
                          </c:forEach>
                        </select>
                      </td>
                    </tr>
                    <tr>
                      <td width="10%">제목</td>
                      <td>
                        <input name="postTitle" id="postTitle">
                      </td>

                    </tr>
                    <tr>
                      <td>내용</td>
                      <td>
                        <textarea class="form-control" rows="5" id="postContent" name="postContent"></textarea>
                      </td>
                    </tr>
                    <tr>
                      <td>공개여부</td>
                      <td>
                        <label class="switch"> <input type="checkbox" id="postVisibility" name="postVisibility" value="false"> <span class="slider round"></span>
                        </label>
                        <p>OFF</p>
                        <p style="display: none;">ON</p>
                      </td>
                    </tr>
                    <tr>
                      <td>대표이미지</td>
                      <td>
                        <input type="file" id="postImage">
                      </td>
                    </tr>
                  </tbody>
                </table>
                <input type="hidden" name="boardId" value="${param.boardId}">
                <input type="hidden" name="boardName" value="개발 노트">
                <input type="hidden" name="cateName" value="1">

                <div class="col-sm-offset-2 col-sm-10">
                  <input type="submit" class="btn btn-warning" onclick="submitContents(this)" value="등록">
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- 네이버 스마트 에디터 -->
  <!-- 전송버튼 클릭이벤트 -->
  <script type="text/javascript">
    var oEditors = [];
    nhn.husky.EZCreator.createInIFrame({
      oAppRef : oEditors,
      elPlaceHolder : "postContent", //textarea에서 지정한 id와 일치해야 합니다. 
      sSkinURI : "../nse_files/SmartEditor2Skin.html", //SmartEditor2Skin.html 파일이 존재하는 경로
      fCreator : "createSEditor2"
    });
    function submitContents(elClickedObj) {
      // 에디터의 내용이 textarea에 적용됩니다.
      // id가 postContent인 textarea에 에디터에서 대입 
      oEditors.getById["postContent"].exec("UPDATE_CONTENTS_FIELD", []);
      // 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("postContent").value를 이용해서 처리하면 됩니다.
      try {
        elClickedObj.form.submit();
      } catch (e) {
      }
    }
  </script>

  <!-- 선택된 카테고리의 name 세팅하기 -->
  <script type="text/javascript">
    $("#cateId").on("change keyup paste", function() {
      var target = document.getElementById("cateId");
      /* var cateName = $("#cateId option:selected").text(); */
      var cateName = target.options[target.selectedIndex].text;
      $('input[name=cateName]').attr('value',cateName);
/*      var target2 = $('input[name=cateName]').val();
      alert(target2); */
    });
  </script>

  <!-- 토글 스위치 -->
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