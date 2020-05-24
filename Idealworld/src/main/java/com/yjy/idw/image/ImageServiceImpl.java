package com.yjy.idw.image;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;


@Service("ImageService")
public class ImageServiceImpl implements ImageService {
	
	@Override
	public int insertImage(ImageVO vo) {
		System.out.println("여기1");
		
		String resource = "config/mybatis-config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		try(SqlSession session = sqlSessionFactory.openSession()) {
			
			ImageDAO image = session.getMapper(ImageDAO.class);
			image.insertImage(vo);
			session.commit();
			session.close();
		}
		
		System.out.println("여기2");
		
		return 1;
	}
}
