package util;

public class constant {
	 public static enum ERole {
		USER(1), ADMIN(2);
		private int value;
		private ERole(int _value) {
			this.value = _value;
		}
		public int getValue() {
			return this.value;
		}
	}
	 
	 public static enum ESession {
		MEMBERID, MEMBERNAME, MEMBERROLE
	}
}