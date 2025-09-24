package in.co.rays.testModel;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.PatientBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.PatientModel;

public class TestPatientModel {
	

	
	public static PatientModel model = new PatientModel();

	public static void main(String[] args) throws Exception {
		//TestAdd();
		//TestSearch();
		//testListAll();
		//TestUpdate();
		TestDelete();
	}
	public static void TestDelete() throws ApplicationException {
		PatientBean bean = new PatientBean();
		bean.setId(1);
		model.delete(bean);
	}
	public static void TestUpdate() throws ApplicationException, ParseException {
 		PatientBean bean = new PatientBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		 
		bean.setName("Vedik Vishwakarma");
		bean.setDob(sdf.parse("2002-07-12"));
		bean.setMobile("8574585965");
		bean.setDisease("Fever");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		bean.setId(1);
		
		model.update(bean);
		 
		System.out.println("Record updated");
		 
		
	}


	public static void TestAdd() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		PatientBean bean = new PatientBean();
		
 		bean.setName("Aditya Sharma");
		bean.setMobile("7485478545");
		bean.setDisease("Fever");
		bean.setDob(sdf.parse("26/07/2002"));
		bean.setCreatedBy("admin@gmail.com");
		bean.setModifiedBy("admin@gmial.com");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

 
		model.add(bean);
	}
	
	public static void testListAll() throws Exception {
		PatientModel model = new PatientModel();
		List<PatientBean> list = model.list();
		
		for (PatientBean bean : list) {
			System.out.print("\t" + bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getMobile());
			System.out.print("\t" + bean.getDisease());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		}
	}
	
	public static void TestSearch() throws Exception {
		
 		PatientBean bean = new PatientBean();

        List<PatientBean> list = model.search(bean, 1, 10);  

        Iterator<PatientBean> it = list.iterator();
        while (it.hasNext()) {
              bean = it.next();
            System.out.print(bean.getId() + "\t");
            System.out.print(bean.getName() + "\t");
            System.out.print(bean.getMobile() + "\t");
            System.out.print(bean.getDisease() + "\t");
            System.out.print(bean.getDob() + "\t");
            System.out.print(bean.getCreatedBy() + "\t");
            System.out.print(bean.getModifiedBy() + "\t");
            System.out.print(bean.getCreatedDatetime() + "\t");
            System.out.println(bean.getModifiedDatetime());
        }

	}
	
	public static void FindByPk() {
		PatientBean  bean = new PatientBean();
	}



}
