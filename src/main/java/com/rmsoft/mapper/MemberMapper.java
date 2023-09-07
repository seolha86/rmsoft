package com.rmsoft.mapper;

import com.rmsoft.domain.MemberVO;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : seolha86
 * @packageName : com.rmsoft.mapper
 * @fileName : MemberMapper
 * @date : 2023-09-01
 * @description :
 * ===========================================================
 * DATE           AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-01        seolha86             최초 생성
 */

@Mapper
public interface MemberMapper {
    List<MemberVO> retrieve();
    void register(MemberVO vo);
    MemberVO findById(String id);
}
