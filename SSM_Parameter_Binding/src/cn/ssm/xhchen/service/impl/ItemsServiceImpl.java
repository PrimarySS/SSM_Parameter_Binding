package cn.ssm.xhchen.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.ssm.xhchen.mapper.ItemsMapper;
import cn.ssm.xhchen.mapper.ItemsMapperCustomer;
import cn.ssm.xhchen.po.Items;
import cn.ssm.xhchen.po.ItemsCustomer;
import cn.ssm.xhchen.po.ItemsQueryVo;
import cn.ssm.xhchen.service.ItemsService;

/**
 * 
 * ClassName: ItemsServiceImpl
 * 
 * @Description: Items����ʵ��
 * @author XHChen
 * @date 2018��10��17�� ����8:50:58
 */
public class ItemsServiceImpl implements ItemsService {

	// applicationContext-dao.xml��ͨ����ɨ��������ItemsMapperCustomer
	@Autowired
	private ItemsMapperCustomer itemsMapperCustomer;
	
	// �Զ�ע��ItemsMapper�ӿ�
	@Autowired
	private ItemsMapper itemsMapper;

	@Override
	/**
	 * ͨ��itemsQueryVo��ѯ��Ʒ��Ϣ
	 */
	public List<ItemsCustomer> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {

		// ͨ��ItemsMapperCustomer��ѯ���ݿ�
		return itemsMapperCustomer.findItemsList(itemsQueryVo);
	}

	
	@Override
	/**
	 * ����id��ѯ��Ʒ��Ϣ
	 */
	public ItemsCustomer findItemsById(Integer id) throws Exception {
		
		// ����id��ѯ��Ʒ��Ϣ
		Items items = itemsMapper.findItemsById(id);
		
		// ����ItemsCustomer����
		ItemsCustomer itemsCustomer = new ItemsCustomer();
		
		// ����Ʒ��Ϣitems���Ƶ�itemsCustomer
		BeanUtils.copyProperties(items, itemsCustomer);
		
		// ������չ��ItemsCustomer
		return itemsCustomer;
	}

	
	@Override
	/**
	 * �޸���Ʒ��Ϣ
	 */
	public void updateItems(Integer id, ItemsCustomer itemsCustomer) throws Exception {

		// �����޸���Ʒid
		itemsCustomer.setId(id);
		
		// �޸���Ʒ��Ϣ
		itemsMapper.updateItems(itemsCustomer);
		
	}

	
	@Override
	/**
	 * ����ɾ��������Ʒ��Ϣ
	 */
	public void deleteItemsArray(Integer[] items_ids) throws Exception {
		
		// ÿ�δ�һ��idֵɾ��
		try {
			for (Integer id : items_ids) {
				// ����itemsMapper�ӿڷ���
				itemsMapper.deleteItems(id);
			}
		} catch (Exception e) {
			// ���������־
			System.out.println(e.getMessage());
		}
	}

	
	@Override
	/**
	 * һ����ɾ����Ʒ��Ϣ
	 */
	public void deleteItemsArrayAllIn(Integer[] items_ids) throws Exception {
		
		// ����itemsMapperCustomer�ӿڷ���
		itemsMapperCustomer.deleteItemsArrayAllIn(items_ids);
	}
	
	
	@Override
	/**
	 * �����ύ�����޸���Ʒ��Ϣ
	 */
	public void updateAllItems(List<ItemsCustomer> itemsList) throws Exception {
		
		try {
			for (ItemsCustomer itemsCustomer : itemsList) {
				// ����itemsMapper�ӿڷ���
				itemsMapper.updateItems(itemsCustomer);
			}
		} catch (Exception e) {
			// ���������־
			System.out.println(e.getMessage());
		}
	}

	
	@Override
	/**
	 * һ�����ύ�����޸���Ʒ��Ϣ
	 */
	public void updateAllItemsSubmit(List<ItemsCustomer> itemsList) throws Exception {
		
		// ����itemsMapperCustomer�ӿڷ���
		itemsMapperCustomer.updateAllItemsSubmit(itemsList);
	}

}
