package cn.ssm.xhchen.po;

import java.util.List;

/**
 * 
 * ClassName: ItemsQueryVo
 * 
 * @Description: ��Ʒ��Ϣ��װ��
 * @author XHChen
 * @date 2018��10��17�� ����8:03:46
 */
public class ItemsQueryVo {

	// ��Ʒ��Ϣ
	private Items items;

	// ϵͳ��չ�ԣ���ԭʼpo������չ
	private ItemsCustomer itemsCustomer;

	// �������飬������Ҫ��ҳ��name����һ��
	private Integer[] items_ids;

	// ����list����,������Ʒ��Ϣ
	private List<ItemsCustomer> itemsList;

	public Items getItems() {
		return items;
	}

	public void setItems(Items items) {
		this.items = items;
	}

	public ItemsCustomer getItemsCustomer() {
		return itemsCustomer;
	}

	public void setItemsCustomer(ItemsCustomer itemsCustomer) {
		this.itemsCustomer = itemsCustomer;
	}

	public Integer[] getItems_ids() {
		return items_ids;
	}

	public void setItems_ids(Integer[] items_ids) {
		this.items_ids = items_ids;
	}

	public List<ItemsCustomer> getItemsList() {
		return itemsList;
	}

	public void setItemsList(List<ItemsCustomer> itemsList) {
		this.itemsList = itemsList;
	}

	@Override
	public String toString() {
		return "ItemsQueryVo [items=" + items + ", itemsCustomer="
				+ itemsCustomer + "]";
	}

}
