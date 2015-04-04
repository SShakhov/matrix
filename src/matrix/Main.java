package matrix;

public class Main
{
	public static void main(String[] args)
	{
		Matrix m1 = new Matrix(2, 2);
		Matrix m2 = new Matrix(2, 2);
		
		int N = 1;
		
		for(int i = 0; i < 2; i++)
			for(int j = 0; j < 2; j++)
			{
				m1.setElement(i, j, N);
				m2.setElement(j, i, ++N);
			}
		
		System.out.println(m1 + "\n" + m2 + "\n");
		
		try
		{
			System.out.println(m1.multiply(m2));
		}
		catch(InterruptedException e)
		{
			System.out.println(e.getMessage());
		}
	}
}
