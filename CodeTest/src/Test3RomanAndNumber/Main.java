package Test3RomanAndNumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


public class Main
{
	private final static String REG_EXPR = "^[\\-][0-9]{1,}$|^[0-9]{1,}$";
	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub
		Map<String, Double> price = new HashMap<String, Double>();
		Main m = new Main();
		m.entryPoint(price);
	}
	
	/**
	 * Entry point of the service
	 * @param price
	 * @throws IOException
	 */
	public void entryPoint(Map<String, Double> price) throws IOException
	{
		System.out.println("Welcome to translation center (Type e/exit to leave center)");
		try (Reader in = new InputStreamReader(System.in);
		    BufferedReader reader = new BufferedReader(in))
		{
			String sentence = "";
			while (true)
			{
				System.out.print("> ");
				sentence = reader.readLine();
				if (null == sentence || "e".equalsIgnoreCase(sentence)
				    || "exit".equalsIgnoreCase(sentence))
				{
					break;
				}
				
				String[] words = sentence.split(" ");
				String credit = words[words.length-1];
				Pattern pattern = Pattern.compile(REG_EXPR);
				//Need to type orginal credit first, and then type the one we need to calculate
				if (pattern.matcher(credit).matches())
				{
					String[] temp = new String[words.length -1 ];
		    	System.arraycopy(words, 0, temp, 0, words.length-1);
		    	price = getCredit(temp, Integer.valueOf(credit), price);
		    	System.out.println("Please enter the items which we want to calculate credits");
		    	System.out.print("> ");
					sentence = reader.readLine();
					temp = sentence.split(" ");
		    	System.out.println(calculateCreditOrNumber(price, temp));
				}
				else
				{
					System.out.println(calculateCreditOrNumber(null, words));
				}
				
			}
		}
	}
	
	/**
	 * get the credit of Silver, Gold and Iron
	 * @param words
	 * @param credit
	 * @param price
	 * @return
	 */
	public Map<String, Double> getCredit(String[] words, int credit, Map<String, Double> price)
	{
		String[] translated = translate(words);
		int number = romanToNum(translated[0]);
		price.put(translated[1], ((double)credit/number));
		return price;
	}
	
	/**
	 * Translate from glob, prok, pish and tegj to Roman
	 * @param words
	 * @return
	 */
	public String[] translate(String[] words)
	{
		String roman = "";
		String[] translate = new String[2];
		for(String word : words)
		{
			switch(word)
			{
				case "glob" :
					roman += "I";
					break;
				case "prok" :
					roman += "V";
					break;
				case "pish" :
					roman += "X";
					break;
				case "tegj" :
					roman += "L";
					break;
				default :
					translate[1] = word;
			}
		}
		translate[0] = roman;
		return translate;
	}
	
	/**
	 * Get number based on Roman or get credit from map
	 * @param price
	 * @param words
	 * @return
	 */
	public int calculateCreditOrNumber(Map<String, Double> price, String[] words)
	{
		String[] translated = translate(words);
		int number = romanToNum(translated[0]);
		Double credit = 0.0;
		if(price == null)
		{
			return number;
		}
		else
		{
			credit = price.get(translated[1]);
			return (int)(number * credit);
		}
		
	}
	
	/**
	 * Translate from digits to Roman
	 * @param number
	 * @return
	 */
	public String numToRoman(int number)
	{
		String roman = "";
		int temp = number;
		String[] symbols = { "M", "CM", "D", "CD", "C", 
				"XC", "L", "XL", "X", "IX", "V", "IV", "I" };
		int[] numbers = { 1000, 900, 500, 400, 100, 
				90, 50, 40, 10, 9, 5, 4, 1 };
		for (int i = 0; temp != 0; i++) {
			while (temp >= numbers[i]) {
				temp -= numbers[i];
				roman += symbols[i];
			}
		}
		return roman;
	}
	
	/**
	 * Translate from Roman to digits
	 * @param roman
	 * @return
	 */
	public int romanToNum(String roman)
	{
		int[] numbers = new int[roman.length()];
		
		for(int i=0;i<numbers.length;i++)
		{
			switch(roman.charAt(i))
			{
				case 'M' :
					numbers[i] = 1000;
					break;
				case 'D' :
					numbers[i] = 500;
					break;
				case 'C' :
					numbers[i] = 100;
					break;
				case 'L' :
					numbers[i] = 50;
					break;
				case 'X' :
					numbers[i] = 10;
					break;
				case 'V' :
					numbers[i] = 5;
					break;
				case 'I' :
					numbers[i] = 1;
					break;
				default :
			}
		}
		
		int total = 0;
		for(int i=0; i<numbers.length-1; i++)
		{
			if(numbers[i]<numbers[i+1])
			{
				total-=numbers[i];
			}
			else
			{
				total+=numbers[i];
			}
		}
		return total+numbers[numbers.length - 1];
	}

}
