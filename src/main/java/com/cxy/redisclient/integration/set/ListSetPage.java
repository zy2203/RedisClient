package com.cxy.redisclient.integration.set;

import com.cxy.redisclient.domain.RedisVersion;
import com.cxy.redisclient.integration.ListPage;
import redis.clients.jedis.params.SortingParams;

import java.util.List;

public class ListSetPage extends ListPage {
	private List<String> page;

	public List<String> getPage() {
		return page;
	}

	public ListSetPage(int id, int db, String key, int start, int end) {
		super(id, db, key, start, end);
	}

	@Override
	protected void command() {
		jedis.select(db);
		SortingParams sp = new SortingParams();
		sp.alpha();
		sp.limit(start, end - start);
		page = jedis.sort(key, sp);

	}

	@Override
	public RedisVersion getSupportVersion() {
		return RedisVersion.REDIS_1_0;
	}
}
