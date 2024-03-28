package bitcamp.myapp.service;

import bitcamp.myapp.vo.Member;
import java.util.List;

public interface MemberService {

  void add(Member member);

  List<Member> list();

  Member get(int no);

  Member get(String email, String password);

  int update(Member member);

  int delete(int no);
}
