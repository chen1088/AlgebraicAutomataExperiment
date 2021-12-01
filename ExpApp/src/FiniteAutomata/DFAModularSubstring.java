package FiniteAutomata;

import java.util.HashMap;
import java.util.Map;

public class DFAModularSubstring extends BinaryDFA {

   public Permutation trans0;
   public Permutation trans1;
   public DFAModularSubstring(String substring,int m,int j)
   {
      //Assume the length of substring is less than or equal to m
      Map<Integer,Integer> m1 = new HashMap<>();
      Map<Integer,Integer> m2 = new HashMap<>();
      for(var i = 0;i<m;++i)
      {
         if(i != j%m)
         {
            m1.put(i,(i+1)%m);
            m2.put(i,(i+1)%m);
         }
         else
         {
            m1.put(i,substring.charAt(0)=='0'?m:(i+1)%m);
            m2.put(i,substring.charAt(0)!='0'?m:(i+1)%m);
         }
      }
      var l = Math.min(substring.length(),m-1);
      for(var i = 1;i<l;++i)
      {
         m1.put(m+i-1,substring.charAt(i)=='0'?(m+i):(j+i+1)%m);
         m2.put(m+i-1,substring.charAt(i)!='0'?(m+i):(j+i+1)%m);
      }
      m1.put(m+l-1,(j+l+1)%m);
      m2.put(m+l-1,(j+l+1)%m);
      trans0 = new Permutation(m1);
      trans1 = new Permutation(m2);
   }

   public String toString()
   {
      return "0-trans: "+ trans0.toString() + "\n1-trans: " + trans1.toString();
   }

   public static void main(String[] args)
   {
      var d = new DFAModularSubstring("1011000001001010",20,5);
      System.out.println(d);
      SyntacticMonoid sm = new SyntacticMonoid(d.GetTrans0(),d.GetTrans1());
      System.out.println(sm);
   }

   @Override
   public Permutation GetTrans0() {
      return trans0;
   }

   @Override
   public Permutation GetTrans1() {
      return trans1;
   }
}
