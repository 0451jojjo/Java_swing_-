/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication1;

/**
 *
 * @author Administrator
 */
public class Pnum implements Comparable<Pnum> {
    
    private Integer sv;
    private Integer num;
    public Pnum(Integer sv,Integer num)
    {
        this.sv=sv;
        this.num=num;
    }
     public Integer getSv() {
        return sv;
    }
 
    public void setSv(Integer sv) {
        this.sv = sv;
    }
     public Integer getNum() {
        return num;
    }
 
    public void setNum(Integer num) {
        this.num = num;
    }

    public int compareTo(Pnum o) {
         if(null == this.num) {
            return 1;
        }
        if(null == o.getNum()) {
            return -1;
        }
        return o.num.compareTo(this.getNum());
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
