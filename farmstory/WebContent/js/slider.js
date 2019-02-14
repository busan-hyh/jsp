$(function(){
  var li = $('.slider > ul > li');
  var i = 0;

  setInterval(function(){
    li.eq(i).animate({'left':'-100%'}, 1000);
    i++;
    if(i==3){i=0;}
    li.eq(i).css('left','100%').animate({'left':'0'}, 1000);
  }, 1000*3); //주기함수, 반복되는 함수
})
