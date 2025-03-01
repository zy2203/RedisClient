package com.cxy.redisclient.integration.key;

import com.cxy.redisclient.domain.NodeType;
import com.cxy.redisclient.domain.RedisVersion;
import redis.clients.jedis.params.ScanParams;
import redis.clients.jedis.resps.ScanResult;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static redis.clients.jedis.params.ScanParams.SCAN_POINTER_START;

public class FindContainerKeys28 extends FindContainerKeys {

	public FindContainerKeys28(int id, int db, String container, String keyPattern) {
		super(id, db, container, keyPattern);
	}

	public FindContainerKeys28(int id, int db, String container, String keyPattern, List<NodeType> valueTypes,
			boolean forward) {
		super(id, db, container, keyPattern, valueTypes, forward);
	}

	protected Set<String> getResult() {
		Set<String> nodekeys = new HashSet<>();
		assert (container != null);

		ScanParams params = new ScanParams();
		params.match(container + keyPattern);
		ScanResult<String> result;
		String cursor = SCAN_POINTER_START;
		do {
			result = jedis.scan(cursor, params);
			nodekeys.addAll(result.getResult());
			cursor = result.getCursor();
		} while (!result.getCursor().equals(SCAN_POINTER_START));

		return nodekeys;
	}

	@Override
	public RedisVersion getSupportVersion() {
		return RedisVersion.REDIS_2_8;
	}

}
