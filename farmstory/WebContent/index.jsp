<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
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
              <c:forEach var="vo" items="${ latest1 }">
              <tr>
                <td>></td>
                <td><a href="/farmstory/board/view.do?gr=croptalk&cate=farm&seq=${ vo.seq }">${ vo.title }</a></td>
                <td>${ vo.rdate }</td>
              </tr>
              </c:forEach>
            </table>
          </div>
          <div>
            <img src="./img/main_latest2_tit.png" alt="">
            <img src="./img/main_latest2_img.jpg" alt="">
            <table border="0">
              <c:forEach var="vo" items="${ latest2 }">
              <tr>
                <td>></td>
                <td><a href="/farmstory/board/view.do?gr=croptalk&cate=school&seq=${ vo.seq }">${ vo.title }</a></td>
                <td>${ vo.rdate }</td>
              </tr>
              </c:forEach>
            </table>
          </div>
          <div>
            <img src="./img/main_latest3_tit.png" alt="">
            <img src="./img/main_latest3_img.jpg" alt="">
            <table border="0">
              <c:forEach var="vo" items="${ latest3 }">
              <tr>
                <td>></td>
                <td><a href="/farmstory/board/view.do?gr=croptalk&cate=story&seq=${ vo.seq }">${ vo.title }</a></td>
                <td>${ vo.rdate }</td>
              </tr>
              </c:forEach>
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

<%@ include file="./_footer.jsp" %>   