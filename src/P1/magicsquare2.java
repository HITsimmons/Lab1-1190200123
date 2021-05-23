package P1;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class magicsquare2 {
    public static boolean isLegalMagicSquare(String fileName) 
    {
		try (Scanner file = new Scanner(new FileInputStream("src/P1/txt/"+fileName));) 
		{
			Vector<Vector<Integer>> square = new Vector<Vector<Integer>>();
			
			while (file.hasNext()) 
			{
				String sb = file.nextLine();
				String[] line = sb.trim().split("\t");
				Vector <Integer> row = new Vector <Integer> ();
				if (!square.isEmpty() && (line.length != square.get(0).size())) {
					System.out.println("���Ƿ��");
					return false;
				}
				int length1 = line.length;
				for (int i=0; i<length1; ++i) {
					String x = line[i];
					for (int j=0; j<x.length(); ++j) {
						if (!Character.isDigit(x.charAt(j))) {
							System.out.println("������������");
							return false;
						}
					}
					row.add(Integer.parseInt(x));
				}
				square.add(row);
			}
			int n = square.size();
			int[] sum1=new int[n];
		    int[] sum2=new int[n];
		    int[] sum3=new int[2];
		    sum3[0]=0;sum3[1]=0;
			int temp1,temp2,temp3;
		    for(int i=0;i<n;i++) {
		    	sum1[i]=0;
		    }
		    for(int j=0;j<n;j++) {
		    	sum2[j]=0;
		    }
		    for(int i=0;i<n;i++) {
		    	for(int j=0;j<n;j++) {
		    		sum1[i]=sum1[i]+square.get(i).get(j);
		    	}
		    }
		    for(int i=0;i<n;i++) {
		    	for(int j=0;j<n;j++) {
		    		sum2[i]=sum2[i]+square.get(j).get(i);
		    	}
		    }
		    for(int i=0;i<n;i++) {
		    	sum3[0]=sum3[0]+square.get(i).get(i);
		    	sum3[1]=sum3[1]+square.get(i).get(i);
		    	}
		    //�ж��Ƿ�����MagicSquare������
		    temp1=sum1[0];temp2=sum2[0];temp3=sum3[0];
		    if(temp1!=temp2||temp1!=temp3||temp2!=temp3) {
		    	return false;
		    }
		    else {
		    	for(int i=1;i<n;i++) {
			    	if(sum1[i]!=temp1) {
			    		return false;
			    	}
		    	}
		    	for(int i=1;i<n;i++) {
		    		if(sum2[i]!=temp2) {
		    			return false;
		    		}
		    	}
		    	if(sum3[1]!=sum3[0]) {
		    		return false;
		    	}	    	
		    }
			
		} catch (FileNotFoundException e) {
			System.out.println("file not found.");
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
    public static boolean generateMagicSquare(int n) 
    {
		 if (n % 2 == 0 || n <= 0) {   //�ж������n�Ƿ�Ϊ�����������򷵻�false
	            return false;
	        }
		 int magic[][] = new int[n][n];
		 int row = 0, col = n / 2, i, j, square = n * n;
		 for (i = 1; i <= square; i++) {  //����������MagicSquare
		 magic[row][col] = i;
		 if (i % n == 0)    //б����
		 row++;
		 else {
		 if (row == 0)    //�ϱ�����������
		 row = n - 1;
		 else
		 row--;
		 if (col == (n - 1))   //�ұ�����������
		 col = 0;
		 else
		 col++;
		 }
		 }
		 for (i = 0; i < n; i++) {              
		 for (j = 0; j < n; j++)
		    System.out.print(magic[i][j] + "\t");   //���MagicSquare
	     System.out.println();
		 }	
		 FileWriter writefile = null;
	     BufferedWriter mywriter = null;
	     try {
	    	    writefile = new FileWriter("src/P1/txt/6.txt");
	            mywriter = new BufferedWriter(writefile);
	        } catch (Exception e) {
	        	 System.out.println("д�����1");
	        	 e.printStackTrace();
	             return false;
	        }
	     return true;
	     }
    public static void main(String[] args) {
        magicsquare2 ms = new magicsquare2();
        
        for(int i=1;i<=5;i++) {
            boolean flag=ms.isLegalMagicSquare(i+".txt");
            System.out.println(i+".txt: "+flag);
        }
        Scanner in=new Scanner(System.in);
		System.out.println("����MagicSquare���L����");
		int n=in.nextInt();
		if(n%2==0) {
			System.out.println("��ż��,����ֵΪ"+MagicSquare.generateMagicSquare(n)+"\n");
		}
		else if(n<0) {
			System.out.println("�Ǹ���,����ֵΪ"+MagicSquare.generateMagicSquare(n)+"\n");
		}
		else {
			System.out.println("MagicSquare����");
			MagicSquare.generateMagicSquare(n);
			System.out.println("ԓMagicSquare�Ƿ��÷���");
			System.out.println(MagicSquare.isLegalMagicSquare("src/P1/txt/6.txt")+"\n");
		}
    }

}
