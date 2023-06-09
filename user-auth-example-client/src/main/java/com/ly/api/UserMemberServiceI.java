package com.ly.api;

/**
 * 用户管理API(接口定义)
 *
 * @author Ly
 * @date 2023/6/9 16:08.
 */
public interface UserMemberServiceI {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    UserMember queryById(Long userId);
    /**
     * 分页查询
     *
     * @param userMember 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<UserMember> paginQuery(UserMember userMember, PageRequest pageRequest);
    /**
     * 新增数据
     *
     * @param userMember 实例对象
     * @return 实例对象
     */
    UserMember insert(UserMember userMember);
    /**
     * 更新数据
     *
     * @param userMember 实例对象
     * @return 实例对象
     */
    UserMember update(UserMember userMember);
    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    boolean deleteById(Long userId);
}
