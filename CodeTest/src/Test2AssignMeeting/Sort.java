package Test2AssignMeeting;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Sort
{

	public static void main(String[] args)
	{
		Sort s = new Sort();
		int[] time = {60, 45, 30, 45, 45, 5, 60, 45, 30, 30, 45, 60, 60, 45,
		    30, 30, 60, 30, 30 };
		String[] name = {"Writing Fast Tests Against Enterprise Rails",
		    "Overdoing it in Python", "Lua for the Masses",
		    "Ruby Errors from Mismatched Gem Versions", "Common Ruby Errors",
		    "Rails for Python Developers", "Communicating Over Distance",
		    "Accounting-Driven Development", "Woah", "Sit Down and Write",
		    "Pair Programming vs Noise", "Rails Magic",
		    "Ruby on Rails: Why We Should Move On",
		    "Clojure Ate Scala (on my project)",
		    "Programming in the Boondocks of Seattle",
		    "Ruby vs. Clojure for Back-End Development",
		    "Ruby on Rails Legacy App Maintenance", "A World Without HackerNews",
		    "User Interface CSS in Rails Apps" };

		int[] total_time = { 180, 240 };
		int n = time.length;
		s.SortByendTime(time, name, n, total_time);
	}

	public void SortByendTime(int[] time, String[] name, int n, int[] left)
	{
		sort(time, name, n);
		int task = 0;
		int time_left = 0;
		Calendar calendar = new GregorianCalendar(2018, 9, 21, 9, 0, 0);
		for (int i = 0; i < left.length; i++)
		{
			String cuurent_time = "";
			time_left = left[i];
			for (; task <n; task++)
			{
				if (time[task] <= time_left)
				{
					time_left -= time[task];
					java.text.Format formatter3 = new java.text.SimpleDateFormat("HH:mm");
					System.out
					    .println(formatter3.format(calendar.getTime()) + " " + name[task]);
					calendar.add(Calendar.MINUTE, time[task]);
					cuurent_time = formatter3.format(calendar.getTime());
				}
				else if (time_left == 0)
				{
					break;
				}
			}
			if (cuurent_time.equals("12:00"))
			{
				System.out.println(cuurent_time + " Lunch");
				calendar.add(Calendar.MINUTE, time[task]);
			}
			else
				System.out.println("17:00 Networking Event");
		}
	}

	public void sort(int[] time, String[] name, int n)
	{
		for (int i = 1; i < time.length; i++)
		{
			for (int j = 1; j < time.length - i; j++)
			{
				if (time[j] < time[j + 1])
				{
					int temp = time[j];
					time[j] = time[j + 1];
					time[j + 1] = temp;

					String np = name[j];
					name[j] = name[j + 1];
					name[j + 1] = np;
				}
			}
		}
	}
}
