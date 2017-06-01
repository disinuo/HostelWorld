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
import org.hibernate.criterion.Order;
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
	public <T> List<T> getByHql(Class<T> c,String hql){
		Session session = getCurrentSession();
		return session.createQuery(hql).list();
	}
	@Override
	public <T> List<T> getByHql_paging(Class<T> c,String hql,int pageNum,int resultNum){
		Session session = getCurrentSession();
		Query query=session.createQuery(hql);
		query.setFirstResult(pageNum);
		query.setMaxResults(resultNum);
		return query.list();
	}
	//TODO 可能要新加【返回排序结果】的接口
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
	public ResultMessage saveNoId(Object entity){
		try {
			save(entity);
		}catch (Exception e){
			return ResultMessage.FAILURE;
		}
		return ResultMessage.SUCCESS;
	}
	@Override
	public ResultMessage saveOrUpdate(Object entity) {
		Session session=getNewSession();
		Transaction tr=session.beginTransaction();
		try {
			session.saveOrUpdate(entity);
		}catch (Exception e){
			e.printStackTrace();
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
			e.printStackTrace();
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
	public <T> List<T> getAllDESC(Class<T> c, String base) {
		Session session=getNewSession();
		Criteria criteria=session.createCriteria(c);
		criteria.addOrder(Order.desc(base));
		return criteria.list();
	}

	@Override
	public <T> List<T> getAllASC(Class<T> c, String base) {
		Session session=getNewSession();
		Criteria criteria=session.createCriteria(c);
		criteria.addOrder(Order.asc(base));
		return criteria.list();
	}


}
