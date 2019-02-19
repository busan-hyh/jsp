<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<section id="sub">
<div>
  <img src="../img/sub_top_tit5.png" alt="COMMUNITY" />
</div>
<div class="community">
  <aside>
    <img src="../img/sub_aside_bg_line.png" alt="line" />
    <img src="../img/sub_aside_cate5_tit.png" alt="커뮤니티" />
    <ul>
      <li class="${ cate=='notice'?'on':'' }"><a href="/farmstory/board/list.do?gr=community&cate=notice">공지사항</a></li>
      <li class="${ cate=='today'?'on':'' }"><a href="/farmstory/board/list.do?gr=community&cate=today">오늘의식단</a></li>
      <li class="${ cate=='cook'?'on':'' }"><a href="/farmstory/board/list.do?gr=community&cate=cook">나도요리사</a></li>
      <li class="${ cate=='onetoone'?'on':'' }"><a href="/farmstory/board/list.do?gr=community&cate=onetoone">1:1고객문의</a></li>
      <li class="${ cate=='qna'?'on':'' }"><a href="/farmstory/board/list.do?gr=community&cate=qna">자주묻는질문</a></li>
    </ul>
  </aside>
  <article>
    <nav>
      <img src="../img/sub_nav_tit_cate5_tit_${ cate }.png" alt="커뮤니티" />

      <p>
        <img src="../img/sub_page_nav_ico.gif" alt="::"> HOME > 커뮤니티 > <strong>나도요리사</strong>
      </p>
    </nav>
      <!-- 내용시작 -->