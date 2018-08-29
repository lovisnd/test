package com.tiankui.reactService.util;
/**
 * @(#)DateDiff.java
 * DateDiff application-展示一段时间中的所有年月
 * @param intYearFirst-起点年，intYearSecond-终点年，intMonthFirst-起点月，intMonthSecond-终点月
 * @return--所有年月的LIST
 * @author zhangmingrui
 * @version 1.00 2018/8/17
 */
import java.util.ArrayList;
import java.util.List;
public class DateDiff {
public List dateDiff(int intYearFirst,int intYearSecond,int intMonthFirst,int intMonthSecond){
List ymList = new ArrayList();
for(int j=intYearFirst;j<=intYearSecond;j++){
/*如果年相同，循环月即可*/    
   if(intYearFirst==intYearSecond){
    for(int i=intMonthFirst;i<=intMonthSecond;i++){
        StringBuffer strBuf=new    StringBuffer();
        if(i<10)
        {
            strBuf.append(j).append("-").append(0).append(i);
            ymList.add(strBuf);
        }
        if(i>9)
        {
            strBuf.append(j).append("-").append(i);
            ymList.add(strBuf);
        }
  
   }
   }
/*如果年不相同，分类处理*/
else {
/*从起点年开始，直到12月*/    
  if(j==intYearFirst){  
    for(int k=intMonthFirst;k<=12;k++){
        StringBuffer strBuf=new    StringBuffer();
        if(k<10)
        {
            strBuf.append(j).append("-").append(0).append(k);
            ymList.add(strBuf);
        }
        if(k>9)
        {
            strBuf.append(j).append("-").append(k);
            ymList.add(strBuf);
        }
 
  }
  }
/*非起点年份，同时也非终点年份*/
  if(j!=intYearSecond&&j!=intYearFirst){
      for(int l=1;l<=12;l++){
        StringBuffer strBuf=new    StringBuffer();
        if(l<10)
        {
            strBuf.append(j).append("-").append(0).append(l);
            ymList.add(strBuf);
        }
        if(l>9)
        {
            strBuf.append(j).append("-").append(l);
            ymList.add(strBuf);
        }
 
  }
  }
/*终点年份*/
   if(j==intYearSecond){
      for(int z=1;z<=intMonthSecond;z++){
        StringBuffer strBuf=new    StringBuffer();
        if(z<10)
        {
            strBuf.append(j).append("-").append(0).append(z);
            ymList.add(strBuf);
        }
        if(z>9)
        {
            strBuf.append(j).append("-").append(z);
            ymList.add(strBuf);
        }
 }
 }
 }//End of else if.    
 }//End of Year Loop.
return ymList;
}  //End of method  
    public static void main(String[] args) {        
        DateDiff a1=new DateDiff();
        List a=a1.dateDiff(2017,2018,8,8);
            for(int j=0;j<a.size();j++){
             System.out.println(a.get(j));
         }
    }
}