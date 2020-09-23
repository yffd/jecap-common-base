package com.yffd.jecap.common.base.service;

import com.yffd.jecap.common.base.entity.IBaseEntity;
import com.yffd.jecap.common.base.result.RtnResult;

import java.io.Serializable;
import java.util.Set;

public interface IBaseAppService<E extends IBaseEntity> {

    /** 添加 */
    RtnResult<E> add(E entity);
    /** 修改 */
    RtnResult<E> updateById(E entity);
    /** 删除 */
    RtnResult<E> deleteById(Serializable id);
    /** 删除-批量 */
    RtnResult<E> deleteByIds(Set<Serializable> ids);
    /** 查询-单个 */
    RtnResult<E> findById(Serializable id);
    /** 查询-分页 */
    RtnResult<E> findPage(E entity, int pageNum, int pageSize);

}
