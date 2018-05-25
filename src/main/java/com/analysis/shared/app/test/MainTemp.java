package com.analysis.shared.app.test;

import java.util.HashMap;

public class MainTemp {

	public static void main(String[] args) {
		HashMap<Key,String> map =new HashMap<>();
		map.put(new Key(),"value1");
		map.put(new Key(),"value2");
		System.out.println(map.get(new Key()));
	}
}

class Demo{
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
}
class Key {  
public int hashcode() {
 return 1;
}
}