package in.co.rays.testClasses;

import java.nio.channels.SelectableChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import in.co.rays.bean.DropdownListBean;
import in.co.rays.model.RoleModel;

public class HtmlUtility {

	public static String getList(String name, String SelectedVal, HashMap<String, String> map) {

		StringBuffer sb = new StringBuffer("<select value ='" + name + "'>");
		sb.append("\n<option selected value = ''>----------select----------</option>");

		Set<String> keys = map.keySet();

		String val = null;

		for (String key : keys) {
			val = map.get(key);
			if (key.trim().equals(SelectedVal)) {
				sb.append("\n<option secelcted val='" + key + "'>" + val + "></option>");
			} else {
				sb.append("\n<option value ='" + key + "'>" + val + "</option>");

			}
		}

		sb.append("\n</select>");
		return sb.toString();

	}

	public static String getList(String name, String SelectedVal, List list) {

		StringBuffer sb = new StringBuffer("<select name ='" + name + "'>");
		sb.append("\n<option selected value =''>----------select----------</option>");

		List<DropdownListBean> dd = (List<DropdownListBean>) list;

		String key = null;
		String val = null;

		for (DropdownListBean obj : dd) {

			key = obj.getKey();
			val = obj.getValue();

			if (key.trim().equals(SelectedVal)) {
				sb.append("\n<option Selected value='" + key + "'>" + val + "</option>");
			} else {
				sb.append("\n<option value='" + key + "'>" + val + "</option>");
			}
		}

		sb.append("\n</select>");
		return sb.toString();

	}

	public static void getListFromList() throws Exception {
		RoleModel model = new RoleModel();
		List<DropdownListBean> list = model.list();

		String SelectedVal = "1";
		String HtmlListFromList = HtmlUtility.getList("name", SelectedVal, list);
		System.out.println(HtmlListFromList);

	}

	public static void getListFromMap() {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("male", "male");
		map.put("female", "female");

		String SelectedVal = null;

		String HtmlListFromMap = HtmlUtility.getList("gender", SelectedVal, map);

		System.out.println(HtmlListFromMap);

	}

	public static void main(String[] args) throws Exception {
		//getListFromMap();
		 getListFromList();
	}
}
