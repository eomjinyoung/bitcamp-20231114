package bitcamp.myapp.dao;

import bitcamp.myapp.vo.Assignment;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AssignmentDao {

  void add(Assignment assignment);

  int delete(int no);

  List<Assignment> findAll(
      @Param("offset") int offset,
      @Param("rowCount") int rowCount);

  Assignment findBy(int no);

  int update(Assignment assignment);

  int countAll();
}
