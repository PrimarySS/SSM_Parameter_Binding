package cn.ssm.xhchen.mapper;

import cn.ssm.xhchen.po.Items;

/**
 * 
 * ClassName: ItemsMapper
 * 
 * @Description: Items��mapper�ӿ�
 * @author XHChen
 * @date 2018��10��17�� ����5:11:51
 */
public interface ItemsMapper {

	// ���
	public void insertItems(Items items) throws Exception;

	// �޸�
	public void updateItems(Items items) throws Exception;

	// ɾ��
	public void deleteItems(int id) throws Exception;

	// ͨ��id��ѯ
	public Items findItemsById(int id) throws Exception;

}
