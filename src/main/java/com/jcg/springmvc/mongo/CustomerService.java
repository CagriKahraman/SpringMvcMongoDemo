package com.jcg.springmvc.mongo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcg.springmvc.mongo.factory.MongoFactory;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Service("customerService")
@Transactional
public class CustomerService {

	static String db_name = "CustomerDB", db_collection = "CustomerCN";
	private static Logger log = Logger.getLogger(CustomerService.class);

	public List<Customer> getAll() {
		List<Customer> customer_list = new ArrayList<Customer>();
		DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

		DBCursor cursor = coll.find();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();

			Customer customer = new Customer();
			customer.setId(dbObject.get("_id").toString());
			customer.setName(dbObject.get("Name").toString());
			customer.setLastName(dbObject.get("LastName").toString());
			customer.setPhone(dbObject.get("Phone").toString());

			customer_list.add(customer);
		}
		log.debug("Total records fetched from the mongo database are= " + customer_list.size());
		return customer_list;
	}

	public Boolean add(Customer customer) {
		boolean output = false;
		Random ran = new Random();
		log.debug("Adding a new user to the mongo database; Entered customer_name is= " + customer.getName());
		try {
			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			BasicDBObject doc = new BasicDBObject();
			doc.put("_id", String.valueOf(ran.nextInt(100)));
			doc.put("Name", customer.getName());
			doc.put("LastName", customer.getLastName());
			doc.put("Phone", customer.getPhone());

			coll.insert(doc);
			output = true;
		} catch (Exception e) {
			output = false;
			log.error("An error occurred while saving a new user to the mongo database", e);
		}
		return output;
	}

	public Boolean edit(Customer customer) {
		boolean output = false;
		log.debug("Updating the existing user in the mongo database; Entered user_id is= " + customer.getId());
		try {

			BasicDBObject existing = (BasicDBObject) getDBObject(customer.getId());

			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			BasicDBObject edited = new BasicDBObject();
			edited.put("Name", customer.getName());
			edited.put("LastName", customer.getLastName());
			edited.put("Phone", customer.getPhone());

			coll.update(existing, edited);
			output = true;
		} catch (Exception e) {
			output = false;
			log.error("An error has occurred while updating an existing user to the mongo database", e);
		}
		return output;
	}

	public Boolean delete(String id) {
		boolean output = false;
		log.debug("Deleting an existing user from the mongo database; Entered user_id is= " + id);
		try {

			BasicDBObject item = (BasicDBObject) getDBObject(id);

			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			coll.remove(item);
			output = true;
		} catch (Exception e) {
			output = false;
			log.error("An error occurred while deleting an existing user from the mongo database", e);
		}
		return output;
	}

	private DBObject getDBObject(String id) {
		DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

		DBObject where_query = new BasicDBObject();

		where_query.put("_id", id);
		return coll.findOne(where_query);
	}

	public Customer findCustomerId(String id) {
		Customer customer = new Customer();
		DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

		DBObject where_query = new BasicDBObject();
		where_query.put("_id", id);

		DBObject dbo = coll.findOne(where_query);
		customer.setId(dbo.get("_id").toString());
		customer.setName(dbo.get("Name").toString());
		customer.setLastName(dbo.get("LastName").toString());
		customer.setPhone(dbo.get("Phone").toString());

		return customer;
	}
}