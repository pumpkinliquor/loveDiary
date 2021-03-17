package com.plushih.daos;

import com.plushih.common.ci.plusActiveRecord;
import com.plushih.entities.FieldIndexEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface CiDAO {

    /**
     * 공통된 리스트를 가져온다
     * @param dbEntity
     * @return
     */
    List<Map<String,Object>> getList(plusActiveRecord dbEntity);
    /**
     * 공통된 리스트를 가져온다
     * @param dbEntity
     * @return
     */
    List<Map<String,Object>> getList_bySubQuery(plusActiveRecord dbEntity);
    /**
     * 공통된 rowData를 가져온다
     * @param dbEntity
     * @return
     */
    Map<String,Object> getRow(plusActiveRecord dbEntity);

    /**
     * 카운트를 가져온다.
     * @return
     */
    Integer getCount(plusActiveRecord dbEntity);

    /**
     * pk넘버 시퀀스넘버 같은 고유번호를 가져온다.
     * @return
     */
    Integer getSeq(plusActiveRecord dbEntity);


    /**
     * 테이블에 컬럼들을 가져온다
     * @param dbEntity
     * @return
     */
    List<Map<String,Object>> get_full_field(plusActiveRecord dbEntity);

    /**
     * 테이블목록을 가져온다
     * @param dbEntity
     * @return
     */
    List<Map<String,Object>> get_tables(plusActiveRecord dbEntity);
    /**
     * 테이블의 인덱스를 가져온다
     * @param dbEntity
     * @return
     */
    List<FieldIndexEntity> get_index(plusActiveRecord dbEntity);
    /**
     * 테이블의 프라이머리키를 가져온다
     * @param dbEntity
     * @return
     */
    FieldIndexEntity get_primary(plusActiveRecord dbEntity);
    /**
     * 테이블의 유니크 인덱스를 가져온다
     * @param dbEntity
     * @return
     */
    List<FieldIndexEntity> get_uniqe(plusActiveRecord dbEntity);

    /**
     * 수정
     * @return
     */
    Integer query(plusActiveRecord dbEntity);

    /**
     * 입력
     * @return
     */
    Integer insert(plusActiveRecord dbEntity);
    /**
     * 수정
     * @return
     */
    Integer update(plusActiveRecord dbEntity);
    /**
     * 삭제
     * @return
     */
    Integer delete(plusActiveRecord dbEntity);
    /**
     * 입력
     * @return
     */
    Integer batch(plusActiveRecord dbEntity);
    /**
     * 히스토리
     * @return
     */
    Integer history(plusActiveRecord dbEntity);
}