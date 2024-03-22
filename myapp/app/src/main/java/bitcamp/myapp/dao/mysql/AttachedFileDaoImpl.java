package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.vo.AttachedFile;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class AttachedFileDaoImpl implements AttachedFileDao {

  private final Log log = LogFactory.getLog(this.getClass());
  SqlSessionFactory sqlSessionFactory;

  public AttachedFileDaoImpl(SqlSessionFactory sqlSessionFactory) {
    log.debug("AttachedFileDaoImpl() 호출됨!");
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void add(AttachedFile file) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
      sqlSession.insert("AttachedFileDao.add", file);
    }
  }

  @Override
  public int addAll(List<AttachedFile> files) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
      return sqlSession.insert("AttachedFileDao.addAll", files);
    }
  }

  @Override
  public int delete(int no) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
      return sqlSession.delete("AttachedFileDao.delete", no);
    }
  }

  @Override
  public int deleteAll(int boardNo) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
      return sqlSession.delete("AttachedFileDao.deleteAll", boardNo);
    }
  }

  @Override
  public List<AttachedFile> findAllByBoardNo(int boardNo) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("AttachedFileDao.findAllByBoardNo", boardNo);
    }
  }

  @Override
  public AttachedFile findByNo(int no) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("AttachedFileDao.findByNo", no);
    }
  }
}
