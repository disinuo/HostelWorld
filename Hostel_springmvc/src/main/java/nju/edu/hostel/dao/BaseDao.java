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

	public <T> T getEntity(Class<T> c, int id);

	public <T> T loadProxy(Class<T> c, int id);

	public <T> List<T> getAll(Class<T> c);

	/**
	 *
	 * @param c
	 * @param base 是列名， 以此列为排序的基准
	 * @param <T>
	 * @return
	 */
	public <T> List<T> getAllDESC(Class<T> c,String base);
	public <T> List<T> getAllASC(Class<T> c,String base);

	public <T> List<T> getByRestrictEqual(Class<T> c, String column, Object value);
	public <T> List<T> getByRestrictEqualDESC(Class<T> c, String column, Object value,String base);
	public <T> List<T> getByRestrictEqualASC(Class<T> c, String column, Object value,String base);

	/**
	 * 表的列名与对应的值构成的map
	 *
	 * @param c
	 * @param map
	 * @param <T>
	 * @return
	 */
	public <T> List<T> getByRestrictEqual(Class<T> c, Map<String, Object> map);
	public <T> List<T> getByRestrictEqualDESC(Class<T> c, Map<String, Object> map,String base);
	public <T> List<T> getByRestrictEqualASC(Class<T> c, Map<String, Object> map,String base);

}
