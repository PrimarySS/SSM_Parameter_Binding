package cn.ssm.xhchen.po;

import java.util.Date;

/**
 * 
 * ClassName: Items
 * 
 * @Description: ��Ʒ��Ϣpo��չ��
 * @author XHChen
 * @date 2018��10��17�� ����5:09:14
 */
public class ItemsCustomer extends Items {

	// �����չ����

	private Date items_creattime; // ��Ʒ����ʱ��

	public Date getItems_creattime() {
		return items_creattime;
	}

	public void setItems_creattime(Date items_creattime) {
		this.items_creattime = items_creattime;
	}

	@Override
	public String toString() {
		return "ItemsCustomer [toString()=" + super.toString()
				+ ", items_creattime=" + items_creattime + "]";
	}

}
