package org.example.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class GenericDAO<T> {
    private final Class<T> clazz;
    private SessionFactory sessionFactory;

    public GenericDAO(Class<T> clazz, SessionFactory sessionFactory) {
        this.clazz = clazz;
        this.sessionFactory = sessionFactory;
    }

    public T getById(int id) {
        return (T) getCurrentSession().get(clazz, id);
    }

    public List<T> getItems(int offset, int limit) {
        Query<T> query = getCurrentSession().createQuery("from " + clazz.getName(), clazz);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + clazz.getName(), clazz).list();
    }

    public T save(T entity) {
        Session session = getCurrentSession();
        session.saveOrUpdate(entity);
        return entity;
    }

    public T update(T entity){
        Session session = getCurrentSession();
        entity = (T) session.merge(entity);
        return entity;
    }

    public void delete(T entity){
        Session session = getCurrentSession();
        session.remove(entity);
    }

    public Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }
}
