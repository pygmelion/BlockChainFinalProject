
public class Account {
	
	String[] usernames = {"CarFactory", "Bank", "LuntaiFactory", "LunguFactory"}, passwords = {"carfactory", "bank", "luntaifactory", "lungufactory"};

	public boolean check_login(String un, String pw) {
		for(int i = 0; i < this.usernames.length; i++) {
			if(this.usernames[i].equals(un) && this.passwords[i].equals(pw)) {
				return true;
			}
		}
		return false;
	}
}
