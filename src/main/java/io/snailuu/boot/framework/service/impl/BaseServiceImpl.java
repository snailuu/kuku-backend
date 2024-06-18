package io.snailuu.boot.framework.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.snailuu.boot.framework.service.BaseService;

/**
 * @author snailuu
 * @date 2022/3/16
 **/
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {

}
