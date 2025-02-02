package bitcamp.myapp.service.impl;

import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.service.AssignmentService;
import bitcamp.myapp.vo.Assignment;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DefaultAssignmentService implements AssignmentService {

  private AssignmentDao assignmentDao;

  public DefaultAssignmentService(AssignmentDao assignmentDao) {
    this.assignmentDao = assignmentDao;
  }

  @Override
  public void add(Assignment assignment) {
    assignmentDao.add(assignment);
  }

  @Override
  public List<Assignment> list() {
    return assignmentDao.findAll();
  }

  @Override
  public Assignment get(int no) {
    return assignmentDao.findBy(no);
  }

  @Override
  public int update(Assignment assignment) {
    return assignmentDao.update(assignment);
  }

  @Override
  public int delete(int no) {
    return assignmentDao.delete(no);
  }
}
