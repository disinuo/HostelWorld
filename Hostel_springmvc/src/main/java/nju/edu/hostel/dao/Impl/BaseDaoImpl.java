package nju.edu.hostel.dao.Impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import nju.edu.hostel.dao.BaseDao;
import nju.edu.hostel.util.ResultMessage;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDaoImpl implements BaseDao {

	@Autowired
	protected SessionFactory sessionFactory;
	private Session getNewSession(){

		return sessionFactory.openSession();
	}
	private Session getCurrentSession(){
		Session session=sessionFactory.getCurrentSession();

		return session==null?getNewSession():session;
	}

	@Override
	public int save(Object entity) throws Exception {
		Session session=getNewSession();
		Transaction tr=session.beginTransaction();
		try {
			int id=(Integer) session.save(entity);
			return id;
		}catch (Exception e){
			throw e;
		}finally {
			tr.commit();
			session.clear();
			session.close();
		}
	}

	@Override
	public ResultMessage saveOrUpdate(Object entity) {
		Session session=getNewSession();
		Transaction tr=session.beginTransaction();
		try {
			session.saveOrUpdate(entity);
		}catch (Exception e){
			return ResultMessage.FAILURE;
		}finally {
			tr.commit();
			session.clear();
			session.close();
		}
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage update(Object entity) {
		Session session=getNewSession();
		Transaction tr=session.beginTransaction();
		try {
			session.merge(entity);
		}catch (Exception e){
			return ResultMessage.FAILURE;
		}finally {
			tr.commit();
			session.clear();
			session.close();
		}
		return ResultMessage.SUCCESS;
	}

	@Override
	public <T> T getEntity(Class<T> c, int id) {

		Session session = sessionFactory.getCurrentSession();
		if (session != null) {
			session.clear(); // internal cache clear
		}

		Cache cache = sessionFactory.getCache();

		if (cache != null) {
			cache.evictAllRegions(); // Evict data from all query regions.
			cache.evictCollectionRegions();
			cache.evictDefaultQueryRegion();
			cache.evictEntityRegions();
			cache.evictQueryRegions();
			cache.evictNaturalIdRegions();
		}
//		Session session=getNewSession();

		T entity=session.get(c,id);
		return entity;
	}

	@Override
	public <T> T loadProxy(Class<T> c, int id) {
		Session session=getNewSession();
		T entity=session.load(c,id);
		return entity;
	}

	@Override
	public <T> List<T> getAll(Class<T> c) {
		Session session=getNewSession();
		Criteria criteria=session.createCriteria(c);
		List list=criteria.list();
		return list;
	}

	@Override
	public <T> List<T> getByRestrictEqual(Class<T> c, String column, Object value) {
		Session session=getNewSession();
		Criteria criteria=session.createCriteria(c);
		criteria.add(Restrictions.eq(column,value));
		return criteria.list();
	}

	@Override
	public <T> List<T> getByRestrictEqual(Class<T> c,Map<String,Object> map){
		Session session=getNewSession();
		Criteria criteria=session.createCriteria(c);
		for(Map.Entry<String,Object> entry:map.entrySet()){
			criteria.add(Restrictions.eq(entry.getKey(),entry.getValue()));
		}
		return criteria.list();
	}
/*********************
@Override
public Session getSession() {
	return sessionFactory.getCurrentSession();
}

	@Override
	public Session getNewSession() {
		return sessionFactory.openSession();
	}

	@Override
	public void flush() {
		getSession().flush();

	}

	@Override
	public void clear() {
		getSession().clear();
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Object load(Class<?> c, String id) {
		Session session = getSession();
		return session.get(c, id);
	}

	@Override
	public ResultMessage save(Object bean) {
		try {
			Session session = getNewSession();
			session.save(bean);
			session.flush();
			session.clear();
			session.close();
			return ResultMessage.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.FAILURE;
		}
	}



	@SuppressWarnings({ "rawtypes" })
	public ResultMessage delete(Class<?> c, int id) {
		try {
			Session session = getNewSession();
			Object obj = session.get(c, id);
			session.delete(obj);
			session.flush();
			session.clear();
			session.close();
			return ResultMessage.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.FAILURE;
		}
	}

	@Override
	public ResultMessage delete(Object bean) {
		try {
			Session session = getNewSession();
			session.delete(bean);
			session.flush();
			session.clear();
			session.close();
			return ResultMessage.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.FAILURE;
		}
	}

	@Override
	public ResultMessage delete(Class<?> c, String[] ids) {
		// TODO 可能要做回滚
		for (String id : ids) {
			Object obj = getSession().get(c, id);
			if (obj != null) {
				getSession().delete(obj);
			}
		}
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage update(Object bean) {
		try {
			Session session = getNewSession();
			session.update(session.merge(bean));
			session.flush();
			session.clear();
			session.close();
			return ResultMessage.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.FAILURE;
		}
	}

	public ResultMessage merge(Object bean){
		try {
			Session session = getNewSession();
			session.beginTransaction();
			session.merge(bean);
			session.getTransaction().commit();
			session.flush();
			session.clear();
			session.close();
			return ResultMessage.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.FAILURE;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getAll(Class<T> c) {
		String hql = "from " + c.getName();
		Session session = getSession();
		return session.createQuery(hql).list();
	}


	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getAllByDesc(Class<T> c, String column) {
		String hql = "from " + c.getName() + " order by " + column + " desc";
		Session session = getSession();
		return session.createQuery(hql).list();
	}

	@Override
	public Long getTotalCount(Class<?> c){
		Session session = getNewSession();
		String hql = "select count(*) from " + c.getName();
		Long count = (Long) session.createQuery(hql).uniqueResult();
		session.close();
		return count != null ? count.longValue() : 0;
	}

	@Override
	public <T> List<T> findByColunms(Class<T> c, String[] columns, Object[] values) {
		if (columns[0]==null || values[0]==null || columns.length != values.length) {
			return null;
		}
		Session session = getSession();
		Criteria criteria = session.createCriteria(c);
		Criterion ctns[] = new Criterion[columns.length];
		for (int i = 0; i < columns.length; i++) {
			ctns[i] = Restrictions.eq(columns[i], values[i]);
			criteria.add(ctns[i]);
		}

//		@SuppressWarnings("unchecked")
		List<T> list = criteria.list();
		if (list.size() == 0) {
			return null;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Class<T> c, int id) {
		Session session = getNewSession();
		return (T) session.get(c, id);
	}

	@Override
	public <T> List<T> fuzzySearch(Class<T> c, String[] columns, String[] values) {
		if (columns[0]==null || values[0]==null || columns.length != values.length) {
			return null;
		}
		Session session = getSession();
		Criteria criteria = session.createCriteria(c);
		Criterion ctns[] = new Criterion[columns.length];
		for (int i = 0; i < ctns.length; i++) {
			if (!values[i].equals("")) {
				ctns[i] = Restrictions.like(columns[i], values[i], MatchMode.ANYWHERE);
				criteria.add(ctns[i]);
			}
		}
		List<T> list = criteria.list();
		if (list.size() == 0) {
			return null;
		}
		return list;
	}

	@Override
	public int saveAndGetId(Object bean) {
		Session session = getNewSession();
		Object object = session.save(bean);
		Integer id = (Integer)object;
		session.flush();
		session.clear();
		session.close();
		return id;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <C, T> List<T> findOneColumnDesc(Class<C> c, String column, T type) {
		if (column == null ) {
			return null;
		}
		String hql = "select distinct " + column +" from " + c.getName() + " order by " + column + " desc";
		Session session = getSession();
		return session.createQuery(hql).list();

	}

	@SuppressWarnings("unchecked")
	@Override
	public <C, T> List<T> findOneColumn(Class<C> c, String column, T type) {
		if (column == null ) {
			return null;
		}
		String hql = "select distinct " + column +" from " + c.getName();
		Session session = getSession();
		return session.createQuery(hql).list();

	}

	@Override
	public <T> List<T> findByColunmsHasNull(Class<T> c, String[] columns, Object[] values, String[] nullColumn) {
		if (columns[0]==null || values[0]==null || columns.length != values.length || nullColumn.length == 0) {
			return null;
		}
		int colLength = columns.length;
		int nullLength = nullColumn.length;
		Session session = getSession();
		Criteria criteria = session.createCriteria(c);
		Criterion ctns[] = new Criterion[colLength + nullLength];
		for (int i = 0; i < colLength; i++) {
			if (!values[i].equals("")) {
				ctns[i] = Restrictions.eq(columns[i], values[i]);
				criteria.add(ctns[i]);
			}
		}
		for (int i = colLength; i < ctns.length; i++) {
			ctns[i] = Restrictions.isNull(nullColumn[i-colLength]);
			criteria.add(ctns[i]);
		}
		List<T> list = criteria.list();
		if (list.size() == 0) {
			return null;
		}
		return list;
	}

	@Override
	public <T> List<T> findByColunmsDesc(Class<T> c, String[] columns,
										 Object[] values, String descColumn) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findByTimestamp(Class<T> c, String column, Timestamp begin, Timestamp end) {
		if(column == null){
			return null;
		}
		Session session = getSession();
		String hql = "select *" + " from " + c.getName() + "where " + column + " >= " + begin + " and < " + end;
		return session.createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	public List find(String queryString) {
		Session session = getNewSession();
		return session.createQuery(queryString).list();

	}

******/
}
