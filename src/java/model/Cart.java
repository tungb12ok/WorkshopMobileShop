package model;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<String, Map<String, Integer>> items; // Change the key type to String (userId)

    public Cart() {
        this.items = new HashMap<>();
    }

    public void addItem(User user, Mobile mobile) {
        String userId = user.getUserId();
        items.computeIfAbsent(userId, k -> new HashMap<>());

        Map<String, Integer> userCart = items.get(userId);
        String mobileId = mobile.getMobileId();

        userCart.put(mobileId, userCart.getOrDefault(mobileId, 0) + 1);
    }

    public Map<String, Map<String, Integer>> getItems() {
        return items;
    }

    public int quantityOfMobile(String userId, Mobile mobile) {
        String mobileId = mobile.getMobileId();
        if (items.containsKey(userId) && items.get(userId).containsKey(mobileId)) {
            return items.get(userId).get(mobileId);
        }
        return 0;
    }

    public void removeItem(User user, String mobileId) {
        String userId = user.getUserId();
        Map<String, Integer> userCart = items.get(userId);
        if (userCart != null) {
            userCart.remove(mobileId);
        }
    }

}
