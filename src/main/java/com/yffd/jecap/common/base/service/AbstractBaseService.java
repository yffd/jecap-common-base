package com.yffd.jecap.common.base.service;

import com.yffd.jecap.common.base.entity.IBaseEntity;
import com.yffd.jecap.common.base.result.RtnResult;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Transactional
public abstract class AbstractBaseService<E extends IBaseEntity> implements IBaseService<E> {

    protected abstract IBaseDomainService getService();

    @Override
    public RtnResult<E> add(E entity) {
        if (null == entity) RtnResult.FAIL_PARAM_ISNULL();
        int num = this.getService().addBy(entity);
        return num > 0 ? RtnResult.OK() : RtnResult.FAIL_ADD();
    }

    @Override
    public RtnResult<E> updateById(E entity) {
        if (null == entity) RtnResult.FAIL_PARAM_ISNULL();
        int num = this.getService().editById(entity);
        return num > 0 ? RtnResult.OK() : RtnResult.FAIL_ADD();
    }

    @Override
    public RtnResult<E> deleteById(Serializable id) {
        if (null == id || "".equals(id.toString().trim())) RtnResult.FAIL_PARAM_ISNULL();
        int num = this.getService().removeById(id);
        return num > 0 ? RtnResult.OK() : RtnResult.FAIL_ADD();
    }

    @Override
    public RtnResult<E> deleteByIds(Set<Serializable> ids) {
        if (null == ids || ids.size() == 0) RtnResult.FAIL_PARAM_ISNULL();
        int num = this.getService().removeByIds(ids);
        return num > 0 ? RtnResult.OK() : RtnResult.FAIL_ADD();
    }

    @Override
    public RtnResult<E> getById(Serializable id) {
        if (null == id || "".equals(id.toString().trim())) RtnResult.FAIL_PARAM_ISNULL();
        IBaseEntity data = this.getService().getById(id);
        return RtnResult.OK(data);
    }

    @Override
    public RtnResult<E> getByIds(Set<? extends Serializable> ids) {
        if (null == ids || ids.size() == 0) RtnResult.FAIL_PARAM_ISNULL();
        List<E> data = this.getService().getByIds(ids);
        return RtnResult.OK(data);
    }

    @Override
    public RtnResult<E> getPage(E entity, int pageNum, int pageSize) {
        return this.getService().getPage(entity, pageNum, pageSize);
    }
}
