package com.plushih.services.front;

import java.util.List;

import com.plushih.common.ci.plusActiveRecord;
import com.plushih.entities.AigoCommentaryEntity;

public interface AigoCommentaryService {
	
	Integer setCommentaryExcute(plusActiveRecord dbEntity, AigoCommentaryEntity aigoCommentaryEntity) throws Exception;
	List<AigoCommentaryEntity> getCommentaryList(plusActiveRecord dbEntity, AigoCommentaryEntity aigoCommentaryEntity) throws Exception;

}