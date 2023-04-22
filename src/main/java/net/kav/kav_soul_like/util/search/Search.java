package net.kav.kav_soul_like.util.search;

import net.kav.kav_soul_like.util.item_requirement.combat_stats_req;

import java.util.ArrayList;
//https://www.geeksforgeeks.org/java-program-to-perform-binary-search-on-arraylist/
public class Search {
    // Returns index of x if it is present in arr[],
    // else return -1
    public static int search(ArrayList<combat_stats_req> arr, String x) {

      for(int i=0;i<arr.size();i++)
      {
          boolean res=  x.equals(arr.get(i).name_id);
          if(res==true)
          {
              return i;
          }

      }

        return -1;

    }
}