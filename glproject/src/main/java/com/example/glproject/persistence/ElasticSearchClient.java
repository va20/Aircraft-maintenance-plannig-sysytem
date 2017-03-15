package com.example.glproject.persistence;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class ElasticSearchClient {
	private static Client client;

	private ElasticSearchClient() {
		try {
			TransportAddress address = new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300);
			client = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(address);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	private static class ElasticSearchClientHolder {
		private static final ElasticSearchClient INSTANCE = new ElasticSearchClient();
	}

	public static Client getClient() {
		return client;
	}

	public static ElasticSearchClient getInstance() {
		return ElasticSearchClientHolder.INSTANCE;
	}
}
