
public class Account {
	public static int[] sort(int[] ori) {
		for (int i = 0; i < ori.length; i++) {
			for (int j = i; j < ori.length; j++) {
				if (ori[i] > ori[j]) {
					int temp = ori[i];
					ori[i] = ori[j];
					ori[j] = temp;
				}
			}
		}
		return ori;
	}
    public double sort_account_list_for_after(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
		int len2 = nums2.length;
		int sumLen = len1 + len2;
		int[] sumArray = new int[sumLen];
		for (int i = 0; i < len1; i++) {
			sumArray[i] = nums1[i];
		}
		for (int j = 0; j < len2; j++) {
			sumArray[j + len1] = nums2[j];
		}
		for (int i = 0; i < sumArray.length; i++) {
			System.out.print(sumArray[i]);
		}
		int[] sortedArray = sort(sumArray);
		if (sumLen%2==0) {
			return (sortedArray[(sumLen/2)-1]+sortedArray[(sumLen/2)])/2.0;
		}
		else{
			return sortedArray[(sumLen/2)];
		}
    }
	String[] usernames = {"CarFactory", "Bank", "LuntaiFactory", "LunguFactory"}, passwords = {"carfactory", "bank", "luntaifactory", "lungufactory"};

	public boolean check_login(String un, String pw) {
		for(int i = 0; i < this.usernames.length; i++) {
			if(this.usernames[i].equals(un) && this.passwords[i].equals(pw)) {
				return true;
			}
		}
		return false;
	}
	boolean in_database(int mapS[], int mapT[]) {// Runtime = O(256) = O(1)
	    for (int i = 0; i < mapT.length; i++) {// s should cover all characters in t
	        if (mapT[i] > mapS[i])
	            return false; 
	    }           
	    return true;
	}

	public String slide_over_database(String s, String t) {   
	    int mapS[] = new int[256];// Count characters in s
	    int mapT[] = new int[256];// Count characters in t      
	    for (char ch : t.toCharArray())
	        mapT[ch]++;

	    String res = "";
	    int right = 0, min = Integer.MAX_VALUE;         
	    for (int i = 0; i < s.length(); i++) {// Two pointers of the sliding window: i(left), right
	        while (right < s.length() && !in_database(mapS, mapT)) {// Extend the right pointer of the sliding window
	            mapS[s.charAt(right)]++;
	            right++;
	        }
	        if (in_database(mapS, mapT) && min > right - i + 1) {
	            res = s.substring(i, right);
	            min = right - i + 1;
	        }
	        mapS[s.charAt(i)]--;// Shrink the left pointer from i to i + 1
	    }
	    return res;
	}
}
