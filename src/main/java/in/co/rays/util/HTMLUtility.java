package in.co.rays.util;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import in.co.rays.bean.DropdownListBean;
import in.co.rays.model.RoleModel;

/**
 * Utility class to generate HTML select (dropdown) elements.
 * Supports creation of dropdowns from both HashMap and List data sources.
 * 
 * @author Aditya
 * @since 2025
 * @version 1.0
 */
public class HTMLUtility {

	/**
	 * Generates an HTML select element from a HashMap of options.
	 * 
	 * @param name        the name attribute of the select element
	 * @param selectedVal the currently selected value
	 * @param map         HashMap containing key-value pairs for options
	 * @return HTML string representing the select element
	 */
	public static String getList(String name, String selectedVal, HashMap<String, String> map) {

		StringBuffer sb = new StringBuffer(
				"<select style=\"width: 170px;text-align-last: center;\"; class='form-control' name='" + name + "'>");

		sb.append("\n<option selected value=''>-------------Select-------------</option>");

		Set<String> keys = map.keySet();
		String val = null;

		for (String key : keys) {
			val = map.get(key);
			if (key.trim().equals(selectedVal)) {
				sb.append("\n<option selected value='" + key + "'>" + val + "</option>");
			} else {
				sb.append("\n<option value='" + key + "'>" + val + "</option>");
			}
		}
		sb.append("\n</select>");
		return sb.toString();
	}

	/**
	 * Generates an HTML select element from a List of DropdownListBean objects.
	 * 
	 * @param name        the name attribute of the select element
	 * @param selectedVal the currently selected value
	 * @param list        List of DropdownListBean containing key-value pairs
	 * @return HTML string representing the select element
	 */
	public static String getList(String name, String selectedVal, List list) {

		List<DropdownListBean> dd = (List<DropdownListBean>) list;

		StringBuffer sb = new StringBuffer("<select style=\"width: 170px;text-align-last: center;\"; "
				+ "class='form-control' name='" + name + "'>");

		sb.append("\n<option selected value=''>-------------Select-------------</option>");

		String key = null;
		String val = null;

		for (DropdownListBean obj : dd) {
			key = obj.getKey();
			val = obj.getValue();
			if (key.trim().equals(selectedVal)) {
				sb.append("\n<option selected value='" + key + "'>" + val + "</option>");
			} else {
				sb.append("\n<option value='"+key+"'>"+val+"</option>");
			}
		}
		sb.append("\n</select>");
		return sb.toString();
	}

	/**
	 * Test method for generating an HTML select from a HashMap.
	 */
	public static void testGetListByMap() {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("male", "male");
		map.put("female", "female");

		String selectedValue = "male";
		String htmlSelectFromMap = HTMLUtility.getList("gender", selectedValue, map);

		System.out.println(htmlSelectFromMap);
	}

	/**
	 * Test method for generating an HTML select from a List.
	 * 
	 * @throws Exception if list retrieval fails
	 */
	public static void testGetListByList() throws Exception {

		RoleModel model = new RoleModel();
		List<DropdownListBean> list = model.list();
		String selectedValue = "1";

		String htmlSelectFromList = HTMLUtility.getList("name", selectedValue, list);

		System.out.println(htmlSelectFromList);
	}

	/**
	 * Main method to execute test cases.
	 * 
	 * @param args command line arguments
	 * @throws Exception if any test method fails
	 */
	public static void main(String[] args) throws Exception {

		//testGetListByMap();
		testGetListByList();

	}
}
