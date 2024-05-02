package bitcamp.myapp.security08;

import bitcamp.myapp.service.MemberService;
import bitcamp.myapp.vo.Member;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {

  private static final Log log = LogFactory.getLog(MyUserDetailsService.class);

  // DBMS에서 사용자 정보를 찾아주는 서비스 객체
  MemberService memberService;

  public MyUserDetailsService(MemberService memberService) {
    this.memberService = memberService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Member member = memberService.get(username);
    if (member == null) {
      throw new UsernameNotFoundException("해당 사용자가 존재하지 않습니다.");
    }

    log.debug(member);

    MemberUserDetails userDetails = new MemberUserDetails();
    userDetails.setNo(member.getNo());
    userDetails.setName(member.getName());
    userDetails.setEmail(member.getEmail());
    userDetails.setPassword(member.getPassword());
    userDetails.setPhoto(member.getPhoto());

    return userDetails;
  }
}
