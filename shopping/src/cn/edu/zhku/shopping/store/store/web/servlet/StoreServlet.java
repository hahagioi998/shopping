package cn.edu.zhku.shopping.store.store.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhku.shopping.store.store.domain.Store;
import cn.edu.zhku.shopping.store.store.service.StoreService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class StoreServlet extends BaseServlet {
	
	private StoreService storeService=new StoreService();
	/**
	 * 用户开店
	 * 1.获取开店信息
	 * 2.利用service同名方法进行开店
	 * 3.返回开店后的页面
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String creatStore(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//1.获取开店信息
		Store store = CommonUtils.toBean(req.getParameterMap(), Store.class);

		String cid = req.getParameter("cid");
		String uid=req.getParameter("uid");
		//2.利用service同名方法进行开店
		storeService.creatStore(store,cid,uid);
		//3.返回开店后的页面
		
		/**
		 * 框架页的跳转问题：/storejsps/store/index.jsp  --> /storejsps/store/main.jsp
		 * 从里层框架跳转到外层框架
		 * 在index.jsp写代码如下
		 * <script>
	     * window.parent.parent.location.href='<%=basePath%>/storejsps/store/main.jsp';
	     * </script>
		 */
		return "f:/storejsps/store/index.jsp";
	}

}