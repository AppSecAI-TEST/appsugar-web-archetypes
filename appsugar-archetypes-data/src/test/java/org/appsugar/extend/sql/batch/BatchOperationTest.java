package org.appsugar.extend.sql.batch;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.appsugar.BaseJpaDaoTestCase;
import org.appsugar.repository.HibernateContext;
import org.hibernate.jdbc.Work;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;

/**
 * 
 * @author NewYoung
 * 2016年2月24日下午6:59:13
 */
public class BatchOperationTest extends BaseJpaDaoTestCase {

	@Autowired
	private HibernateContext context;
	private int i = 1000;

	@Test
	public void testBatchInsert() {
		context.getSession().doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				BatchOperation<String> op = new DefaultBatchOperation<>(new BatchOperationInformation<String>() {
					@Override
					public String sql() {
						return "insert into as_user (name,login_name,password,id) values (?,?,?,?)";
					}

					@Override
					public List<Object> getParameterList(String entity) {
						List<Object> p = new ArrayList<>();
						p.add(entity);
						p.add(entity);
						p.add(entity);
						p.add(i++);
						return p;
					}

					@Override
					public int batchSize() {
						return 2;
					}
				}, connection);
				int insertCount = op.executeBatch(Lists.newArrayList("1", "2", "3"));
				logger.debug("insert count is {}", insertCount);
			}
		});

	}

	@Test
	public void testValuesBatchInsert() {
		context.getSession().doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				BatchOperation<String> op = new ValuesInsertBatchOperation<>(new BatchOperationInformation<String>() {
					@Override
					public String sql() {
						return "insert into as_user (name,login_name,password,id) values (?,?,?,?)";
					}

					@Override
					public List<Object> getParameterList(String entity) {
						List<Object> p = new ArrayList<>();
						p.add(entity);
						p.add(entity);
						p.add(entity);
						p.add(i++);
						return p;
					}

					@Override
					public int batchSize() {
						return 2;
					}
				}, connection);
				int insertCount = op.executeBatch(Lists.newArrayList("1", "2", "3"));
				logger.debug("values insert count is {}", insertCount);
			}
		});
	}
}
