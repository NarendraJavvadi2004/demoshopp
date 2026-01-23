// CartPayload.java
package api.payloads;
import java.util.List;
public class CartPayload {
	
	private int userId;
    private List<Integer> productIds;
    
    // getters & setters
    public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public List<Integer> getProductIds() {
		return productIds;
	}
	public void setProductIds(List<Integer> productIds) {
		this.productIds = productIds;
	}
	
   
}

