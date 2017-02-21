package com.mkyong.stock.dao.impl;

import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.mkyong.stock.dao.StockDao;
import com.mkyong.stock.model.Stock;

@Transactional
public class StockDaoImpl extends HibernateDaoSupport implements StockDao{
	
	private TransactionTemplate transactionTemplate;

	  public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	  }
	  
	public void save(final Stock stock){
//		System.out.println("Richard is debuging Java SE with Hibernate:=========" + getHibernateTemplate().getSessionFactory());
//		// getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
//		getHibernateTemplate().save(stock);
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				// TODO Auto-generated method stub
				getHibernateTemplate().save(stock);
			}
		});
	}
	
	public void update(final Stock stock){
		// getHibernateTemplate().update(stock);
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				// TODO Auto-generated method stub
				getHibernateTemplate().update(stock);
			}
		});
	}
	
	public void delete(final Stock stock){
		// getHibernateTemplate().delete(stock);
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				// TODO Auto-generated method stub
				getHibernateTemplate().delete(stock);
			}
		});
	}
	
	public Stock findByStockCode(String stockCode){
		List list = getHibernateTemplate().find("from Stock where stockCode=?",stockCode);
		return (Stock)list.get(0);
	}

}
