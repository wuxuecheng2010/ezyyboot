package com.enze.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.enze.entity.TmsTrDoc;


@Repository
@Mapper
public interface TmsTrDocMapperAnno {
	String TABLE_NAME = "Tms_tr_doc";
    String SELECT_FIELDS = "trid,warehid,credate ,operationtype";

   
    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where trid = #{trid}"})
    TmsTrDoc selectTmsTrDocByTrid(int  trid);

}
