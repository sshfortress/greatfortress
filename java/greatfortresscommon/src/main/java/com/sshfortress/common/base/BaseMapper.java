package com.sshfortress.common.base;

import java.util.List;
import java.util.Map;

public interface BaseMapper<K, T> {
	/*根据参数查询单表的分页 */
	public List<T> queryByParamsListPager(Map<String,Object> map);
	/*根据参数查询单表的全部 */
	public List<T> list(T paramT);
	/*根据主键删除单表的数据 */
	int deleteByPrimaryKey(K paramLong);
	/*添加表数据*/
	int insert(T t);
	int insertSelective(T t);
	T selectByPrimaryKey(K k);
	/*更新数据通过主键*/
	int updateByPrimaryKeySelective(T t);
	int updateByPrimaryKey(T t);
}
