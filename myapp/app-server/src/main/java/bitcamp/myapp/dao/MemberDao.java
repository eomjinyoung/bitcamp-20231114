package bitcamp.myapp.dao;

import bitcamp.myapp.vo.Member;
import java.util.List;

public interface MemberDao {

  public void add(Member member);

  public int delete(int no);

  public List<Member> findAll();

  public Member findBy(int no);

  public int update(Member member);

  public Member findByEmailAndPassword(String email, String password);
}
