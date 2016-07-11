package dbService.dao;

import dbService.dataSets.UsersDataSet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


public class UsersDAO {

    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public UsersDataSet get(long id) throws HibernateException {
        return (UsersDataSet) session.get(UsersDataSet.class, id);
    }

    public long getUserId(String login, String password) throws HibernateException {
        Criteria criteria = session.createCriteria(UsersDataSet.class);
        criteria = criteria.add(Restrictions.eq("login", login));
        criteria = criteria.add(Restrictions.eq("password", password));
        return ((UsersDataSet) criteria.uniqueResult()).getId();
    }

    public long insertUser(String login, String password) throws HibernateException {
        return (Long) session.save(new UsersDataSet(login,password));
    }
}
