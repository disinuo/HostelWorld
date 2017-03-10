package nju.edu.hostel.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import nju.edu.hostel.util.ResultMessage;
import org.hibernate.Session;

public interface BaseDao {

	public int save(Object entity) throws Exception;
//	public ResultMessage save(List<Object> entitys);
	public ResultMessage saveOrUpdate(Object entity);

	public ResultMessage update(Object entity);

	public <T> T getEntity(Class<T> c,int id);

	public <T> T loadProxy(Class<T> c,int id);

	public <T> List<T> getAll(Class<T> c);

	public <T> List<T> getByRestrictEqual(Class<T> c,String column,Object value);

	/**
	 * 表的列名与对应的值构成的map
	 * @param c
	 * @param map
	 * @param <T>
	 * @return
	 */
	public <T> List<T> getByRestrictEqual(Class<T> c,Map<String,Object> map);





/*******

public Session getSession();

	public Session getNewSession();

	public void flush();

	public void clear();

	public Object load(Class<?> c, String id);

	public ResultMessage save(Object bean);

	public int saveAndGetId(Object bean);

	public ResultMessage delete(Class<?> c, int id);

	public ResultMessage delete(Object bean);

	public ResultMessage delete(Class<?> c, String[] ids);

	public ResultMessage merge(Object bean);

	public ResultMessage update(Object bean);

	public <T> List<T> getAll(Class<T> c);

	public <T> List<T> getAllByDesc(Class<T> c, String column);

	public Long getTotalCount(Class<?> c);

	/**
	 * 按列值查
	 * @return
	 */
/******
	public <T> List<T> findByColunms(Class<T> c, String[] columns, Object[] values);

	public <T> List<T> findByColunmsDesc(Class<T> c, String[] columns, Object[] values, String descColumn);

	public <T> List<T> findByColunmsHasNull(Class<T> c, String[] columns, Object[] values, String[] nullColumn);

	public <T> T get(Class<T> c, int id);

	public <T> List<T> fuzzySearch(Class<T> c, String[] columns, String[] values);

	public <C, T> List<T> findOneColumn(Class<C> c, String column, T type);

	public <C, T> List<T> findOneColumnDesc(Class<C> c, String column, T type);

	public <T> List<T> findByTimestamp(Class<T> c, String column, Timestamp begin, Timestamp end);

	public List find(String queryString);



******/












//
//
//
//
//	public Object load(Class<?> c, String id);
//
//	public ResultMessage save(Object bean);
//
//	public int saveAndGetId(Object bean);
//
//	public ResultMessage delete(Class<?> c, int id);
//
//	public ResultMessage delete(Object bean);
//
//	public ResultMessage delete(Class<?> c, String[] ids);
//
//	public ResultMessage merge(Object bean);
//
//	public ResultMessage update(Object bean);
//
//	public <T> List<T> getAll(Class<T> c);
//
//	public <T> List<T> getAllByDesc(Class<T> c, String column);
//
//	public Long getTotalCount(Class<?> c);
//
//	/**
//	 * 按列值查
//	 * @return
//	 */
//	public <T> List<T> findByColunms(Class<T> c, String[] columns, Object[] values);
//
//	public <T> List<T> findByColunmsDesc(Class<T> c, String[] columns, Object[] values, String descColumn);
//
//	public <T> List<T> findByColunmsHasNull(Class<T> c, String[] columns, Object[] values, String[] nullColumn);
//
//	public <T> T get(Class<T> c, int id);
//
//	public <T> List<T> fuzzySearch(Class<T> c, String[] columns, String[] values);
//
//	public <C, T> List<T> findOneColumn(Class<C> c, String column, T type);
//
//	public <C, T> List<T> findOneColumnDesc(Class<C> c, String column, T type);
//
//	public <T> List<T> findByTimestamp(Class<T> c, String column, Timestamp begin, Timestamp end);
//
//	public List find(String queryString);
}
