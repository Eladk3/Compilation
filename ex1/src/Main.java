import java.io.*;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java_cup.runtime.Symbol;
   
public class Main
{
	static public void main(String argv[])
	{
		Lexer l;
		Symbol s;
		FileReader file_reader;
		PrintWriter file_writer = null;
		String inputFilename = argv[0];
		String outputFilename = argv[1];
		boolean isComment = false;
		String fieldName;

		try
		{
			/********************************/
			/* [1] Initialize a file reader */
			/********************************/
			file_reader = new FileReader(inputFilename);

			/********************************/
			/* [2] Initialize a file writer */
			/********************************/
			file_writer = new PrintWriter(outputFilename);
			
			/******************************/
			/* [3] Initialize a new lexer */
			/******************************/
			l = new Lexer(file_reader);

			/***********************/
			/* [4] Read next token */
			/***********************/
			s = l.next_token();

			/********************************/
			/* [5] Main reading tokens loop */
			/********************************/
			while (s.sym != TokenNames.EOF)
			{
				/************************/
				/* [6] Print to console */
				/************************/
				/* 
				df

				//*/
				Class<TokenNames> tokenNamesClass = TokenNames.class;

				Field[] fields = tokenNamesClass.getDeclaredFields();
				
				if (s.sym == TokenNames.START_COMMENT) isComment = true;
				if (s.sym == TokenNames.END_COMMENT) isComment = false;


				// if (!isComment && s.sym == TokenNames.ELSE)
				// 	file_writer.println("ERROR");
				// if (!isComment) {
				// file_writer.println(fields[s.sym].getName());
				if (fields[s.sym].getName() == "ERROR") {
					// file_writer.println(s.value);
					throw new Exception("Illegal characters");
				}
				fieldName = fields[s.sym].getName();
				if (!(fieldName == "START_COMMENT" || fieldName == "END_COMMENT")) {

					file_writer.print(fields[s.sym].getName());
					if (s.value != null) {
						if (fields[s.sym].getName() == "INT") {
							if (((String)s.value).charAt(0) == '0' && ((String)s.value).length() > 1) {
								throw new Exception("leading zeros in a non-zero number");
							}
							int val = Integer.valueOf((String)s.value);
							if (0 > val || val > 32767) {
								throw new Exception("integer too big");
							}
						}
						file_writer.print("(");
						if (s.value instanceof String && fields[s.sym].getName() == "STRING") {
							file_writer.print(s.value);
						} else {
							file_writer.print(s.value);
						}
						file_writer.print(")");
					}
					file_writer.print("[");
					file_writer.print(l.getLine());
					file_writer.print(",");
					file_writer.print(l.getTokenStartPosition());
					file_writer.print("]");
					
					file_writer.print("\n");
				}
				// file_writer.print("Start porision" + l.getTokenStartPosition() + "\n");
				/*********************/
				/* [7] Print to file */
				/*********************/
				// file_writer.print(l.getLine());
				// file_writer.print(": ");
				// file_writer.print(s.value);
				// file_writer.print("\n");
				// } 



				/***********************/
				/* [8] Read next token */
				/***********************/
				s = l.next_token();


			}
			
			if (isComment) throw new Exception("EOF inside a comment");

			/******************************/
			/* [9] Close lexer input file */
			/******************************/
			l.yyclose();

			/**************************/
			/* [10] Close output file */
			/**************************/
			file_writer.close();
    	}
			     
		catch (Exception e)
		{
			try {
				
				if (file_writer != null) {
					file_writer = new PrintWriter(outputFilename);
				}
				file_writer.print("ERROR");
				file_writer.close();
				// e.printStackTrace();

			}
			catch (Exception e1) {
				System.out.println("WTF??????????");
				e1.printStackTrace();
			}
		}
	}
}
