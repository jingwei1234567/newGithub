package my.dao.impl;

import my.dao.BaseDao;
import my.dao.News_categoryDao;
import my.pojo.News_category;

public class News_categoryDaoImpl extends BaseDao implements News_categoryDao{
	public boolean del(News_category newscategory){
		boolean flag=false;
		try {
			String sql="delete from news_category where id=?";
			Object[] parmas={newscategory.getId()};
			int i=this.executeUpdate(sql, parmas);
			if(i>0){
				System.out.println("����ɾ���ɹ���");
				flag=true;
			}
		} finally{
			this.closeResouse();
		}
		return flag;
	}
}
