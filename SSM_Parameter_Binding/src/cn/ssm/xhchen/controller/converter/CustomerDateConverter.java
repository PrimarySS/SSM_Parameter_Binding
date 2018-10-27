package cn.ssm.xhchen.controller.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 
 * ClassName: CustomerDateConverter
 * 
 * @Description: ����ת����
 * @author XHChen
 * @date 2018��10��21�� ����11:56:35
 */
public class CustomerDateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {

		// �����ڴ�ת����yyyy:MM:dd HH:mm:ss��ʽ
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");

		// ���������ʽת��
		try {
			return simpleDateFormat.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

}
