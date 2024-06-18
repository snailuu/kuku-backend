package io.snailuu.boot.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.snailuu.boot.framework.page.Paging;
import io.snailuu.boot.user.dto.AppUserHeadDto;
import io.snailuu.boot.user.dto.AppUserNicknameDto;
import io.snailuu.boot.user.dto.UserDto;
import io.snailuu.boot.user.entity.User;
import io.snailuu.boot.user.query.UserQuery;
import io.snailuu.boot.user.vo.AppUserVo;
import io.snailuu.boot.user.vo.UserVo;


/**
 * 用户信息 服务接口
 *
 * @author snailuu
 * @since 2023-11-25
 */
public interface UserService extends IService<User> {

    /**
     * 根据微信openid获取用户
     *
     * @param openid
     * @return
     * @throws Exception
     */
    User getUserByOpenid(String openid);

    /**
     * 根据账号获取用户
     *
     * @param username
     * @return
     * @throws Exception
     */
    User getUserByUsername(String username);

    /**
     * 添加用户信息
     *
     * @param userDto
     * @return
     * @throws Exception
     */
    boolean addUser(UserDto userDto);

    /**
     * 修改用户信息
     *
     * @param userDto
     * @return
     * @throws Exception
     */
    boolean updateUser(UserDto userDto);

    /**
     * 删除用户信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteUser(Long id);

    /**
     * 用户信息详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    UserVo getUserById(Long id);

    /**
     * 用户信息分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Paging<UserVo> getUserPage(UserQuery query);

    /**
     * 获取App用户信息
     *
     * @return
     * @throws Exception
     */
    AppUserVo getProfile();

    /**
     * 修改用户头像
     *
     * @param dto
     * @return
     * @throws Exception
     */
    boolean updateHead(AppUserHeadDto dto);

    /**
     * 修改用户昵称
     *
     * @param dto
     * @return
     * @throws Exception
     */
    boolean updateNickname(AppUserNicknameDto dto);

}
