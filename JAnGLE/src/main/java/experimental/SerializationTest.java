package experimental;

import java.io.Serializable;
import java.util.Arrays;

import org.apache.commons.lang3.SerializationUtils;

public class SerializationTest {
	
	public static class Test1 implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private int number;
		private boolean bool;
		private double dubs;
		public int getNumber() {
			return number;
		}
		public void setNumber(int number) {
			this.number = number;
		}
		public boolean isBool() {
			return bool;
		}
		public void setBool(boolean bool) {
			this.bool = bool;
		}
		public double getDubs() {
			return dubs;
		}
		public void setDubs(double dubs) {
			this.dubs = dubs;
		}
		
	}
	
	public static void main(String[] args){
		Test1 test1 = new Test1();
		test1.setNumber(53);
		test1.setBool(true);
		test1.setDubs(78.32);
		byte[] serialized1 = SerializationUtils.serialize(test1);
		Test1 test2 = SerializationUtils.clone(test1);
		byte[] serialized2 = SerializationUtils.serialize(test2);
		System.out.println(Arrays.equals(serialized1, serialized2));
		System.out.println(test1==test2);
	}

}
