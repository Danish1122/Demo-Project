package com.ecommerce.bakerymanagement.controller;

import java.io.IOException;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.FailureMode;
import net.spy.memcached.MemcachedClient;

public class MemcachedJava {
	
	   public static void main(String[] args) throws IOException {
		   
		   String address = "127.0.0.1:11211";
		   
	      
	      // Connecting to Memcached server on localhost
	      MemcachedClient mcc = new MemcachedClient(new ConnectionFactoryBuilder().setDaemon(true).setFailureMode(FailureMode.Retry).build(), AddrUtil.getAddresses(address));
	      System.out.println("Connection to server sucessfully");
	      System.out.println("set status:"+mcc.set("tutorialspoint", 900, "memcached").isDone());
	      
	      // Get value from cache
	      System.out.println("Get from Cache:"+mcc.get("tutorialspoint"));
	   }
	}