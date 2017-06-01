package nju.edu.hostel.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import nju.edu.hostel.util.ResultMessage;
import org.hibernate.Session;

public interface BaseDao {

	public int save(Object entity) throws Exception;
	public ResultMessage saveNoId(Object entity);

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
	public <T> List<T> getByHql(Class<T> c,String hql);

	/**
	 * 分页查询
	 * @param c
	 * @param hql
	 * @param pageNum 第几页
	 * @param resultNum 一页显示的最大行数
	 * @param <T>
	 * @return
	 */
	public <T> List<T> getByHql_paging(Class<T> c,String hql,int pageNum,int resultNum);



}
