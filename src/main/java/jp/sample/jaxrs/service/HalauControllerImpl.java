package jp.sample.jaxrs.service;

import jp.sample.jaxrs.dao.PostgresqlConnector;
import jp.sample.jaxrs.domain.Item;

public class HalauControllerImpl implements HalauController {

    @Override
    public void putRecord(Item item) throws Exception {
    	PostgresqlConnector pc = new PostgresqlConnector();
    	int rs = pc.putRecord(item.getPrice(), item.getItemname());
    	if(rs != 1){
    		throw new Exception("Putting failed.");
    	}
        return;
    }
    
    @Override
    public Item[] pullRecords() {
    	PostgresqlConnector pc = new PostgresqlConnector();
        return pc.pullRecords();
    }
    
    @Override
    public String sayHello() {
        return "say, Hello";
    }
    
}