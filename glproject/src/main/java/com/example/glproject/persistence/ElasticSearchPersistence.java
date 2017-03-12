package com.example.glproject.persistence;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class ElasticSearchPersistence {
	private static Client client;

	private ElasticSearchPersistence() {
	}

	public static Client getInstance() {
		if (client == null) {
			try {
				TransportAddress address = new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300);
				client = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(address);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}

		return client;
	}
}
