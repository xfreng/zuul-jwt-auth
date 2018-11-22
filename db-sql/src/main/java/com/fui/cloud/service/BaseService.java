package com.fui.cloud.service;

import com.fui.cloud.dao.BaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseService<T, P> {

    protected BaseMapper<T, P> baseMapper;

    public int deleteByPrimaryKey(P id) {
        return baseMapper.deleteByPrimaryKey(id);
    }

    public int insert(T record) {
        return baseMapper.insert(record);
    }

    public int insertSelective(T record) {
        return baseMapper.insertSelective(record);
    }

    public T selectByPrimaryKey(P id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(T record) {
        return baseMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(T record) {
        return baseMapper.updateByPrimaryKey(record);
    }
}
