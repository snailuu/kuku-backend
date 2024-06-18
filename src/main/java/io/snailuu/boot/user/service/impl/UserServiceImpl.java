package io.snailuu.boot.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.snailuu.boot.auth.util.AppLoginUtil;
import io.snailuu.boot.framework.exception.BusinessException;
import io.snailuu.boot.framework.page.OrderByItem;
import io.snailuu.boot.framework.page.OrderMapping;
import io.snailuu.boot.framework.page.Paging;
import io.snailuu.boot.user.dto.AppUserHeadDto;
import io.snailuu.boot.user.dto.AppUserNicknameDto;
import io.snailuu.boot.user.dto.UserDto;
import io.snailuu.boot.user.entity.User;
import io.snailuu.boot.user.mapper.UserMapper;
import io.snailuu.boot.user.query.UserQuery;
import io.snailuu.boot.user.service.UserService;
import io.snailuu.boot.user.vo.AppUserVo;
import io.snailuu.boot.user.vo.UserVo;
import io.snailuu.boot.util.PagingUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户信息 服务实现类
 *
 * @author snailuu
 * @since 2023-11-25
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByOpenid(String openid) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getOpenid, openid);
        return getOne(wrapper);
    }

    @Override
    public User getUserByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        return getOne(wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateUser(UserDto userDto) {
        Long id = userDto.getId();
        User user = getById(id);
        if (user == null) {
            throw new BusinessException("用户信息不存在");
        }
        BeanUtils.copyProperties(userDto, user);
        return updateById(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteUser(Long id) {
        return removeById(id);
    }

    @Override
    public UserVo getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public Paging<UserVo> getUserPage(UserQuery query) {
        OrderMapping orderMapping = new OrderMapping();
        orderMapping.put("createTime", "create_time");
        PagingUtil.handlePage(query, orderMapping, OrderByItem.desc("id"));
        List<UserVo> list = userMapper.getUserPage(query);
        Paging<UserVo> paging = new Paging<>(list);
        return paging;
    }

    @Override
    public AppUserVo getProfile() {
        Long userId = AppLoginUtil.getUserId();
        return userMapper.getAppUserById(userId);
    }

    @Override
    public boolean updateHead(AppUserHeadDto dto) {
        Long userId = AppLoginUtil.getUserId();
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(User::getHead, dto.getHead());
        wrapper.eq(User::getId, userId);
        return update(new User(), wrapper);
    }

    @Override
    public boolean updateNickname(AppUserNicknameDto dto) {
        Long userId = AppLoginUtil.getUserId();
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(User::getNickname, dto.getNickname());
        wrapper.eq(User::getId, userId);
        return update(new User(), wrapper);
    }

}
