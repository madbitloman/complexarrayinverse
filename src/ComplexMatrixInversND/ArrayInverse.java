package ComplexMatrixInversND;
import flanagan.complex.Complex;
import flanagan.complex.ComplexMatrix;
 
public class ArrayInverse{
	  
	public static int [] genind(int m_0,int m_1,int m_0s,int m_1s, int a1 ){

	int x=1;
	int max=m_0*m_1*a1*m_0s*m_1s;
	int Y[] = new int[max];
	
		for(int k=0;k<m_0;k++){
			for(int l=0;l<m_1;l++){
				for(int ks=0;ks<m_0s;ks++){
					for(int ls=0;ls<m_1s;ls++){
						for(int a=0;a<a1;a++){
							Y[x-1] = (a + 1) + 100 * (ls + 1) + 1000 * (ks + 1) + 10000 * (l + 1)+100000 * (k + 1);
							x++;
					 	}
					}
				}	
			}	
		}
		
		return Y;
		
		}
	
	
	
	
	public static int index(int m_0,int m_1,int m_0s,int m_1s,int a1, int Y[]){
		int index=0;
	
		
		for(int i=0;i<Y.length;i++){
			if (Y[i]==(a1+1)+100*(m_1s+1)+1000*(m_0s+1)+10000*(m_1+1)+100000*(m_0+1))	
				index=i;	
		}
			
		return index;
		
		}
	


	public static int m1sindexback( int k, int Y[]){
		int m1s=0;

		m1s=(int) Math.floor((Y[k]-(Math.floor(Y[k]/1000)*1000))/100);
		
		return m1s;
		
	}
	
	public static int m1indexback( int k, int Y[]){
		int m1=0;
	
		m1=(int) Math.floor((Y[k]-(Math.floor(Y[k]/100000)*100000))/10000);

		return m1;
		
	}
	
	public static int m0sindexback( int k, int Y[]){
		int m0s=0;
		
		m0s=(int) (Math.floor(Y[k]/1000-Math.floor(Y[k]/100000)*100)-Math.floor((Y[k]/1000-Math.floor(Y[k]/100000)*100)/10)*10);
		
		return m0s;
		
	}
	
	public static int m0indexback( int k, int Y[]){
		int m0=0;
			
		m0=(int) Math.floor(Y[k]/100000);
		
		return m0;
		
	}
	
	public static int a1indexback(int k, int Y[]){
		int a1=0;
		
		a1=(int)(Math.floor((Math.floor((Y[k]-(Math.floor(Y[k]/1000)*1000))))-Math.floor((Math.floor((Y[k]-(Math.floor(Y[k]/1000)*1000))))/1000)*1000)-Math.floor(((Math.floor((Y[k]-(Math.floor(Y[k]/1000)*1000))))-Math.floor((Math.floor((Y[k]-(Math.floor(Y[k]/1000)*1000))))/1000)*1000)/100)*100);
		
		return a1;
	
	}

	
	public static Complex[][] Transition(int Y[],int max,Complex hamatrix[][][][][][][][][][]){
		Complex trans[][]= new Complex[max][max];
		
		for (int i=0; i<hamatrix[0].length; i++){
			for (int j=0; j<hamatrix[0][1].length; j++){
				for (int k=0; k<hamatrix[0][1][2].length; k++ ){
					for (int l=0; l<hamatrix[0][1][2][3].length; l++ ){
						for (int m=0; m<hamatrix[0][1][2][3][4].length; m++){
							for(int n=0;n<hamatrix[0][1][2][3][4][5].length; n++){
								for (int ks=0; ks<hamatrix[0][1][2][3][4][5][6].length; ks++ ){
									for (int ls=0; ls<hamatrix[0][1][2][3][4][5][6][7].length; ls++ ){
										for (int ms=0; ms<hamatrix[0][1][2][3][4][5][6][7][8].length; ms++){
											for(int ns=0;ns<hamatrix[0][1][2][3][4][5][6][7][8][9].length; ns++){
					
					 
													trans[index(k,l,ks,ls,i,Y)][index(m,n,ms,ns,j,Y)]=hamatrix[i][j][k][l][m][n][ks][ls][ms][ns];
					

													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
		return trans;
	
	}
	
	
	public static Complex[][] inversion(Complex trans[][]){
		ComplexMatrix inversm = new ComplexMatrix(trans[0].length,trans[1].length);
		ComplexMatrix comp1= new ComplexMatrix(trans);
    	inversm=comp1.inverse();
    	
    	for(int k=0; k<trans[0].length;k++){
			for(int l=0; l<trans[0].length; l++){
					trans[k][l]=inversm.getElementReference(k,l);		
			}
		}
    	
    	return trans;
    	
	}
	

	public static Object[][][][][][][][][][] matrixturn(Complex[][][][][][][][][][] hamatrixnew){
		
		final int a_1=hamatrixnew[0].length;//a stochastic indices 
		final int b_1=hamatrixnew[1].length;//b stochastic indices
		final int m_0I=hamatrixnew[2].length;
		final int m_0S=hamatrixnew[3].length;
		final int m_1I=hamatrixnew[4].length;
		final int m_00I=hamatrixnew[5].length;
		final int m_11I=hamatrixnew[6].length;
		final int m_1S=hamatrixnew[7].length;
		final int m_00S=hamatrixnew[8].length;
		final int m_11S=hamatrixnew[9].length;
		final int max=m_0I*m_1I*a_1*m_0S*m_1S; //matrix dimension
		Complex[][][][][][][][][][] hamatrixnewer= new Complex[a_1][b_1][m_0I][m_1I][m_00I][m_11I][m_0S][m_1S][m_00S][m_11S];
		int Y[]=ArrayInverse.genind(m_0I, m_1I, m_0S, m_1S, a_1);
		Complex[][] trans= ArrayInverse.Transition(Y, max, hamatrixnew);
		Complex[][] transcompl = ArrayInverse.inversion(trans);
		
		for(int k=0; k<max;k++){
			for(int l=0; l<max; l++){
				hamatrixnewer[a1indexback(k,Y)-1][a1indexback(l,Y)-1][m0indexback(k,Y)-1][m1indexback(k,Y)-1][m0indexback(l,Y)-1][m1indexback(l,Y)-1][m0sindexback(k,Y)-1][m1sindexback(k,Y)-1][m0sindexback(l,Y)-1][m1sindexback(l,Y)-1]=transcompl[k][l];	
			}
		}
    	
		return hamatrixnew;
	}
	

	
			
}	
		
	

	


