package bitcamp.myapp.controller.member;

import bitcamp.myapp.controller.RequestMapping;
import bitcamp.myapp.dao.MemberDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberListController {

  private MemberDao memberDao;

  public MemberListController(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @RequestMapping
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    request.setAttribute("list", memberDao.findAll());
    return "/member/list.jsp";
  }
}
