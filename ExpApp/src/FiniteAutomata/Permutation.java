package FiniteAutomata;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class Permutation {
   public Map<Integer,Integer> perm;
   public static Permutation identity = new Permutation(new HashMap<Integer, Integer>());
   public Permutation(Map<Integer,Integer> p)
   {
      Map<Integer,Integer> tmpperm = new HashMap<>();
      for(var kv:p.entrySet())
      {
         if(!kv.getKey().equals(kv.getValue()))
            tmpperm.put(kv.getKey(),kv.getValue());
      }
      perm = tmpperm;
   }
   /// a * b := a(b(x)) := a.composite(b)
   public Permutation composite(Permutation other)
   {
      Map<Integer,Integer> resmap = new HashMap<>();
      for(var kv:other.perm.entrySet())
      {
         var v = kv.getValue();
         if(perm.containsKey(v))
         {
            if(!perm.get(v).equals(kv.getKey()))
               resmap.put(kv.getKey(),perm.get(v));
         }
         else
         {
            resmap.put(kv.getKey(),kv.getValue());
         }
      }
      for(var kv:perm.entrySet())
      {
         var k = kv.getKey();
         if(!other.perm.containsKey(k))
         {
            resmap.put(k,kv.getValue());
         }
      }
      return new Permutation(resmap);
   }

   public boolean equals(Object other)
   {
      return (other instanceof Permutation) && perm.equals(((Permutation)other).perm);
   }
   public static Permutation getRandom(int size)
   {
      Map<Integer,Integer> res = new HashMap<>();

      return new Permutation(res);
   }
   public int hashCode()
   {
      return perm.hashCode();
   }
   public String toString()
   {
      return "perm = " + perm.toString();
   }
   public static void main(String[] args)
   {
      Map<Integer,Integer> a = Map.ofEntries(
              entry(1,2),
              entry(2,3),
              entry(3,1),
              entry(4,5),
              entry(5,6),
              entry(6,4),
              entry(7,8),
              entry(8,9),
              entry(9,7)
      );
      Map<Integer,Integer> b = Map.ofEntries(
              entry(1,4),
              entry(4,7),
              entry(7,1)
      );
      Map<Integer,Integer> c = Map.ofEntries(
              entry(5,1),
              entry(4,3)
      );
      Permutation aa = new Permutation(a);
      Permutation bb = new Permutation(b);
      Permutation cc = new Permutation(c);
      System.out.println(aa);
      System.out.println(bb);
      Permutation d = aa.composite(bb).composite(cc);
      Permutation dd = aa.composite(bb.composite(cc));
      System.out.println(d);
      System.out.println(dd);
      System.out.println(d.equals(dd));
      System.out.println(d.hashCode());
      System.out.println(dd.hashCode());
   }
}
