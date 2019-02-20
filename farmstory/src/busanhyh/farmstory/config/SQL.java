package busanhyh.farmstory.config;

public class SQL {
	public static final String SELECT_LOGIN = "SELECT * FROM `JSP_MEMBER` WHERE uid=? AND pass=PASSWORD(?)";
	public static final String INSERT_BOARD = "INSERT INTO `JSP_BOARD` SET "
											+ "cate='notice', "
											+ "title=?, "
											+ "content=?, "
											+ "file=?, "
											+ "uid=?, "
											+ "regip=?, "
											+ "rdate=NOW()";
	public static final String INSERT_FILE	= "INSERT INTO `JSP_FILE` "
											+ "(parent, oldName, newName, rdate) "
											+ "VALUES (?,?,?,NOW())";
	public static final String SELECT_MAXS	= "SELECT MAX(seq) FROM `JSP_BOARD`";
	public static final String SELECT_UID_COUNT = "SELECT COUNT(*) FROM `JSP_MEMBER` WHERE uid=?";
	public static final String SELECT_NICK_COUNT = "SELECT COUNT(*) FROM `JSP_MEMBER` WHERE nick=?";
	public static final String SELECT_EMAIL_COUNT = "SELECT COUNT(*) FROM `JSP_MEMBER` WHERE email=?";
	public static final String SELECT_HP_COUNT = "SELECT COUNT(*) FROM `JSP_MEMBER` WHERE hp=?";
	public static final String SELECT_LIST	= "SELECT b.*, m.nick FROM JSP_BOARD "
											+ "AS b JOIN JSP_MEMBER AS m ON b.uid = m.uid "
											+ "WHERE parent=0 AND cate=? ORDER BY b.seq DESC "
											+ "LIMIT ?,10";
	public static final String SELECT_VIEW	= "SELECT * FROM `JSP_BOARD` WHERE seq=?";
	public static final String SELECT_VIEW_WIHT_FILE = "SELECT * FROM `JSP_BOARD` AS B "
											+ "LEFT JOIN `JSP_FILE` AS F ON B.seq = F.parent "
											+ "WHERE B.seq=?";
	public static final String SELECT_TOTAL	= "SELECT COUNT(*) FROM `JSP_BOARD` WHERE parent=0 AND cate=?";
	public static final String UPDATE_HIT	= "UPDATE `JSP_BOARD` SET hit=hit+1 WHERE seq=?";
	public static final String UPDATE_DOWN_HIT = "UPDATE `JSP_FILE` SET download=download+1 WHERE parent=?";
	public static final String DELETE_HIT	= "UPDATE `JSP_BOARD` SET comment=comment-1 WHERE seq=?";
	public static final String DELETE_POST	= "DELETE FROM `JSP_BOARD` WHERE seq=?";
	public static final String UPDATE_POST	= "UPDATE `JSP_BOARD` SET title=?, content=? WHERE seq=?";
	public static final String INSERT_COMM	= "INSERT INTO `JSP_BOARD` SET "
											+ "parent=?, "
											+ "content=?, "
											+ "uid=?, "
											+ "regip=?, "
											+ "rdate=NOW()";
	//public static final String UPDATE_COMM	= ""; 
	public static final String CALL_COMM	= "CALL insertComment(?,?,?,?)";
	public static final String SELECT_COMM	= "SELECT b.*, m.nick FROM JSP_BOARD "
											+ "AS b JOIN JSP_MEMBER AS m ON b.uid = m.uid "
											+ "WHERE parent=? "
											+ "ORDER BY b.seq ASC";
}
