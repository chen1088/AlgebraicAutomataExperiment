package FiniteAutomata;

import java.util.*;

import static java.util.Map.entry;

public class SyntacticMonoid {
   Set<Permutation> closure;

   public SyntacticMonoid(Permutation trans0, Permutation trans1)
   {
      closure = new HashSet<>();
      closure.add(Permutation.identity);
      closure.add(trans0);
      closure.add(trans1);
      LinkedHashSet<Permutation> interestedElements = new LinkedHashSet<>();
      interestedElements.add(trans0);
      interestedElements.add(trans1);
      while(!interestedElements.isEmpty())
      {
         //compute the coset of the one of the new element
         Iterator<Permutation> iter = interestedElements.iterator();
         Permutation current = iter.next();
         Set<Permutation> coset = new HashSet<>();
         for(var p: closure)
         {
            coset.add(p.composite(current));
         }
         for(var p: coset)
         {
            if(!closure.contains(p))
            {
               interestedElements.add(p);
            }
         }
         closure.addAll(coset);
         interestedElements.remove(current);
      }
   }
   public static void main(String[] args)
   {
//      Map<Integer,Integer> m1 = Map.ofEntries(
//              entry(1,2),
//              entry(2,3),
//              entry(3,1),
//              entry(4,5),
//              entry(5,6),
//              entry(6,4),
//              entry(7,8),
//              entry(8,9),
//              entry(9,7)
//      );
//      Map<Integer,Integer> m2 = Map.ofEntries(
//              entry(1,4),
//              entry(4,7),
//              entry(7,1),
//              entry(2,5),
//              entry(5,8),
//              entry(8,2),
//              entry(3,6),
//              entry(6,9),
//              entry(9,3)
//      );
      Map<Integer,Integer> m1 = Map.ofEntries(
              entry(1,2),
              entry(2,3),
              entry(3,4),
              entry(4,5),
              entry(5,1)
      );
      Map<Integer,Integer> m2 = Map.ofEntries(
              entry(1,3),
              entry(3,2)
      );
      Permutation p1 = new Permutation(m1);
      Permutation p2 = new Permutation(m2);
      SyntacticMonoid sm = new SyntacticMonoid(p1,p2);
      System.out.println(sm);
   }
   public String toString()
   {
      return "Monoid size: " + closure.size();
   }
}
