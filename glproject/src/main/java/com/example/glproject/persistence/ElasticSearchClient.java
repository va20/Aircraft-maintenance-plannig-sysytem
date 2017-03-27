package com.example.glproject.persistence;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ElasticSearchClient {
	private Client client;

	private ElasticSearchClient() {
		try {
			// TODO Add a configuration file
			TransportAddress address = new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300);
			client = new PreBuiltTransportClient(Settings.builder().put("cluster.name", "elasticsearch").build())
					.addTransportAddress(address);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	private static class ElasticSearchClientHolder {
		private static final ElasticSearchClient INSTANCE = new ElasticSearchClient();
	}

	public Client getClient() {
		return client;
	}

	public static ElasticSearchClient getInstance() {
		return ElasticSearchClientHolder.INSTANCE;
	}
}
