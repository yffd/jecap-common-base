package com.yffd.jecap.common.base.service;

import com.yffd.jecap.common.base.entity.IBaseEntity;
import com.yffd.jecap.common.base.page.PageData;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public interface IBaseService<E extends IBaseEntity> {

    /** 添加 */
    int addBy(E entity);
    /** 添加-批量 */
    int addBatch(List<E> entityList);

    /** 修改 */
    int updateBy(E oldEntity, E newEntity);
    /** 修改 */
    int updateById(E entity);

    /** 删除 */
    int deleteBy(E entity);
    /** 删除 */
    int deleteById(Serializable id);
    /** 删除-批量 */
    int deleteByIds(Set<Serializable> ids);

    /** 存在 */
    boolean existBy(E entity);
    /** 存在 */
    boolean existById(Serializable id);

    /** 查询-单个 */
    E findById(Serializable id);
    /** 查询-多个 */
    List<E> findByIds(Set<? extends Serializable> ids);
    /** 查询-单个 */
    E findBy(E entity);
    /** 查询-列表 */
    List<E> findList(E entity);
    /** 查询-列表 */
    List<E> findAll();
    /** 查询-分页 */
    PageData<E> findPage(E entity, int pageNum, int pageSize);

}
