package com.ee.y1.board.notice;

import org.apache.ibatis.annotations.Mapper;

import com.ee.y1.board.BoardMapper;

//@Repository는 추가해도 되고 안해도 되지만 @Mapper는 꼭 필요
@Mapper
public interface NoticeMapper extends BoardMapper {

}
