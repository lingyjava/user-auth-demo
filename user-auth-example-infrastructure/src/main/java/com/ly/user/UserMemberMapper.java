package com.ly.user;

import com.ly.domain.user.UserMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
 * userMember(接口定义)
 *
 * @author Ly
 * @date 2023/6/9 15:51.
 */
@Mapper
public interface UserMemberMapper{
    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    UserMember queryById(Long userId);
    /**
     * 分页查询指定行数据
     *
     * @param userMember 查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<UserMember> queryAllByLimit(UserMember userMember, @Param("pageable") Pageable pageable);
    /**
     * 统计总行数
     *
     * @param userMember 查询条件
     * @return 总行数
     */
    long count(UserMember userMember);
    /**
     * 新增数据
     *
     * @param userMember 实例对象
     * @return 影响行数
     */
    int insert(UserMember userMember);
    /**
     * 批量新增数据
     *
     * @param entities List<UserMember> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<UserMember> entities);
    /**
     * 批量新增或按主键更新数据
     *
     * @param entities List<UserMember> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<UserMember> entities);
    /**
     * 更新数据
     *
     * @param userMember 实例对象
     * @return 影响行数
     */
    int update(UserMember userMember);
    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 影响行数
     */
    int deleteById(Long userId);
}