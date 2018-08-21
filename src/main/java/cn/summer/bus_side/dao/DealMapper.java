package cn.summer.bus_side.dao;

import cn.summer.bus_side.domain.Deal;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DealMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Deal record);

    int insertSelective(Deal record);

    Deal selectByPrimaryKey(Integer id);

    List<Deal>  selectByBusId(String bus_id);

    int updateByPrimaryKeySelective(Deal record);

    int updateByPrimaryKey(Deal record);

    List<Deal> selectByStatus(Byte status);

    void updateStatus(String bus_id);



}