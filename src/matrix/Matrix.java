package matrix;

import java.util.ArrayList;

public class Matrix
{
	private int rows;
	private int columns;
	
	private double[][] matrix;
	
	public Matrix(int rows, int columns)
	{
		setRows(rows);
		setColumns(columns);
		
		matrix = new double[rows][columns];
	}
	
	public int getRows()
	{
		return rows;
	}
	
	public int getColumns()
	{
		return columns;
	}
	
	public void setRows(int rows)
	{
		//check
		this.rows = rows;
	}
	
	public void setColumns(int columns)
	{
		//check
		this.columns = columns;
	}
	
	public double getElement(int i, int j)
	{
		//check
		return matrix[i][j];
	}
	
	public void setElement(int i, int j, double value)
	{
		//check
		matrix[i][j] = value;
	}
	
	public Matrix multiply(Matrix a) throws InterruptedException
	{
		//check if multipliable
		Matrix result = new Matrix(rows, a.getColumns());
		int threadNum = 0;
		
		class MultThread extends Thread
		{
			public int i, j;
			public Matrix a, result;
			
			public MultThread(int i, int j, Matrix a, Matrix result)
			{
				this.i = i;
				this.j = j;
				this.a = a;
				this.result = result;
			}
			
			@Override
			public void run()
			{
				double element = 0;
				for(int m = 0; m < columns; m++)
					element += matrix[i][m] * a.getElement(m, j);
				
				result.setElement(i, j, element);
			}
		}
	
		ArrayList<MultThread> thr = new ArrayList<MultThread>();
		
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < a.getColumns(); j++)
			{
				threadNum++;
				thr.add(new MultThread(i, j, a, result));
				thr.get(threadNum - 1).start();
			}
		
		for(int wait = 0; wait < threadNum; wait++)
			thr.get(wait).join();
		
		return result;
	}
	
	@Override
	public String toString()
	{
		String str = "";
		
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < columns; j++)
				str += Double.toString(matrix[i][j]) + "\t";
			
			str.trim();
			str += "\n";
		}
				
		return str.trim();
	}
}
