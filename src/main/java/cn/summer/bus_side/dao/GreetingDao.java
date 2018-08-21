package cn.summer.bus_side.dao;

import cn.summer.bus_side.domain.Greeting;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface GreetingDao {
    String TABLE_NAME = "Deal";
    String INSERT_FIELDS = " bus_id,trd_dir,msg_type,req_branch,req_user,cp_branch,cp_user,cd_type,drft_nm," +
            "sum_amt," + "quoteTime,set_time,set_amt,cd_no,cd_amt,due_dt,pay_int,set_one_amt";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{bus_id},#{trd_dir},#{msg_type},#{req_branch},#{req_user},#{cp_branch},#{cp_user}," +
                    "#{cd_type},#{drft_nm},#{sum_amt},#{quoteTime},#{set_time},#{set_amt},#{cd_no}," +
                    "#{cd_amt},#{due_dt},#{pay_int},#{set_one_amt})"})
    int addDeal(Greeting greeting);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where bus_id=#{bus_id}"})
    Greeting selectByBusId(String bus_id);

//    @Update({"update ", TABLE_NAME, " set status=#{status} where ticket=#{ticket}"})
//    void updateStatus(@Param("ticket") String ticket, @Param("status") int status);
//
//    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
//    LoginTicket selectById(int id);
}




