package com.my.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.my.dto.MyDTO;

public class MyDAO {

	public List<MyDTO> selectList() {
		
		String resource = "com/my/db/mybatis-config.xml";
		
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		List<MyDTO> list = session.selectList("com.my.mapper.selectList");
		session.close();

		return list;
	}

	public MyDTO selectOne(int myNo) {
		
		String resource = "com/my/db/mybatis-config.xml";
		
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		MyDTO dto = session.selectOne("com.my.mapper.selectOne", myNo);
		session.close();
		
		return dto;
	}

	public int insert(MyDTO dto) {
		
		String resource = "com/my/db/mybatis-config.xml";
		
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		int res = session.insert("com.my.mapper.insert", dto);
		session.close();

		return res;
	}

	public int update(MyDTO dto) {
		
		return 0;
	}

	public int delete(int myNo) {

		return 0;
	}
}
