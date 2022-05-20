package com.cxy.redisclient.integration.zset;

import com.cxy.redisclient.domain.RedisVersion;
import com.cxy.redisclient.integration.ListPage;
import redis.clients.jedis.resps.Tuple;

import java.util.List;

public class ListZSetPage extends ListPage {
	private List<Tuple> page;

	public ListZSetPage(int id, int db, String key, int start, int end) {
		super(id, db, key, start, end);
	}

	@Override
	protected void command() {
		jedis.select(db);
		page = jedis.zrangeWithScores(key, start, end);
	}

	@Override
	public RedisVersion getSupportVersion() {
		return RedisVersion.REDIS_1_2;
	}

	public List<Tuple> getPage() {
		return page;
	}

}
