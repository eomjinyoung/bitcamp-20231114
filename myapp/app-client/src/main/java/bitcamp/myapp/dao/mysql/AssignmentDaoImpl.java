package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.vo.Assignment;
import java.sql.Connection;
import java.util.List;

public class AssignmentDaoImpl implements AssignmentDao {

  Connection con;

  public AssignmentDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public void add(Assignment assignment) {

  }

  @Override
  public int delete(int no) {
    return 0;
  }

  @Override
  public List<Assignment> findAll() {
    return null;
  }

  @Override
  public Assignment findBy(int no) {
    return null;
  }

  @Override
  public int update(Assignment assignment) {
    return 0;
  }
}
