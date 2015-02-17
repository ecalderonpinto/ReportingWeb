/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.entities.dao;

import java.util.List;

import javax.swing.text.html.parser.Entity;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractDAO<T> {
	private Class<T> entityClass;

	public AbstractDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected abstract HibernateTemplate getHibernateTemplate();

	@Transactional
	public void create(T entity) {
		System.out.println("Creating: " + entity.toString());

		getHibernateTemplate().save(entity);
		// getHibernateTemplate().flush();
		System.out.println("Created");
	}

	@Transactional
	public void edit(T entity) {
		getHibernateTemplate().merge(entity);
	}

	@Transactional
	public void delete(T entity) {
		getHibernateTemplate().delete(getHibernateTemplate().merge(entity));
		
		//getHibernateTemplate().flush();
		System.out.println("Deleted");
	}

	// public T find(Object id) {
	// return getHibernateTemplate().find(entityClass, id);
	// }

	@Transactional
	public List<T> findByExample(T entity) {
		return (List<T>) getHibernateTemplate().findByExample(entity);
	}

//	@Transactional
//	public List<T> findAll() {
//		return (List<T>) getHibernateTemplate().findByCriteria(
//				DetachedCriteria.forClass(entityClass).add(
//						Example.create(entityClass)));
//	}
	
	@Transactional
	public List<T> findAll() {
		return (List<T>) getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(entityClass));
	}

	
	@Transactional
	public void deleteAll() {
		List<T> entities = findAll();
		for(T entity : entities) {
			delete(entity);
		}
	}
	

	// public List<T> findRange(int[] range) {
	// javax.persistence.criteria.CriteriaQuery cq =
	// getHibernateTemplate().getCriteriaBuilder().createQuery();
	// cq.select(cq.from(entityClass));
	// javax.persistence.Query q = getHibernateTemplate().createQuery(cq);
	// q.setMaxResults(range[1] - range[0]);
	// q.setFirstResult(range[0]);
	// return q.getResultList();
	// }
	//
	// public int count() {
	// javax.persistence.criteria.CriteriaQuery cq =
	// getHibernateTemplate().getCriteriaBuilder().createQuery();
	// javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
	// cq.select(getHibernateTemplate().getCriteriaBuilder().count(rt));
	// javax.persistence.Query q = getHibernateTemplate().createQuery(cq);
	// return ((Long) q.getSingleResult()).intValue();
	// }

}
