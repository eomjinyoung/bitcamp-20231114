package bitcamp.myapp.service;

import bitcamp.myapp.vo.Assignment;
import java.util.List;

public interface AssignmentService {

  void add(Assignment assignment);

  List<Assignment> list(int pageNo, int pageSize);

  Assignment get(int no);

  int update(Assignment assignment);

  int delete(int no);

  int countAll();
}
