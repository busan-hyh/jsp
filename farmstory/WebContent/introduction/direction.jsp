<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>팜스토리 소개</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css" />
  </head>
  <body>
    <div id="wrapper">
      <header>
        <a href="../index.do" class="logo"><img src="../img/logo.png" /></a>
        <p>
          <a href="../index.do">HOME | </a>
          <a href="#">로그인 | </a>
          <a href="#">회원가입 | </a>
          <a href="#">고객센터</a>
        </p>
        <img src="../img/head_txt_img.png" alt="3만원 이상 무료배송" />
        <ul class="gnb">
          <li><a href="../introduction/hello.do">팜스토리소개</a></li>
          <li><a href="../market/market.do"><img src="../img/head_menu_badge.png" alt="">장보기</a></li>
          <li><a href="../croptalk/story.do">농작물이야기</a></li>
          <li><a href="../event/event.do">이벤트</a></li>
          <li><a href="../community/notice.do">커뮤니티</a></li>
        </ul>
      </header>
      <section id="sub">
        <div>
          <img src="../img/sub_top_tit1.png" alt="INTRODUCTION" />
        </div>
        <div class="introduction">
          <aside>
            <img src="../img/sub_aside_bg_line.png" alt="line" />
            <img src="../img/sub_aside_cate1_tit.png" alt="팜스토리 소개" />
            <ul>
              <li><a href="./hello.do">인사말</a></li>
              <li class="on"><a href="./direction.do">찾아오시는길</a></li>
            </ul>
          </aside>
          <article>
            <nav>
              <img src="../img/sub_nav_tit_cate1_tit2.png" alt="인사말" />

              <p>
                <img src="../img/sub_page_nav_ico.gif" alt="::"> HOME > 팜스토리소개 > <strong>찾아오시는길</strong>
              </p>
            </nav>
              <!-- 내용시작 -->
              <p><strong>▣ 팜스토리</strong><br>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;주소: 경기도 이천시 잘한다구 신난다동 123<br>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;전화: 01-234-5678</p><br><br>
              <p><strong>▣ 찾아오시는길</strong></p><br>
              <!-- * Daum 지도 - 지도퍼가기 -->
              <!-- 1. 지도 노드 -->
              <div id="daumRoughmapContainer1550110133645" class="root_daum_roughmap root_daum_roughmap_landing"></div>

              <!--
              	2. 설치 스크립트
              	* 지도 퍼가기 서비스를 2개 이상 넣을 경우, 설치 스크립트는 하나만 삽입합니다.
              -->
              <script charset="UTF-8" class="daum_roughmap_loader_script" src="http://dmaps.daum.net/map_js_init/roughmapLoader.js"></script>

              <!-- 3. 실행 스크립트 -->
              <script charset="UTF-8">
              	new daum.roughmap.Lander({
              		"timestamp" : "1550110133645",
              		"key" : "s7mg",
              		"mapWidth" : "760",
              		"mapHeight" : "400"
              	}).render();
              </script>
              <!-- 내용끝 -->
          </article>
        </div>
      </section>
      <footer>
        <hr>
        <img src="../img/footer_logo.png" alt="" />
        <div class="footer_txt">
          (주)팜스토리 / 사업자등록번호 123-45-67890 / 통신판매업신고 제 2013-팜스토리구-123호 / 벤처기업확인 서울지방중소기업청 제 012345678-9-01234호 <br>
          등록번호 팜스토리01234 (2013.04.01) / 발행인 : 홍길동 <br>
          대표 : 홍길동 / 이메일 : email@mail.mail / 전화 : 01) 234-5678 / 경기도 성남시 잘한다구 신난다동 345 <br>
          <span>Copyright<copyright> (C)홍길동 </copyright>All rights reserved.</span>
        </div>
      </footer>
    </div>
  </body>
</html>
