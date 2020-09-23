package com.yffd.jecap.common.base.service;

import com.yffd.jecap.common.base.entity.IBaseEntity;
import com.yffd.jecap.common.base.page.PageData;
import com.yffd.jecap.common.base.result.RtnResult;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Set;

@Transactional
public abstract class AbstractBaseAppService<E extends IBaseEntity> implements IBaseAppService<E> {

    protected abstract IBaseService getBaseService();

    @Override
    public RtnResult<E> add(E entity) {
        if (null == entity) RtnResult.FAIL_PARAM_ISNULL();
        int num = this.getBaseService().addBy(entity);
        return num > 0 ? RtnResult.OK() : RtnResult.FAIL_ADD();
    }

    @Override
    public RtnResult<E> updateById(E entity) {
        if (null == entity) RtnResult.FAIL_PARAM_ISNULL();
        int num = this.getBaseService().updateById(entity);
        return num > 0 ? RtnResult.OK() : RtnResult.FAIL_ADD();
    }

    @Override
    public RtnResult<E> deleteById(Serializable id) {
        if (null == id || "".equals(id.toString().trim())) RtnResult.FAIL_PARAM_ISNULL();
        int num = this.getBaseService().deleteById(id);
        return num > 0 ? RtnResult.OK() : RtnResult.FAIL_ADD();
    }

    @Override
    public RtnResult<E> deleteByIds(Set<Serializable> ids) {
        if (null == ids || ids.size() == 0) RtnResult.FAIL_PARAM_ISNULL();
        int num = this.getBaseService().deleteByIds(ids);
        return num > 0 ? RtnResult.OK() : RtnResult.FAIL_ADD();
    }

    @Override
    public RtnResult<E> findById(Serializable id) {
        if (null == id || "".equals(id.toString().trim())) RtnResult.FAIL_PARAM_ISNULL();
        IBaseEntity data = this.getBaseService().findById(id);
        return RtnResult.OK(data);
    }

    @Override
    public RtnResult<E> findPage(E entity, int pageNum, int pageSize) {
        PageData page = this.getBaseService().findPage(entity, pageNum, pageSize);
        return RtnResult.OK(page);
    }
}
