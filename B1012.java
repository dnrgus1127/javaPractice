package baekjoon;

import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ABC{

	public static void main(String[] args) throws Exception { 
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bfr.readLine());
		StringTokenizer strToken;
		int M;
		int N;
		int K;
		int[][] MN;
		int[] Print = new int[n];
		for (int i = 0; i < n; i++) {
			Print[i]=0;
			strToken = new StringTokenizer(bfr.readLine()," ");
			M = Integer.parseInt(strToken.nextToken());
			N = Integer.parseInt(strToken.nextToken());
			K = Integer.parseInt(strToken.nextToken());
			MN =new int[M+2][N+2];
			for(int j=0;j<K;j++) {
				strToken = new StringTokenizer(bfr.readLine()," ");
				int x =Integer.parseInt(strToken.nextToken());
				int y =Integer.parseInt(strToken.nextToken());
				MN[x+1][y+1] = 1;
			}
			for(int k =1;k<M+1;k++) {
				for(int l =1;l<N+1;l++) {
					if(MN[k][l] == 1) {
						Check(MN,k,l);
						Print[i] +=1;
					}
				}
			}
		}
		for(int i=0;i<n;i++) {
			System.out.println(Print[i]);
		}
	}
	public static void Check(int[][] MN,int x, int y) {
		if(MN[x][y] == 1) {
			MN[x][y] = 0;
			Check(MN,x+1,y);
			Check(MN,x-1,y);
			Check(MN,x,y+1);
			Check(MN,x,y-1);
		}
		
		
	}
	

}