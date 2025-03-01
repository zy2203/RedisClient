package com.cxy.redisclient.integration.list;

import com.cxy.redisclient.domain.RedisVersion;
import com.cxy.redisclient.integration.JedisCommand;
import redis.clients.jedis.args.ListPosition;

public class InsertList extends JedisCommand {
	private final int db;

	private final String key;

	private final boolean beforeAfter;

	private final String pivot;

	private final String value;

	public InsertList(int id, int db, String key, boolean beforeAfter, String pivot, String value) {
		super(id);
		this.db = db;
		this.key = key;
		this.beforeAfter = beforeAfter;
		this.pivot = pivot;
		this.value = value;
	}

	@Override
	protected void command() {
		jedis.select(db);
		jedis.linsert(key, beforeAfter ? ListPosition.BEFORE : ListPosition.AFTER, pivot, value);
	}

	@Override
	public RedisVersion getSupportVersion() {
		return RedisVersion.REDIS_2_2;
	}

}
