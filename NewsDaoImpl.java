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
//��ѯ���ŵķ���
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
//	������ŵķ���
	public void addNews(News_detail news){
		try{
			String sql="insert into news_detail(id,title,summary,author,createDate)value(?,?,?,?,?)";
			Object[] parmas={news.getId(), news.getTitle(),news.getSummary(),news.getAuthor(),news.getCreateDate()};
			int i=this.executeUpdate(sql, parmas);
			if(i>0){
				System.out.println("������ӳɹ���");
			}
		}finally{
			this.closeResouse();
		}
	}
//	ɾ�����ŵķ���
	public boolean delete(News_detail news){
		boolean flag=false;
		try {
			String sql="delete from news_detail where id=?";
			Object[] parmas={news.getId()};
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
//	�������ŵķ���
	public void updateNews(News_detail news){
		try {
			String sql="update news_detail set title=? where id=?";
			Object[] parmas={news.getTitle(),news.getId()};
			int i=this.executeUpdate(sql, parmas);
			if(i>0){
				System.out.println("���ĳɹ���");
			}
			
		}finally{
			this.closeResouse();
		}
		
	}
//	��ѯĳ�������б�����������
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
//	��ѯ����������
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
//	ȥ���ݿ��з�ҳ��ѯ
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

