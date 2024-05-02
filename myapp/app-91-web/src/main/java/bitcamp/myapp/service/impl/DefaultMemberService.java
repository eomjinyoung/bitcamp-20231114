package bitcamp.myapp.service.impl;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.service.MemberService;
import bitcamp.myapp.vo.Member;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DefaultMemberService implements MemberService {

  private static final Log log = LogFactory.getLog(DefaultMemberService.class);
  private final MemberDao memberDao;

  @Override
  public void add(Member member) {
    memberDao.add(member);
  }

  @Override
  public List<Member> list(int pageNo, int pageSize) {
    log.debug(String.format("pageNo: %s", pageNo));
    log.debug(String.format("pageSize: %s", pageSize));
    return memberDao.findAll(pageSize * (pageNo - 1), pageSize);
  }

  @Override
  public Member get(int no) {
    return memberDao.findBy(no);
  }

  @Override
  public Member get(String email, String password) {
    return memberDao.findByEmailAndPassword(email, password);
  }

  @Override
  public Member get(String email) {
    return memberDao.findByEmail(email);
  }

  @Override
  public int update(Member member) {
    return memberDao.update(member);
  }

  @Override
  public int delete(int no) {
    return memberDao.delete(no);
  }

  @Override
  public int countAll() {
    return memberDao.countAll();
  }
}
