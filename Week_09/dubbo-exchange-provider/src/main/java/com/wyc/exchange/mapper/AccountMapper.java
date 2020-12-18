package com.wyc.exchange.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wyc.exchange.dto.Account;

/**
 * @author yuchen.wu
 * @date 2020-12-16
 */

public interface AccountMapper {

    /**
     * pay for money
     * @param account account
     */
    @Update("update `account` set dollar = dollar + #{dollar}, rmb = rmb + " +
                    "#{rmb} where dollar >= #{dollar} and rmb >= #{rmb} and id = #{id}")
    boolean payment(Account account);


    /**
     * query one
     * @return account
     */
    @Select("select * from account where id = #{id}")
    Account queryOne(int id);
}
