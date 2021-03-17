package com.plushih.daos;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


/**
 * 공통 DAO
 */
@SuppressWarnings({"unchecked"})
public class CommonDao {
	
	@Autowired
	@Qualifier("sqlSession")
	private SqlSessionTemplate sqlSession;
	
	protected Logger log = LogManager.getLogger(this.getClass());
	
    /**
     * SELECT 쿼리를 실행하고 결과를 단건으로 반환한다.
     *
     * @param queryId 실행할 Query의 ID
     * @return 조회된 단건 데이터
     */
    public <T> T selectOne(String queryId){
       	return (T) sqlSession.selectOne(queryId);
    }
	
    /**
     * SELECT 쿼리를 실행하고 결과를 단건으로 반환한다.
     *
     * @param queryId 실행할 Query의 ID
     * @param parameter Input parameter 객체
     * @return 조회된 단건 데이터
     */
    public <T> T selectOne(String queryId, Object parameter){
       	return (T) sqlSession.selectOne(queryId, parameter);
    }
    
    
    /**
     * SELECT 쿼리를 실행하고 결과를 List로 반환한다.
     *
     * @param queryId 실행할 Query의 ID
     * @return 조회된 목록 데이터
     */
    public <E> List<E> selectList(String queryId) {      	        	
       	return (List<E>) sqlSession.selectList(queryId);
    }

    /**
     * SELECT 쿼리를 실행하고 결과를 List로 반환한다.
     *
     * @param queryId 실행할 Query의 ID
     * @param parameter Input parameter 객체
     * @param rowBounds 특정 범위의 데이터만 조회하기 위한 범위 설정 객체
     * @return 조회된 목록 데이터
     */
    public <E> List<E> selectList(String queryId, Object parameter) {
       	return (List<E>) sqlSession.selectList(queryId, parameter);
    }
    
    /**
     * INSERT 쿼리를 실행하고 처리 건수를 int 값으로 반환한다.
     *
     * @param queryId 실행할 Query의 ID
     * @return 처리 건수
     */
    public int insert(String queryId) {  	
        return sqlSession.insert(queryId);      
    }    

    /**
     * INSERT 쿼리를 실행하고 처리 건수를 int 값으로 반환한다.
     *
     * @param queryId 실행할 Query의 ID
     * @param parameter Input parameter 객체
     * @return 처리 건수
     */
    public int insert(String queryId, Object parameter) {
       	return sqlSession.insert(queryId, parameter);      
    }
    
    /**
     * UPDATE 쿼리를 실행하고 처리 건수를 int 값으로 반환한다.
     *
     * @param queryId 실행할 Query의 ID
     * @param parameter Input parameter 객체
     * @return 처리 건수
     */
    public int update(String queryId) {       	
       	 return sqlSession.update(queryId);
    }

    /**
     * UPDATE 쿼리를 실행하고 처리 건수를 int 값으로 반환한다.
     *
     * @param queryId 실행할 Query의 ID
     * @param parameter Input parameter 객체
     * @return 처리 건수
     */
    public int update(String queryId, Object parameter) {
       	 return sqlSession.update(queryId, parameter);
    }
    
    /**
     * DELETE 쿼리를 실행하고 처리 건수를 int 값으로 반환한다.
     *
     * @param queryId 실행할 Query의 ID
     * @return 처리 건수
     */
    public int delete(String queryId) {
       	return sqlSession.delete(queryId);
    }    
    
    /**
     * DELETE 쿼리를 실행하고 처리 건수를 int 값으로 반환한다.
     *
     * @param queryId 실행할 Query의 ID
     * @param parameter Input parameter 객체 
     * @return 처리 건수
     */
    public int delete(String queryId, Object parameter) {
       	return sqlSession.delete(queryId, parameter);
    }
    
    public void setSqlSession(SqlSessionTemplate sqlSession) {
  		this.sqlSession = sqlSession;
  	}

  	public SqlSessionTemplate getSqlSession() {
  		return sqlSession;
  	} 
}
