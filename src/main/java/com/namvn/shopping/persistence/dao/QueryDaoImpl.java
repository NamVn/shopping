package com.namvn.shopping.persistence.dao;

import com.namvn.shopping.persistence.entity.Queries;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class QueryDaoImpl implements QueryDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void addQuery(Queries queries) {
        Session session = sessionFactory.openSession();
        session.save(queries);
        session.close();
    }
}
