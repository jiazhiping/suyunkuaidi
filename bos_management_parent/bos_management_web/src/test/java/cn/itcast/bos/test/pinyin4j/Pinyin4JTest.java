package cn.itcast.bos.test.pinyin4j;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import cn.itcast.bos.utils.PinYin4jUtils;

public class Pinyin4JTest {

	/**
	 * shortcode：简码
	 * 		河北省  石家庄市  开发区
	 * 		河北石家庄开发
	 * 		HBSJZKF
	 * citycode：城市码
	 * 		石家庄市
	 * 		石家庄
	 * 		shijiazhuang
	 */
	@Test
	public void testPinyin4J(){
		//1.shortcode：简码
		String province = "河北省";
		String city = "石家庄市";
		String district = "开发区";
		province = province.substring(0, province.length() - 1);
		city = city.substring(0, city.length() - 1);
		district = district.substring(0, district.length() - 1);
		String temp = province+city+district;
		String[] headByString = PinYin4jUtils.getHeadByString(temp);//[H,B,S,J,Z,K,F]
		String shortcode = StringUtils.join(headByString,"");
		System.out.println(shortcode);
		//2.citycode：城市码
		String citycode = PinYin4jUtils.hanziToPinyin(city, "");
		System.out.println(citycode);
	}
}
