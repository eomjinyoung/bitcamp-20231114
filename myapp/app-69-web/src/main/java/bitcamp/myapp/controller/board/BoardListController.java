package bitcamp.myapp.controller.board;

import bitcamp.myapp.controller.RequestMapping;
import bitcamp.myapp.dao.BoardDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardListController {

  private BoardDao boardDao;

  public BoardListController(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @RequestMapping
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int category = Integer.valueOf(request.getParameter("category"));
    request.setAttribute("boardName", category == 1 ? "게시글" : "가입인사");
    request.setAttribute("list", boardDao.findAll(category));
    request.setAttribute("category", category);
    return "/board/list.jsp";
  }
}
