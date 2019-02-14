<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>팜스토리</title>
    <link rel="stylesheet" type="text/css" href="./css/style.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="./js/slider.js"></script>
    <script>
      /*
      $(function(){}); //dom이 생성되자마자 실행
      window.onload = function(){}; //리소스도 호출되고 실행
      */
    </script>
  </head>
  <body>
    <div id="wrapper">
      <header>
        <a href="./index.do" class="logo"><img src="./img/logo.png" /></a>
        <p>
          <a href="./index.do">HOME | </a>
          <a href="#">로그인 | </a>
          <a href="#">회원가입 | </a>
          <a href="#">고객센터</a>
        </p>
        <img src="./img/head_txt_img.png" alt="3만원 이상 무료배송" />
        <ul class="gnb">
          <li><a href="./introduction/hello.do">팜스토리소개</a></li>
          <li><a href="./market/market.do"><img src="./img/head_menu_badge.png" alt="">장보기</a></li>
          <li><a href="./croptalk/story.do">농작물이야기</a></li>
          <li><a href="./event/event.do">이벤트</a></li>
          <li><a href="./community/notice.do">커뮤니티</a></li>
        </ul>
      </header>
      <main>
        <div class="slider">
          <ul>
            <li><img src="./img/main_slide_img1.jpg" alt="슬라이드1" /></li>
            <li><img src="./img/main_slide_img2.jpg" alt="슬라이드2" /></li>
            <li><img src="./img/main_slide_img3.jpg" alt="슬라이드3" /></li>
          </ul>
          <img src="./img/main_slide_img_tit.png" alt="사람과 자연을 사랑하는 팜스토리" />
          <div class="grandOpen">
            <img src="./img/main_banner_txt.png" alt="">
            <img src="./img/main_banner_tit.png" alt="">
            <img src="./img/main_banner_img.png" alt="">
          </div>
        </div>
        <div class="banner">
          <a href="#"><img src="./img/main_banner_sub1_tit.png" alt=""></a>
          <a href="#"><img src="./img/main_banner_sub2_tit.png" alt=""></a>
        </div>
        <div class="latest">
          <div>
            <img src="./img/main_latest1_tit.png" alt="">
            <img src="./img/main_latest1_img.jpg" alt="">
            <table border="0">
              <tr>
                <td>></td>
                <td>토마토! 건강하게 길러서 안심하고 먹자</td>
                <td>02-12</td>
              </tr>
              <tr>
                <td>></td>
                <td>토마토! 건강하게 길러서 안심하고 먹자</td>
                <td>02-12</td>
              </tr>
              <tr>
                <td>></td>
                <td>토마토! 건강하게 길러서 안심하고 먹자</td>
                <td>02-12</td>
              </tr>
              <tr>
                <td>></td>
                <td>토마토! 건강하게 길러서 안심하고 먹자</td>
                <td>02-12</td>
              </tr>
              <tr>
                <td>></td>
                <td>토마토! 건강하게 길러서 안심하고 먹자</td>
                <td>02-12</td>
              </tr>
            </table>
          </div>
          <div>
            <img src="./img/main_latest2_tit.png" alt="">
            <img src="./img/main_latest2_img.jpg" alt="">
            <table border="0">
              <tr>
                <td>></td>
                <td>토마토! 건강하게 길러서 안심하고 먹자</td>
                <td>02-12</td>
              </tr>
              <tr>
                <td>></td>
                <td>토마토! 건강하게 길러서 안심하고 먹자</td>
                <td>02-12</td>
              </tr>
              <tr>
                <td>></td>
                <td>토마토! 건강하게 길러서 안심하고 먹자</td>
                <td>02-12</td>
              </tr>
              <tr>
                <td>></td>
                <td>토마토! 건강하게 길러서 안심하고 먹자</td>
                <td>02-12</td>
              </tr>
              <tr>
                <td>></td>
                <td>토마토! 건강하게 길러서 안심하고 먹자</td>
                <td>02-12</td>
              </tr>
            </table>
          </div>
          <div>
            <img src="./img/main_latest3_tit.png" alt="">
            <img src="./img/main_latest3_img.jpg" alt="">
            <table border="0">
              <tr>
                <td>></td>
                <td>토마토! 건강하게 길러서 안심하고 먹자</td>
                <td>02-12</td>
              </tr>
              <tr>
                <td>></td>
                <td>토마토! 건강하게 길러서 안심하고 먹자</td>
                <td>02-12</td>
              </tr>
              <tr>
                <td>></td>
                <td>토마토! 건강하게 길러서 안심하고 먹자</td>
                <td>02-12</td>
              </tr>
              <tr>
                <td>></td>
                <td>토마토! 건강하게 길러서 안심하고 먹자</td>
                <td>02-12</td>
              </tr>
              <tr>
                <td>></td>
                <td>토마토! 건강하게 길러서 안심하고 먹자</td>
                <td>02-12</td>
              </tr>
            </table>
          </div>
        </div>
        <div class="info">
          <div>
            <img src="./img/main_sub2_cs_tit.png" alt="">
            <img src="./img/main_sub2_cs_img.png" alt="">
            <img src="./img/main_sub2_cs_txt.png" alt="">
            <span>평일: AM 09:00 ~ PM 06:00<br>
            점심: PM 12:00 ~ PM 01:00<br>
            토, 일요일, 공휴일 휴무</span>
            <div>
              <img src="./img/main_sub2_cs_bt1.png" alt="">
              <img src="./img/main_sub2_cs_bt2.png" alt="">
              <img src="./img/main_sub2_cs_bt3.png" alt="">
            </div>
          </div>
          <div>
            <img src="./img/main_sub2_account_tit.png" alt="">
            <span>기업은행 123-456789-01-01-012<br>
            국민은행 01-1234-56789<br>
            우리은행 123-456789-01-01-012<br>
            하나은행 123-456789-01-01<br>
            예 금 주 (주)팜스토리</span>
          </div>
          <div>

          </div>
        </div>
      </main>
      <footer>
        <hr>
        <img src="./img/footer_logo.png" alt="" />
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
