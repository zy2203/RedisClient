package com.cxy.redisclient.service;

import com.cxy.redisclient.integration.key.Expire;
import com.cxy.redisclient.integration.key.IsKeyExist;
import com.cxy.redisclient.integration.zset.*;
import redis.clients.jedis.resps.Tuple;

import java.util.List;
import java.util.Map;

public class ZSetService {
	public void add(int id, int db, String key, Map<String, Double> values, int ttl) {
		AddZSet command = (AddZSet) new AddZSetFactory(id, db, key, values).getCommand();
		command.execute();

		if(ttl != -1) {
			Expire command1 = new Expire(id, db, key, ttl);
			command1.execute();
		}
	}

	public List<Tuple> list(int id, int db, String key) {
		IsKeyExist command1 = new IsKeyExist(id, db, key);
		command1.execute();
		if(!command1.isExist()) {
			throw new KeyNotExistException(id, db, key);
		}

		ListZSet command = new ListZSet(id, db, key);
		command.execute();
		return command.getValues();
	}

	public List<Tuple> getPage(int id, int db, String key, int start, int end) {
		ListZSetPage command = new ListZSetPage(id, db, key, start, end);
		command.execute();
		return command.getPage();
	}

	public void addValues(int id, int db, String key, Map<String, Double> values) {
		AddZSet command = (AddZSet) new AddZSetFactory(id, db, key, values).getCommand();
		command.execute();
	}

	public void removeMembers(int id, int db, String key, String[] members) {
		RemoveMembers command = new RemoveMembers(id, db, key, members);
		command.execute();
	}
}
