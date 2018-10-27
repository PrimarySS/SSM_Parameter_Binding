package cn.ssm.xhchen.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.ssm.xhchen.po.ItemsCustomer;
import cn.ssm.xhchen.po.ItemsQueryVo;
import cn.ssm.xhchen.service.ItemsService;

/**
 * 
 * ClassName: ItemsController
 * 
 * @Description: 商品管理控制
 * @author XHChen
 * @date 2018年10月26日 上午10:28:22
 */
@Controller
// 窄化请求映射,对url进行分类管理
@RequestMapping("/items")
public class ItemsController {

	@Autowired
	private ItemsService itemsService;

	/**
	 * 
	 * @Description: 查询商品信息,pojo绑定
	 * @param @param itemsQueryVo pojo绑定
	 * @param @return 返回商品列表
	 * @param @throws Exception
	 * @return ModelAndView
	 * @throws
	 * @author XHChen
	 * @date 2018年10月26日 上午10:23:36
	 */
	@RequestMapping("/queryItems.action")
	public ModelAndView queryItems(HttpServletRequest request, ItemsQueryVo itemsQueryVo)
			throws Exception {

		// 调用service方法查询数据库
		List<ItemsCustomer> itemsList = itemsService.findItemsList(itemsQueryVo);

		// 遍历结果
		for (ItemsCustomer itemsCustomer2 : itemsList) {
			System.out.println(itemsCustomer2);
		}
		System.out.println(itemsList.size());

		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemsList", itemsList);

		// 指定视图
		// 路径前缀和后缀已由springmvc.xml配置
		modelAndView.setViewName("items/itemsList");

		return modelAndView;

	}

	
	/**
	 * 
	 * @Description: 商品信息修改页面
	 * @param @param request 通过request对象获取请求信息
	 * @param @param id 商品信息id
	 * @param @return 返回修改页面
	 * @param @throws Exception
	 * @return ModelAndView
	 * @throws
	 * @author XHChen
	 * @date 2018年10月26日 上午10:16:06
	 */
	// 1.Integer id，要求request返回参数名称与controller形参名称一致
	// 2.@RequestParam对简单类型的参数进行绑定
	// 	a.@RequestParam,指定request返回参数名称与controller形参名称绑定
	// 	b.required,指定参数必须传入
	// 	c.defaultValue,id默认参数
	@RequestMapping("/editItems.action")
	public ModelAndView editItems(
			HttpServletRequest request,
			@RequestParam(value = "id", required = true, defaultValue = "id") Integer id)
			throws Exception {

		// 调用itemsService接口，查询商品信息
		ItemsCustomer itemsCustomer = itemsService.findItemsById(id);

		// 创建ModelAndView
		ModelAndView modelAndView = new ModelAndView();

		// 返回数据到页面
		modelAndView.addObject("itemsCustomer", itemsCustomer);

		// 指定视图
		// 路径前缀和后缀已由springmvc.xml配置
		modelAndView.setViewName("items/editItems");

		// 返回指定视图
		return modelAndView;

	}

	
	/**
	 * 
	 * @Description: 修改商品信息,数组绑定
	 * @param @param request 通过request对象获取请求信息
	 * @param @param id 商品信息id
	 * @param @param itemsCustomer
	 * @param @return 返回商品列表
	 * @param @throws Exception
	 * @return ModelAndView
	 * @throws
	 * @author XHChen
	 * @date 2018年10月26日 上午10:18:14
	 */
	// ItemsCustomer itemsCustomer pojo封裝页面request返回参数
	@RequestMapping("/editItemsSubmit.action")
	public ModelAndView editItemsSubmit(HttpServletRequest request,
			Integer id, ItemsCustomer itemsCustomer, ItemsQueryVo itemsQueryVo)
			throws Exception {

		// 调用itemsService方法修改商品信息，需要将页面数据提交到此方法
		itemsService.updateItems(id, itemsCustomer);

		// 重新查询商品信息
		List<ItemsCustomer> itemsList = itemsService.findItemsList(itemsQueryVo);

		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();

		// 返回参数到页面
		modelAndView.addObject("itemsList", itemsList);

		// 指定视图
		modelAndView.setViewName("items/itemsList");

		// 返回指定视图
		return modelAndView;

	}

	
	/**
	 * 
	 * @Description: 批量删除商品
	 * @param @param items_id 与页面checkbox的名称保持一致
	 * @param @return 返回商品列表
	 * @return String
	 * @throws Exception
	 * @throws
	 * @author XHChen
	 * @date 2018年10月26日 上午9:57:26
	 */
	@RequestMapping("/deleteItems.action")
	public ModelAndView deleteItems(HttpServletRequest requests, ItemsQueryVo itemsQueryVo) throws Exception {

		// 调用itemsService方法
		// 逐一删除商品信息
		// itemsService.deleteItemsArray(itemsQueryVo.getItems_ids());
		
		// 一次删除商品信息,需要sql拼接
		itemsService.deleteItemsArrayAllIn(itemsQueryVo.getItems_ids());

		// 重新查询商品信息
		List<ItemsCustomer> itemsList = itemsService.findItemsList(itemsQueryVo);

		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();

		// 返回参数到页面
		modelAndView.addObject("itemsList", itemsList);

		// 指定视图
		modelAndView.setViewName("items/itemsList");

		// 返回指定视图
		return modelAndView;
	}
	
	
	/**
	 * 
	 * @Description: 批量修改商品信息页面 ,list绑定
	 * @param @param request 通过request对象获取请求信息
	 * @param @param itemsQueryVo pojo绑定
	 * @param @return 返回批量修改商品信息页面
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @throws
	 * @author XHChen
	 * @date 2018年10月26日 下午9:14:40
	 */
	@RequestMapping("/editItemsQuery.action")
	public ModelAndView editItemsQuery(HttpServletRequest request, ItemsQueryVo itemsQueryVo)
			throws Exception {
		
		// 查询所有商品信息
		List<ItemsCustomer> itemsList = itemsService.findItemsList(itemsQueryVo);
		
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		
		// 向页面传递数据
		modelAndView.addObject("itemsList", itemsList);
		
		// 指定视图
		modelAndView.setViewName("items/editItemsQuery");
		
		// 返回指定视图
		return modelAndView;
		
	}
	

	/**
	 * 
	 * @Description: 提交批量修改商品信息
	 * 绑定原理：通过ItemsQueryVo批量提交商品信息，将商品信息存储在ItemsQueryVo的itemsList属性中
	 * @param @param request 通过request对象获取请求信息
	 * @param @param itemsQueryVo pojo绑定
	 * @param @return 返回商品信息页面
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @throws
	 * @author XHChen
	 * @date 2018年10月26日 下午9:30:23
	 */
	@RequestMapping("/editItemsAllSubmit.action")
	public ModelAndView editItemsAllSubmit(HttpServletRequest request, ItemsQueryVo itemsQueryVo) 
			throws Exception {
		
		// 调用itemsService接口方法
		// 逐条提交批量修改商品信息
		// itemsService.updateAllItems(itemsQueryVo.getItemsList());
		
		// 一次性提交批量修改商品信息,需要sql拼接
		itemsService.updateAllItemsSubmit(itemsQueryVo.getItemsList());
		
		// 重新查询所有商品信息
		List<ItemsCustomer> itemsList = itemsService.findItemsList(itemsQueryVo);
		
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		
		// 向页面传递数据
		modelAndView.addObject("itemsList", itemsList);
		
		// 指定视图
		modelAndView.setViewName("items/itemsList");
		
		// 返回指定视图
		return modelAndView;
	}
	
}
