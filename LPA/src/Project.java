import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.*;

public class Project
{

	public Project()
	{
	}

	public static void main(String args[])
	{
		boolean flag = false;
		String s = "";
		ArrayList arraylist = new ArrayList();
		ArrayList arraylist1 = new ArrayList();
		ArrayList arraylist2 = new ArrayList();
		ArrayList arraylist3 = new ArrayList();
		ArrayList arraylist4 = new ArrayList();
		ArrayList arraylist5 = new ArrayList();
		ArrayList arraylist6 = new ArrayList();
		ArrayList arraylist7 = new ArrayList();
		int i = Array.getLength(args);
		if (i < 1)
			printUsageAndExit();
		String s2 = args[0];
		int j = 1;
		do
		{
			if (j >= i)
				break;
			String s1 = args[j++];
			if (s1.equals("-w"))
				flag = true;
			if (s1.equals("-s"))
				s = args[j++];
		} while (true);
		String s3 = s2.substring(0, s2.lastIndexOf('.'));
		String s4 = (new StringBuilder()).append(s3).append("-1").append(s).append(".txt").toString();
		String s5 = (new StringBuilder()).append(s3).append("-2").append(s).append(".txt").toString();
		LPAOC.readBiGraphEdges(s2, arraylist, arraylist1, arraylist6, arraylist7, null);
		if (flag)
		{
			List list2 = weightedProject(arraylist, arraylist1);
			List list3 = weightedProject(arraylist1, arraylist);
			writeWeightedEdges(s4, list2, arraylist6);
			writeWeightedEdges(s5, list3, arraylist7);
		} else
		{
			List list = project(arraylist, arraylist1);
			List list1 = project(arraylist1, arraylist);
			writeGraphEdges(s4, list, arraylist6);
			writeGraphEdges(s5, list1, arraylist7);
		}
	}

	private static List project(List list, List list1)
	{
		ArrayList arraylist = new ArrayList();
		for (int i = 0; i < list.size(); i++)
		{
			HashSet hashset = new HashSet();
			for (Iterator iterator = ((HashSet)list.get(i)).iterator(); iterator.hasNext(); hashset.addAll((Collection)list1.get(((Integer)iterator.next()).intValue())));
			hashset.remove(Integer.valueOf(i));
			arraylist.add(hashset);
		}

		return arraylist;
	}

	private static List weightedProject(List list, List list1)
	{
		ArrayList arraylist = new ArrayList();
		for (int i = 0; i < list.size(); i++)
		{
			HashMap hashmap = new HashMap();
			for (Iterator iterator = ((HashSet)list.get(i)).iterator(); iterator.hasNext();)
			{
				Iterator iterator1 = ((HashSet)list1.get(((Integer)iterator.next()).intValue())).iterator();
				while (iterator1.hasNext()) 
				{
					int j = ((Integer)iterator1.next()).intValue();
					if (hashmap.containsKey(Integer.valueOf(j)))
						hashmap.put(Integer.valueOf(j), Integer.valueOf(((Integer)hashmap.get(Integer.valueOf(j))).intValue() + 1));
					else
						hashmap.put(Integer.valueOf(j), Integer.valueOf(1));
				}
			}

			arraylist.add(hashmap);
		}

		return arraylist;
	}

	private static void writeGraphEdges(String s, List list, List list1)
	{
		try
		{
			PrintStream printstream = new PrintStream(new FileOutputStream(s));
			for (int i = 0; i < list.size(); i++)
			{
				String s1 = (String)list1.get(i);
				Iterator iterator = ((HashSet)list.get(i)).iterator();
				do
				{
					if (!iterator.hasNext())
						break;
					String s2 = (String)list1.get(((Integer)iterator.next()).intValue());
					if (s1.compareTo(s2) < 0)
						printstream.println((new StringBuilder()).append(s1).append("\t").append(s2).toString());
				} while (true);
			}

		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			System.exit(1);
		}
	}

	private static void writeWeightedEdges(String s, List list, List list1)
	{
		try
		{
			PrintStream printstream = new PrintStream(new FileOutputStream(s));
			for (int i = 0; i < list.size(); i++)
			{
				String s1 = (String)list1.get(i);
				HashMap hashmap = (HashMap)list.get(i);
				Iterator iterator = hashmap.keySet().iterator();
				do
				{
					if (!iterator.hasNext())
						break;
					int j = ((Integer)iterator.next()).intValue();
					String s2 = (String)list1.get(j);
					if (s1.compareTo(s2) < 0)
						printstream.println((new StringBuilder()).append(s1).append("\t").append(s2).append("\t").append(hashmap.get(Integer.valueOf(j))).toString());
				} while (true);
			}

		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			System.exit(1);
		}
	}

	private static void printUsageAndExit()
	{
		System.err.println("Usage: java Project <infile>");
		System.exit(1);
	}
}