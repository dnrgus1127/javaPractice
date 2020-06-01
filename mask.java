package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 대경권 프로그래밍 대회
public class mask {
	public static int solution(int[][] atmos) {
		int awnser = 0;
		boolean[] newmask = new boolean[atmos.length];
		int count = 0;
		for (int i = 0; i < atmos.length; i++) {
			if (atmos[i][0] > 80 || atmos[i][1] > 35) {
				if (count == 0) {
					newmask[i] = true;
					count++;
				}
				if (atmos[i][0] > 80 && atmos[i][1] > 75) {
					count = 0;
				}
				if(count >= 3)
				{
					count =0;
					newmask[i] = true;
				}
			}
			else
			{
				count++;
			}
			
		}
		for(int i=0; i<newmask.length;i++)
		{
			if(newmask[i]==true)
			{
				awnser ++;
			}
		}
		return awnser;
	}

	public static void main(String[] args) throws Exception {
		int[][] atmos = {{140,90},{177,75},{95,45},{71,33},{151,76},{80,36}};
		System.out.println(solution(atmos));
		
	}
}
