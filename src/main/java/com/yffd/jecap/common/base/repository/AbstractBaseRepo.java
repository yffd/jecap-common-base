package com.yffd.jecap.common.base.repository;

import com.yffd.jecap.common.base.dao.IBaseDao;
import com.yffd.jecap.common.base.entity.IBaseEntity;
import com.yffd.jecap.common.base.page.PageData;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public abstract class AbstractBaseRepo<E extends IBaseEntity> implements IBaseRepository<E> {

    protected abstract IBaseDao<E> getDao();

    @Override
    public int add(E entity) {
        if (null == entity) return 0;
        return this.getDao().addBy(entity);
    }

    @Override
    public int modify(E oldEntity, E newEntity) {
        if (null == oldEntity || null == newEntity) return 0;
        return this.getDao().modifyBy(oldEntity, newEntity);
    }

    @Override
    public int modifyById(E entity) {
        if (null == entity) return 0;
        return this.getDao().modifyById(entity);
    }

    @Override
    public int remove(E entity) {
        if (null == entity) return 0;
        return this.getDao().removeBy(entity);
    }

    @Override
    public int removeById(Serializable id) {
        if (null == id) return 0;
        return this.getDao().removeById(id);
    }

    @Override
    public int removeByIds(Set<? extends Serializable> ids) {
        if (null == ids || ids.size() == 0) return 0;
        return this.getDao().removeByIds(ids);
    }

    @Override
    public E get(E entity) {
        if (null == entity) return null;
        return this.getDao().findOne(entity);
    }

    @Override
    public E getById(Serializable id) {
        if (null == id) return null;
        return this.getDao().findById(id);
    }

    @Override
    public List<E> getByIds(Set<? extends Serializable> ids) {
        if (null == ids || ids.size() == 0) return Collections.emptyList();
        return this.getDao().findByIds(ids);
    }

    @Override
    public List<E> getList(E entity) {
        if (null == entity) return Collections.emptyList();
        return this.getDao().findList(entity);
    }

    @Override
    public List<E> getAll() {
        return this.getDao().findAll();
    }

    @Override
    public PageData<E> getPage(E entity, int pageNum, int pageSize) {
        return this.getDao().findPage(entity, pageNum, pageSize);
    }

}
