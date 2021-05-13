package com.ERS.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ERS.util.ErsUtil;

public class ManagerDaoImp implements ManagerDao{

	@Override
	public boolean requestAction(int reimID, int action, int managerID) {
		boolean success = false;
		
		try(Connection conn = ErsUtil.getConnection();) {
			
			//update offer status column and return the current row for later use.
			String setStatus = "UPDATE ers_reimbursement SET (reim_resolved, reim_resolver, reim_status_id) = (now(),?,?) where reim_id = ? RETURNING *;";
			PreparedStatement ps = conn.prepareStatement(setStatus);
			ps.setInt(1, managerID);
			ps.setInt(2, action);
			ps.setInt(3, reimID);
			
			//store the modified offer row into result set.
			ResultSet rs = ps.executeQuery();
			
			if(rs != null) {
				success = true;
			} 
			
			//If the action is "Accept", then all other offers' status on this item will update to "Rejected". 
			//This item's owner and status column will also be updated.
			//Lastly, call the paymentDao method to create a new payment record for this item.
//			if(action == 2) {
//				//reject SQL that turn down all other offers.
//				String reject = "UPDATE shop_offer SET offer_status = 'Rejected' where item_id = ? and offer_status = 'Pending';";
//				PreparedStatement rejectOffer = conn.prepareStatement(reject);
//				
//				//SQL to change the ownership of item ID and customer ID.
//				String owner = "UPDATE shop_item SET item_owner_id = ?, item_status = 'Owned' WHERE item_id = ?;";
//				PreparedStatement ownerChange = conn.prepareStatement(owner);
//				
//				while(rs.next()) {
//					//complete the reject SQL statement.
//					rejectOffer.setInt(1, rs.getInt("item_id"));
//					
//					//complete the owner SQL statement.
//					ownerChange.setInt(1, rs.getInt("customer_id"));
//					ownerChange.setInt(2, rs.getInt("item_id"));
//					
//					//Fill a new payment object and call the createPayment method with this object.
//					Payment p = new Payment();
//					p.setItemID(rs.getInt("item_id"));
//					p.setPaymentAmount(rs.getInt("offer_amount"));
//					p.setCustomerID(rs.getInt("customer_id"));
//					p.setRemainPayment(rs.getInt("offer_amount"));
//					p.setPaymentStatus("Awaiting");
//					p.setRemainTerms(5);
//					
//					PaymentDAO pDao = new PaymentDAOImp();
//					pDao.createPayment(p);
//					
//					
//				}
				
//				//execute the reject SQL to turn down other offers.
//				rejectOffer.executeUpdate();
//				
//				//execute the reject SQL to change the owner ID of this item.
//				ownerChange.executeUpdate();

//			} 
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return success;
	}

}
