package com.jdbc.repository.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;





import org.apache.commons.collections4.CollectionUtils;

import com.jdbc.domain.Address;
import com.jdbc.domain.Customer;
import com.jdbc.util.DataBaceConnectionUtil;

public class CustomerAddressRepository {
	public static void main(String[] args) {
		CustomerAddressRepository customerAddressRepository = new CustomerAddressRepository();
		int cId = 101;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Customer customer = null;
		try {
			con = DataBaceConnectionUtil.getConnection();
			customer = customerAddressRepository.getCustomerBycId(cId, con);
			List<String> zipcodes = customerAddressRepository.getZipcodesBycId(cId, con);
			List<Address> addresses =null;
			//if(null != zipcodes && zipcodes.size()>0){
			if(CollectionUtils.isNotEmpty(zipcodes)){
				addresses= customerAddressRepository.getAddressesbyZipcodes(zipcodes, con);
			}
			
          if(null != customer){
        	  customer.setAddressList(addresses);
          }
          customerAddressRepository.printCustomerDetails(customer);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				DataBaceConnectionUtil.closeRs(rs);
				DataBaceConnectionUtil.closePstmt(pstmt);
				DataBaceConnectionUtil.closeConnection(con);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	private Customer getCustomerBycId(int cId, Connection con) throws SQLException {
		PreparedStatement pstmt = con.prepareStatement("select * from Customer where cId=?");
		pstmt.setInt(1, cId);
		ResultSet rs = pstmt.executeQuery();
		Customer customer=null;
		while (rs.next()) {
			customer = new Customer();
			customer.setcId(rs.getInt("cId"));
			customer.setcName(rs.getString("cName"));
			customer.setcEmailId(rs.getString("cEmailId"));
		}
		return customer;
	}
	private List<String> getZipcodesBycId(int cId, Connection con) throws SQLException {
		PreparedStatement pstmt = con.prepareStatement("select zipcode from CustomerAddress where cId=?");
		pstmt.setInt(1, cId);
		ResultSet rs = pstmt.executeQuery();
		List<String> zipcodes = new ArrayList<String>();
		while (rs.next()) {
			zipcodes.add(rs.getString("zipcode"));
		}
		return zipcodes;
	}

	private List<Address> getAddressesbyZipcodes(List<String> zipcodes,Connection con)
			throws SQLException {
		StringBuilder sb = new StringBuilder("select * from Address where zipcode in(");
		String delimiter = "";
		for (int i = 0; i < zipcodes.size(); i++) {
			sb.append(delimiter).append("?");
			delimiter = ",";
		}
		sb.append(")");
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		int i = 1;
		for (String zipcode : zipcodes) {
			pstmt.setString(i, zipcode);
			i++;
		}
		 ResultSet rs = pstmt.executeQuery();
		List<Address> addresses = new ArrayList<Address>();
		Address a = null;
		while (rs.next()) {
			a = new Address();
			a.setZipcode(rs.getString("zipcode"));
			a.setArea(rs.getString("area"));
			a.setStreet(rs.getString("street"));
			a.setDistrict(rs.getString("district"));
			addresses.add(a);
		}
		return addresses;
	}

	private void printCustomerDetails(Customer customer) {
     if(null != customer){
		System.out.println(customer.getcId() + "," + customer.getcName() + "," + customer.getcEmailId());
     
		List<Address> addressList = customer.getAddressList();
		printAddresses(addressList);
     }else{
    	 System.out.println("customer is not found.");
     }
	}
	private void printAddresses(List<Address> addressList) {
		//if(null != addressList ){
		if(CollectionUtils.isNotEmpty(addressList)){
		for (Address a1 : addressList) {
			System.out.println(a1.getZipcode() + "," + a1.getArea() + "," + a1.getStreet() + "," + a1.getDistrict());
		}
		}else{
			System.out.println("address not found.");
		}
	}
}

