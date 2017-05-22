package cn.edu.zhku.shopping.goods.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.edu.zhku.shopping.goods.domain.goods.Goods;
import cn.edu.zhku.shopping.pager.Expression;
import cn.edu.zhku.shopping.pager.PageBean;
import cn.edu.zhku.shopping.pager.PageConstants;
import cn.itcast.jdbc.TxQueryRunner;


/**
 * 商品模块持久层
 * @author Administrator
 *
 */
public class GoodsDao {

	private static QueryRunner qr = new TxQueryRunner();
	//private QueryRunner qr=new TxQueryRunner();

	/**
	 * 主页菜单显示，按分类查
	 * 1.利用Expression拼接要查询的语句条件
	 * 2.利用通用查询方法，得到按分类查询的分页结果
	 * @param cid
	 * @param pc
	 * @return
	 * @throws SQLException 
	 */
	public static PageBean<Goods> findByCategory(String cid, int pc) throws SQLException {

		// 1.利用Expression拼接要查询的语句条件
		List<Expression> exprList=new ArrayList<Expression>();
		exprList.add(new Expression("cid","=",cid));
		return findByCriteria(exprList, pc);
	}

	/**
	 * 通用的查询方法,得到相应的分页查询结果
	 * 1.得到每页记录pc
	 * 2.通过List<Expression> exprList生成相应where子句
	 * 3.得到总记录书tr
	 * 4.得到当前页记录beanList
	 * 5.创建相应的PageBean,返回分页查询结果
	 * @param exprList
	 * @param pc
	 * @return
	 * @throws SQLException 
	 */
	private static PageBean<Goods> findByCriteria(List<Expression> exprList,int pc) throws SQLException {
		
		//1.得到每页记录pc
		int ps=PageConstants.GOODS_PAGE_SIZE;
		//2.通过List<Expression> exprList生成相应where子句
		StringBuilder whereSql=new StringBuilder(" where 1=1");
		
		ArrayList<Object> params = new ArrayList<Object>();
		for(Expression expr:exprList){
			whereSql.append(" and ").append(expr.getName()).append(" ")
			.append(expr.getOperator()).append(" ");
			
			//如果Operator为is null，说明已经不需要条件了
			//两种情况：where cid is null  和   where cid = 3
			
			//情况一：where cid = 3
			if(!expr.getOperator().equals("is null")){
				whereSql.append("?");
			}
			params.add(expr.getValue());//相应参数位置上赋值
		}
		//3.得到总记录书tr
		String sql="select count(*) from t_goods"+whereSql;
		Number number = (Number)qr.query(sql, new ScalarHandler(), params.toArray());
		int tr = number.intValue();
		//4.得到当前页记录beanList
		sql="select * from t_goods"+whereSql+"order by orderBy limit ?,?";
		params.add((pc-1)*ps);//当前页记录的开始下标
		params.add(ps);//当前页记录数
		List<Goods> beanList=qr.query(sql, new BeanListHandler<Goods>(Goods.class),params.toArray());
		//5.创建相应的PageBean,返回分页查询结果
		PageBean<Goods> pb=new PageBean<Goods>();
		
		//当前PageBean中没设置url，url的设置有Servlet完成
		pb.setBeanList(beanList);//设置记录
		pb.setPc(pc);//设置当前页
		pb.setPs(ps);//设置每页记录
		pb.setTr(tr);//设置总记录
		
		return pb;
	}
}
