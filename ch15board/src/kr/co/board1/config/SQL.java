package kr.co.board1.config;

public class SQL {
	public static final String SELECT_LOGIN = "SELECT * FROM `JSP_MEMBER` WHERE uid=? AND pass=PASSWORD(?)";
	public static final String INSERT_BOARD = "INSERT INTO `JSP_BOARD` SET "
											+ "cate='notice', "
											+ "title=?, "
											+ "content=?, "
											+ "uid=?, "
											+ "regip=?, "
											+ "rdate=NOW()";
	public static final String SELECT_LIST	= "SELECT b.*, m.nick FROM JSP_BOARD AS b JOIN JSP_MEMBER AS m ON b.uid = m.uid ORDER BY b.seq DESC";
}
