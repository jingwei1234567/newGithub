package my.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import my.dao.BaseDao;
import my.dao.NewsDao;
import my.pojo.News_category;
import my.pojo.News_detail;

public class NewsDaoImpl extends BaseDao implements NewsDao{
//查询新闻的方法
	public List<News_detail> getList() {
		List<News_detail> list=new ArrayList<>();
		try {
			String sql="select id,title,summary,content from news_detail";
			Object[] parmas={};
			ResultSet rs=this.executeQuery(sql, parmas);
			try {
				while(rs.next()){
					int id=rs.getInt("id");
					String title=rs.getString("title");
					String summary=rs.getString("summary");
					String content=rs.getString("content");
					News_detail news=new News_detail();
					news.setId(id);
					news.setTitle(title);
					news.setSummary(summary);
					news.setContent(content);
					list.add(news);
//					System.out.println(id+"    "+title);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}finally{
			this.closeResouse();
		}
		return list;
	}
//	添加新闻的方法
	public void addNews(News_detail news){
		try{
			String sql="insert into news_detail(id,title,summary,author,createDate)value(?,?,?,?,?)";
			Object[] parmas={news.getId(), news.getTitle(),news.getSummary(),news.getAuthor(),news.getCreateDate()};
			int i=this.executeUpdate(sql, parmas);
			if(i>0){
				System.out.println("新闻添加成功！");
			}
		}finally{
			this.closeResouse();
		}
	}
//	删除新闻的方法
	public boolean delete(News_detail news){
		boolean flag=false;
		try {
			String sql="delete from news_detail where id=?";
			Object[] parmas={news.getId()};
			int i=this.executeUpdate(sql, parmas);
			if(i>0){
				System.out.println("新闻删除成功！");
				flag=true;
			}
		} finally{
			this.closeResouse();
		}
		return flag;
	}
//	更改新闻的方法
	public void updateNews(News_detail news){
		try {
			String sql="update news_detail set title=? where id=?";
			Object[] parmas={news.getTitle(),news.getId()};
			int i=this.executeUpdate(sql, parmas);
			if(i>0){
				System.out.println("更改成功！");
			}
			
		}finally{
			this.closeResouse();
		}
		
	}
//	查询某个新闻列表下有无新闻
	public int getNewsCountByCategoryId(News_category newsCategory){
		int count=0;
		try {
			String sql="select count(1) as count from news_detail where categoryId=?";
			Object[] parmas={newsCategory.getId()};
			ResultSet rs=this.executeQuery(sql, parmas);
			try {
				while(rs.next()){
					count = rs.getInt("count");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}finally{
			this.closeResouse();
		}
	return count;
	}
//	查询新闻总条数
	public int getTotalCount(){
		int count=0;
		try {
			String sql="select count(1) as count from news_detail";
			Object[] parmas={};
			ResultSet rs=this.executeQuery(sql, parmas);
			try {
				while(rs.next()){
					count = rs.getInt("count");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}finally{
			this.closeResouse();
		}
	return count;
	}
//	去数据库中分页查询
	public List<News_detail> getPageList(int pageNo,int pageSize){
		List<News_detail> list=new ArrayList<>();
		try {
			String sql="select id,title,summary,content from news_detail limit ?,?";
			Object[] parmas={(pageNo-1)*pageSize,pageSize};
			ResultSet rs=this.executeQuery(sql, parmas);
			try {
				while(rs.next()){
					int id=rs.getInt("id");
					String title=rs.getString("title");
					String summary=rs.getString("summary");
					String content=rs.getString("content");
					News_detail news=new News_detail();
					news.setId(id);
					news.setTitle(title);
					news.setSummary(summary);
					news.setContent(content);
					list.add(news);
//					System.out.println(id+"    "+title);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}finally{
			this.closeResouse();
		}
		return list;
		
	}
}

