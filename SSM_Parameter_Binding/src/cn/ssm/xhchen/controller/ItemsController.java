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
 * @Description: ��Ʒ�������
 * @author XHChen
 * @date 2018��10��26�� ����10:28:22
 */
@Controller
// խ������ӳ��,��url���з������
@RequestMapping("/items")
public class ItemsController {

	@Autowired
	private ItemsService itemsService;

	/**
	 * 
	 * @Description: ��ѯ��Ʒ��Ϣ,pojo��
	 * @param @param itemsQueryVo pojo��
	 * @param @return ������Ʒ�б�
	 * @param @throws Exception
	 * @return ModelAndView
	 * @throws
	 * @author XHChen
	 * @date 2018��10��26�� ����10:23:36
	 */
	@RequestMapping("/queryItems.action")
	public ModelAndView queryItems(HttpServletRequest request, ItemsQueryVo itemsQueryVo)
			throws Exception {

		// ����service������ѯ���ݿ�
		List<ItemsCustomer> itemsList = itemsService.findItemsList(itemsQueryVo);

		// �������
		for (ItemsCustomer itemsCustomer2 : itemsList) {
			System.out.println(itemsCustomer2);
		}
		System.out.println(itemsList.size());

		// ����ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemsList", itemsList);

		// ָ����ͼ
		// ·��ǰ׺�ͺ�׺����springmvc.xml����
		modelAndView.setViewName("items/itemsList");

		return modelAndView;

	}

	
	/**
	 * 
	 * @Description: ��Ʒ��Ϣ�޸�ҳ��
	 * @param @param request ͨ��request�����ȡ������Ϣ
	 * @param @param id ��Ʒ��Ϣid
	 * @param @return �����޸�ҳ��
	 * @param @throws Exception
	 * @return ModelAndView
	 * @throws
	 * @author XHChen
	 * @date 2018��10��26�� ����10:16:06
	 */
	// 1.Integer id��Ҫ��request���ز���������controller�β�����һ��
	// 2.@RequestParam�Լ����͵Ĳ������а�
	// 	a.@RequestParam,ָ��request���ز���������controller�β����ư�
	// 	b.required,ָ���������봫��
	// 	c.defaultValue,idĬ�ϲ���
	@RequestMapping("/editItems.action")
	public ModelAndView editItems(
			HttpServletRequest request,
			@RequestParam(value = "id", required = true, defaultValue = "id") Integer id)
			throws Exception {

		// ����itemsService�ӿڣ���ѯ��Ʒ��Ϣ
		ItemsCustomer itemsCustomer = itemsService.findItemsById(id);

		// ����ModelAndView
		ModelAndView modelAndView = new ModelAndView();

		// �������ݵ�ҳ��
		modelAndView.addObject("itemsCustomer", itemsCustomer);

		// ָ����ͼ
		// ·��ǰ׺�ͺ�׺����springmvc.xml����
		modelAndView.setViewName("items/editItems");

		// ����ָ����ͼ
		return modelAndView;

	}

	
	/**
	 * 
	 * @Description: �޸���Ʒ��Ϣ,�����
	 * @param @param request ͨ��request�����ȡ������Ϣ
	 * @param @param id ��Ʒ��Ϣid
	 * @param @param itemsCustomer
	 * @param @return ������Ʒ�б�
	 * @param @throws Exception
	 * @return ModelAndView
	 * @throws
	 * @author XHChen
	 * @date 2018��10��26�� ����10:18:14
	 */
	// ItemsCustomer itemsCustomer pojo���bҳ��request���ز���
	@RequestMapping("/editItemsSubmit.action")
	public ModelAndView editItemsSubmit(HttpServletRequest request,
			Integer id, ItemsCustomer itemsCustomer, ItemsQueryVo itemsQueryVo)
			throws Exception {

		// ����itemsService�����޸���Ʒ��Ϣ����Ҫ��ҳ�������ύ���˷���
		itemsService.updateItems(id, itemsCustomer);

		// ���²�ѯ��Ʒ��Ϣ
		List<ItemsCustomer> itemsList = itemsService.findItemsList(itemsQueryVo);

		// ����ModelAndView
		ModelAndView modelAndView = new ModelAndView();

		// ���ز�����ҳ��
		modelAndView.addObject("itemsList", itemsList);

		// ָ����ͼ
		modelAndView.setViewName("items/itemsList");

		// ����ָ����ͼ
		return modelAndView;

	}

	
	/**
	 * 
	 * @Description: ����ɾ����Ʒ
	 * @param @param items_id ��ҳ��checkbox�����Ʊ���һ��
	 * @param @return ������Ʒ�б�
	 * @return String
	 * @throws Exception
	 * @throws
	 * @author XHChen
	 * @date 2018��10��26�� ����9:57:26
	 */
	@RequestMapping("/deleteItems.action")
	public ModelAndView deleteItems(HttpServletRequest requests, ItemsQueryVo itemsQueryVo) throws Exception {

		// ����itemsService����
		// ��һɾ����Ʒ��Ϣ
		// itemsService.deleteItemsArray(itemsQueryVo.getItems_ids());
		
		// һ��ɾ����Ʒ��Ϣ,��Ҫsqlƴ��
		itemsService.deleteItemsArrayAllIn(itemsQueryVo.getItems_ids());

		// ���²�ѯ��Ʒ��Ϣ
		List<ItemsCustomer> itemsList = itemsService.findItemsList(itemsQueryVo);

		// ����ModelAndView
		ModelAndView modelAndView = new ModelAndView();

		// ���ز�����ҳ��
		modelAndView.addObject("itemsList", itemsList);

		// ָ����ͼ
		modelAndView.setViewName("items/itemsList");

		// ����ָ����ͼ
		return modelAndView;
	}
	
	
	/**
	 * 
	 * @Description: �����޸���Ʒ��Ϣҳ�� ,list��
	 * @param @param request ͨ��request�����ȡ������Ϣ
	 * @param @param itemsQueryVo pojo��
	 * @param @return ���������޸���Ʒ��Ϣҳ��
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @throws
	 * @author XHChen
	 * @date 2018��10��26�� ����9:14:40
	 */
	@RequestMapping("/editItemsQuery.action")
	public ModelAndView editItemsQuery(HttpServletRequest request, ItemsQueryVo itemsQueryVo)
			throws Exception {
		
		// ��ѯ������Ʒ��Ϣ
		List<ItemsCustomer> itemsList = itemsService.findItemsList(itemsQueryVo);
		
		// ����ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		
		// ��ҳ�洫������
		modelAndView.addObject("itemsList", itemsList);
		
		// ָ����ͼ
		modelAndView.setViewName("items/editItemsQuery");
		
		// ����ָ����ͼ
		return modelAndView;
		
	}
	

	/**
	 * 
	 * @Description: �ύ�����޸���Ʒ��Ϣ
	 * ��ԭ��ͨ��ItemsQueryVo�����ύ��Ʒ��Ϣ������Ʒ��Ϣ�洢��ItemsQueryVo��itemsList������
	 * @param @param request ͨ��request�����ȡ������Ϣ
	 * @param @param itemsQueryVo pojo��
	 * @param @return ������Ʒ��Ϣҳ��
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @throws
	 * @author XHChen
	 * @date 2018��10��26�� ����9:30:23
	 */
	@RequestMapping("/editItemsAllSubmit.action")
	public ModelAndView editItemsAllSubmit(HttpServletRequest request, ItemsQueryVo itemsQueryVo) 
			throws Exception {
		
		// ����itemsService�ӿڷ���
		// �����ύ�����޸���Ʒ��Ϣ
		// itemsService.updateAllItems(itemsQueryVo.getItemsList());
		
		// һ�����ύ�����޸���Ʒ��Ϣ,��Ҫsqlƴ��
		itemsService.updateAllItemsSubmit(itemsQueryVo.getItemsList());
		
		// ���²�ѯ������Ʒ��Ϣ
		List<ItemsCustomer> itemsList = itemsService.findItemsList(itemsQueryVo);
		
		// ����ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		
		// ��ҳ�洫������
		modelAndView.addObject("itemsList", itemsList);
		
		// ָ����ͼ
		modelAndView.setViewName("items/itemsList");
		
		// ����ָ����ͼ
		return modelAndView;
	}
	
}
