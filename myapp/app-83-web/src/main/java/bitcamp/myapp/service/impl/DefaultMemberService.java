package bitcamp.myapp.service.impl;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.service.MemberService;
import bitcamp.myapp.vo.Member;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DefaultMemberService implements MemberService {

  private final MemberDao memberDao;

  @Override
  public void add(Member member) {
    memberDao.add(member);
  }

  @Override
  public List<Member> list() {
    return memberDao.findAll();
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
  public int update(Member member) {
    return memberDao.update(member);
  }

  @Override
  public int delete(int no) {
    return memberDao.delete(no);
  }
}
